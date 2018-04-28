package com.spfood.oms.order.manager.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import com.spfood.basicservice.intf.OrderNumberGeneratorService;
import com.spfood.kernel.utils.MessageHelper;
import com.spfood.oms.order.dao.OrderDao;
import com.spfood.oms.order.dao.OrderLogDao;
import com.spfood.oms.order.dao.OrderPayDao;
import com.spfood.oms.order.dao.OrderTempDao;
import com.spfood.oms.order.intf.domain.CreateOrderMessage;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderCommodity;
import com.spfood.oms.order.intf.domain.OrderLog;
import com.spfood.oms.order.intf.domain.OrderTemp;
import com.spfood.oms.order.manager.OrderCreateManager;
import com.spfood.oms.order.thread.AddOrderLogThread;
import com.spfood.oms.order.utils.Constant;
import com.spfood.oms.order.utils.OrderStatus;
import com.spfood.oms.order.utils.OrderType;
import com.spfood.oms.order.utils.VerificationCodeUitls;
import com.spfood.wms.goods.domain.vo.GoodsQuantityVo;
import com.spfood.wms.goods.domain.vo.OrderResultVo;
import com.spfood.wms.goods.intf.GoodsQuantityIntf;


/**
* @author huangcj
* @date 2017年1月3日
* <p>Description: 订单管理修改实现类</p>
*/
@Service
@Transactional
public class OrderCreateManagerImpl implements OrderCreateManager{
	private static final Logger logger = Logger.getLogger(OrderCreateManagerImpl.class);
	
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderTempDao orderTempDao;
	@Autowired
	private OrderLogDao orderLogDao;
	@Autowired
	private OrderPayDao orderPayDao;
	@Autowired
	private GoodsQuantityIntf goodsQuantityService;
	@Autowired
	private OrderNumberGeneratorService orderNumberGeneratorService;
	
	@Override
	public CreateOrderMessage addOrder(Order order){
		if (order == null) {
			logger.info("传入参数为null");
			return new CreateOrderMessage(Constant.ERROR,MessageHelper.getMessage("order.manager.addOrder.orderIsNull"));
		}
		List<OrderCommodity> newProducts = order.getOrderCommList();//获得订单中的商品
		if (newProducts == null || newProducts.size() == 0) {
			logger.info("订单中没有商品");
			return new CreateOrderMessage(Constant.ERROR,MessageHelper.getMessage("order.manager.addOrder.orderCommodityIsNull"));
		}
		order.setVerification(VerificationCodeUitls.getVerificationCode(Constant.CODE_COUNT, Constant.HAS_LITTERS));//添加验证码
		logger.info(MessageHelper.getMessage("service")+"orderNumberGeneratorService.getNextOrderNumber(String paramString)");
		order.setOrderNo(orderNumberGeneratorService.getNextOrderNumber(order.getCustomerCode()));//订单编号
		String orderNo = order.getOrderNo();
		order.setType(OrderType.ORDINARY.getValue());//设置为普通订单
		order.setIsBill(Constant.NOT_BILL);//设置为未开发票1
		order.setCreateTime(new Date());	//设置订单的生成时间
		order.setStatus(OrderStatus.UNPAIED.getValue());//订单状态设为待付款1
		BigDecimal orderAmount = order.getComAmount().subtract(order.getDiscount());//商品总金额-折扣
		order.setOrderAmount(orderAmount.add(order.getCarriage()));//加上运费 订单总金额
		for (OrderCommodity product : newProducts) {
			product.setOrderNo(orderNo);//设置商品对应的订单编号
		}
		order.setOrderTemp(new OrderTemp(orderNo, order.getStatus(), order.getCreateTime()));//临时表
		order.getOrderPay().setOrderNo(orderNo);
		order.getOrderPay().setIsPay(Constant.ORDERPAY_NOT_PAY);//未支付1
		order.getOrderPay().setIsArrived(Constant.ORDERPAY_NOT_ARRIVED);//未到账1
		order.getOrderPay().setIsSendFms(Constant.ORDERPAY_NOT_SENDFMS);//未发送1
		orderDao.insert(order);//保存订单、关联商品、日志、支付、临时表 
		
		OrderResultVo placeOrder = placeOrder(order.getOrderCommList());//下订单操作物品库存
		if (placeOrder.isSuccess()) {
			new AddOrderLogThread(orderLogDao, order).start();//新建线程添加日志
			return new CreateOrderMessage(Constant.SUCCESS, order.getOrderNo());
		} 
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动事务回滚
		return new CreateOrderMessage(Constant.ERROR, placeOrder.getGoodsCodeList());
	}
	
	@Override
	public CreateOrderMessage addExchangeOrder(Order newOrder){
		if (newOrder == null) {
			logger.info("传入参数为null");
			return new CreateOrderMessage(Constant.ERROR,MessageHelper.getMessage("order.manager.addOrder.orderIsNull"));
		}
		List<OrderCommodity> newProducts = newOrder.getOrderCommList();//获得订单中的商品
		if (newProducts == null || newProducts.size() == 0) {
			logger.info("订单中没有商品");
			return new CreateOrderMessage(Constant.ERROR,MessageHelper.getMessage("order.manager.addOrder.orderCommodityIsNull"));
		}
		newOrder.setVerification(VerificationCodeUitls.getVerificationCode(Constant.CODE_COUNT, Constant.HAS_LITTERS));//添加验证码
		String orderNo = newOrder.getOrderNo();
		newOrder.setType(OrderType.CHANGE.getValue()); //设置为换货订单1
		newOrder.setCreateTime(new Date());		 //设置订单的生成时间
		newOrder.setIsBill(Constant.NOT_BILL);	//设置为未开发票1
		for (OrderCommodity product : newOrder.getOrderCommList()) {
			product.setOrderNo(orderNo);//设置商品对应的订单编号
		}
		newOrder.setOrderTemp(new OrderTemp(orderNo, newOrder.getStatus(), newOrder.getCreateTime()));//临时表
		orderDao.insert(newOrder);//保存订单、关联商品、日志、临时表 
		new AddOrderLogThread(orderLogDao, newOrder).start();//新建线程添加日志
		return new CreateOrderMessage(Constant.SUCCESS, newOrder.getOrderNo());
	}
	
	@Override
	public boolean addOrderLog(OrderLog orderLog) {
		if (orderLog == null) {
			return false;
		}
		if (!StringUtils.hasText(orderLog.getOrderNo()) || !StringUtils.hasText(orderLog.getOprator())
				|| !StringUtils.hasText(orderLog.getOpratorCode()) || !StringUtils.hasText(orderLog.getOprContent())) {
			return false;
		}
		OrderLog log = new OrderLog(orderLog.getOrderNo());//创建订单日志
		log.setOprTime(new Date());//创建日志时间
		log.setOprContent(orderLog.getOprContent());//日志的内容
		log.setOprator(orderLog.getOprator());//操作人
		log.setOpratorCode(orderLog.getOpratorCode());//操作人编码
		orderLogDao.insert(log);//保存日志
		return true;
	}

	@Override
	public boolean addOrderLog(List<OrderLog> orderLogList) {
		if (orderLogList != null &&orderLogList.size() > 0) {
			orderLogDao.insertInBatch(orderLogList);
			return true;
		}
		return false;
	}

	private OrderResultVo placeOrder(List<OrderCommodity> newProducts) {
		List<GoodsQuantityVo> goodsCodeList = new ArrayList<GoodsQuantityVo>(); 
		for (OrderCommodity orderCommodity : newProducts) {
			GoodsQuantityVo goodsQuantityVo = new GoodsQuantityVo(orderCommodity.getCode(), orderCommodity.getCount());
			goodsCodeList.add(goodsQuantityVo);
		}
		logger.info("下订单调用 goodsQuantityService服务 扣减库存");
		return goodsQuantityService.placeOrders(goodsCodeList);
	}
}
