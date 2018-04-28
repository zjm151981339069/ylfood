package com.spfood.oms.order.manager.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import com.spfood.kernel.utils.MessageHelper;
import com.spfood.oms.order.dao.OrderCommodityDao;
import com.spfood.oms.order.dao.OrderDao;
import com.spfood.oms.order.dao.OrderLogDao;
import com.spfood.oms.order.dao.OrderPayDao;
import com.spfood.oms.order.dao.OrderTempDao;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderBill;
import com.spfood.oms.order.intf.domain.OrderCommodity;
import com.spfood.oms.order.intf.domain.OrderLog;
import com.spfood.oms.order.intf.domain.OrderPay;
import com.spfood.oms.order.intf.domain.OrderTemp;
import com.spfood.oms.order.intf.searchCriteria.OrderSearchCriteria;
import com.spfood.oms.order.manager.OrderManager;
import com.spfood.oms.order.thread.AddOrderLogThread;
import com.spfood.oms.order.utils.Constant;
import com.spfood.oms.order.utils.OrderLogText;
import com.spfood.oms.order.utils.OrderPayType;
import com.spfood.oms.order.utils.OrderStatus;
import com.spfood.oms.orderpay.intf.OrderPayByACPService;
import com.spfood.oms.orderpay.intf.OrderPayByAliPayService;
import com.spfood.oms.orderpay.intf.OrderPayByWeiChatService;
import com.spfood.oms.orderpay.intf.domain.ACPPayQueryResult;
import com.spfood.oms.orderpay.intf.domain.AlipayQueryResult;
import com.spfood.oms.orderpay.intf.domain.WeiChatPayQueryResult;
import com.spfood.wms.goods.domain.vo.GoodsQuantityVo;
import com.spfood.wms.goods.intf.GoodsQuantityIntf;


/**
* @author huangcj
* @date 2017年1月3日
* <p>Description: 订单管理修改实现类</p>
*/
@Service
@Transactional
public class OrderManagerImpl implements OrderManager{ 
	private static final Logger logger = Logger.getLogger(OrderManagerImpl.class);
	
	@Autowired
	private OrderTempDao orderTempDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderCommodityDao orderCommodityDao;
	@Autowired
	private OrderPayDao orderPayDao;
	@Autowired
	private OrderLogDao orderLogDao;
	@Autowired
	private OrderPayByACPService orderPayByACPService;
	@Autowired
	private OrderPayByAliPayService orderPayByAliPayService;
	@Autowired
	private OrderPayByWeiChatService orderPayByWeiChatService;
	@Autowired
	private GoodsQuantityIntf goodsQuantityService;

	@Override
	public boolean abondonOrder(String orderNo ,String operator,String operatorCode) {
		if (!StringUtils.hasText(orderNo)) {
			return false;
		}
		if (!StringUtils.hasText(operator) || !StringUtils.hasText(operatorCode)) {
			return false;
		}
		Order selectOne = orderDao.selectOne(new OrderSearchCriteria(orderNo));
		if(selectOne.getStatus() != OrderStatus.UNPAIED.getValue()) {
			return false;
		}
		OrderTemp orderTemp = new OrderTemp(orderNo);
		List<OrderTemp> orderTempList = new ArrayList<OrderTemp>();
		orderTempList.add(orderTemp);
		return this.cancelOrder(orderTempList,operator,operatorCode);
	}

	@Override
	public String setSign(String orderNo, String sign,String username,String usernameCode) {
		if (sign != null && sign.length()>100) {
			return MessageHelper.getMessage("order.sign.maxString");
		}
		if (!StringUtils.hasText(orderNo)) {
			return MessageHelper.getMessage("notFoundOrderNo");
		}
		if (!StringUtils.hasText(username) || !StringUtils.hasText(usernameCode)) {
			return MessageHelper.getMessage("notFoundUser");
		}
		this.addOrderLog(orderNo, OrderLogText.SET_SIGN, username,usernameCode);//添加日志
		Order order = new Order(orderNo);
		order.setSign(sign);
		orderDao.updateByOrderNoSelective(order);
		return MessageHelper.getMessage("success");
	}

	@Override
	public String updateDeliverTime(String orderNo, Date deliverTime,String username,String usernameCode) {
		if (deliverTime == null) {
			return MessageHelper.getMessage("order.deliverTime.form");
		}
		if (!StringUtils.hasText(orderNo)) {
			return MessageHelper.getMessage("notFoundOrderNo");
		}
		if (!StringUtils.hasText(username) || !StringUtils.hasText(usernameCode)) {
			return MessageHelper.getMessage("notFoundUser");
		}
		if(deliverTime.getTime() < new Date().getTime()){
			return MessageHelper.getMessage("order.deliverTime.outDate");
		}
		Order order = orderDao.selectOne(new OrderSearchCriteria(orderNo));
		if (order.getStatus() != OrderStatus.UNPAIED.getValue() && order.getStatus() != OrderStatus.HASPAIED.getValue()) {
			return MessageHelper.getMessage("order.deliverTime.statusError");
		}
		this.addOrderLog(orderNo, OrderLogText.SET_ORDER_DELIVERTIME, username,usernameCode);//添加日志
		order.setDeliverTime(deliverTime);
		order.setModifier(username);
		order.setModifierCode(usernameCode);
		orderDao.updateByOrderNoSelective(order);
		return MessageHelper.getMessage("success");
	}

	private void addOrderLog(String orderNo,String content,String operator,String operatorCode){
		OrderLog log = new OrderLog(orderNo);//创建订单日志
		log.setOprTime(new Date());//创建日志时间
		log.setOprContent(content);//日志的内容
		log.setOprator(operator);//操作人
		log.setOpratorCode(operatorCode);//操作人
		orderLogDao.insert(log);//保存日志
	}

	@Override
	public boolean deleteOrderTempByOrderNo(String orderNo) {
		if (!StringUtils.hasText(orderNo)) {
			return false;
		}
		orderTempDao.delete(new OrderTemp(orderNo));
		return true;
	}
	
	@Override
	public void deleteOrderTempByOrderNo(List<String> orderNoList) {
		if (orderNoList != null && orderNoList.size() > 0) {
			orderTempDao.deleteByOrderNoInBatch(orderNoList);
		}
	}

	@Override
	public boolean updateOrderVerification(String orderNo, String verificationCode) {
		if (!StringUtils.hasText(orderNo)) {
			return false;
		}
		Order order = new Order(orderNo);
		order.setVerification(verificationCode);
		orderDao.updateByOrderNoSelective(order);
		return true;
	}

	@Override
	public boolean updateOrderTempStatusByOrderNo(String orderNo, Integer status) {
		if (!StringUtils.hasText(orderNo)) {
			return false;
		}
		OrderTemp orderTemp = new OrderTemp(orderNo);
		orderTemp.setStatus(status);
		orderTempDao.updateByOrderNoSelective(orderTemp);
		return true;
	}

	@Override
	public boolean updateOrderBill(OrderBill orderBill) {
		if (orderBill != null && StringUtils.hasText(orderBill.getOrderNo())) {
			Order order = new Order(orderBill.getOrderNo());
			order.setIsBill(Constant.ALREADY_BILL);	//	是否已开发票
			order.setBillType(orderBill.getBillType());			//	发票类型
			order.setBillTitle(orderBill.getBillTitle());			//	发票抬头
			order.setBillContent(orderBill.getBillContent());		//	发票内容
			order.setOrderAmount(orderBill.getOrderAmount());
			orderDao.updateByOrderNoSelective(order);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateOrderPay(OrderPay orderPay) {
		if (orderPay == null || !StringUtils.hasText(orderPay.getOrderNo())) {
			return false;
		}
		orderPayDao.updateByOrderNoSelective(orderPay);
		return true;
	}

	@Override
	public void updateOrderStatus(List<Order> orderList) {
		if (orderList != null && orderList.size() > 0) {
			orderDao.updateOrderStatus(orderList);
		}
	}
	
	@Override
	public boolean updateOrderStatus(Order order) {
		if (order != null && StringUtils.hasText(order.getOrderNo())) {
			int i = orderDao.updateByOrderNoSelective(order);
			if (i == 1) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updateOrderTempStatus(List<OrderTemp> orderTempList) {
		if (orderTempList == null || orderTempList.size() == 0) {
			return false;
		}
		for (OrderTemp orderTemp : orderTempList) {
			if (orderTemp.getStatus() == OrderStatus.CANCEL.getValue()) {
				return false;
			}
		}
		orderTempDao.updateOrderStatus(orderTempList);
		return true;
	}

	@Override
	public boolean updateOrderPayIsSendFms(String orderNo) {
		if (!StringUtils.hasText(orderNo)) {
			return false;
		}
		OrderPay orderPay = new OrderPay(orderNo);
		orderPay.setIsSendFms(Constant.ORDERPAY_SENDFMS);
		orderPayDao.updateByOrderNoSelective(orderPay);
		return true;
	}
	
	@Override
	public void updateOrderPayIsSendFms(List<String> orderNoList) {
		if (orderNoList != null && orderNoList.size() > 0) {
			List<OrderPay> orderPayList = new ArrayList<OrderPay>();
			for (String orderNo : orderNoList) {
				OrderPay orderPay = new OrderPay(orderNo);
				orderPay.setIsSendFms(Constant.ORDERPAY_SENDFMS);
				orderPayList.add(orderPay);
			}
			orderPayDao.updateBatchIsSendFms(orderPayList);
		}
	}
	
	@Override
	public Order getOrderAndCommodity(String orderNo) {
		if (StringUtils.hasText(orderNo)) {
			return orderDao.selectOrderAndCommodity(orderNo);
		}
		return null;
	}
	
	@Override
	public List<Order> getOrderAndCommodity(List<String> orderNoList) {
		if (orderNoList != null && orderNoList.size() > 0) {
			return orderDao.selectOrderAndCommodity(orderNoList);
		}
		return null;
	}

	@Override
	public void cancelOrderQuartz() {
		Long timeLong = new Date().getTime() - Constant.CANCEL_ORDER_TIME;
		Date time = new Date(timeLong);//得到当前时间-指定的未付款时间,超过此时间未付款取消订单
		OrderTemp orderTemp = new OrderTemp();
		orderTemp.setStatus(OrderStatus.UNPAIED.getValue());
		orderTemp.setCreateTime(time);
		List<OrderTemp> orderTempList = orderTempDao.selectList(orderTemp);//查询条件: status、createTime
		this.cancelOrder(orderTempList,null,null);
	}

	private boolean cancelOrder(List<OrderTemp> orderTempList, String operator, String operatorCode) {
		if (orderTempList != null && orderTempList.size() > 0) {
			List<Order> orderList = new ArrayList<Order>();//待修改状态的订单集合
			List<String> orderNoList = new ArrayList<String>();//要删除的临时订单编号集合 
			List<String> isPayOrderNoList = new ArrayList<String>();//可能已付款还未修改状态的订单编号
			
			this.queryOrderPay(orderTempList,orderList,orderNoList,isPayOrderNoList);//得到未支付的订单
			
			if (orderTempList != null && orderTempList.size() > 0) {
				if (operator != null) {//手动取消会传入operator
					int i = orderDao.cancelOrderDao(orderTempList.get(0).getOrderNo());//修改条数 修改订单状态为已取消
					if (i == 0) {
						logger.info(MessageHelper.getMessage("cancelOrderError"));
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动事务回滚
						return false;
					}
				}else {
					orderDao.updateBatchCancelOrder(orderList);//修改订单状态为已取消
				}
				orderTempDao.deleteByOrderNoInBatch(orderNoList);//删除的临时订单
				List<OrderCommodity> orderProductList = orderCommodityDao.selectByOrderNoList(orderNoList);//得到订单中的商品待添加库存
				if (orderProductList.size() > 0) {
					List<GoodsQuantityVo> paramList = new ArrayList<GoodsQuantityVo>();
					for (OrderCommodity orderCommodity : orderProductList) {
						paramList.add(new GoodsQuantityVo(orderCommodity.getCode(), orderCommodity.getCount()));
					}
					boolean flag = goodsQuantityService.cancelOrders(paramList);
					if (!flag) {	//如果返回flase
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动事务回滚
						return false;
					}	
				}
			}
			if (isPayOrderNoList != null && isPayOrderNoList.size() > 0) {
				this.updateOrderStatusToIsPay(isPayOrderNoList);//执行已支付 修改订单状态、订单支付、临时订单为已支付、添加日志
			}
			if (operator != null) {
				new AddOrderLogThread(orderLogDao, orderList.get(0),operator,operatorCode).start();//添加日志(确定为手动取消)
			}else {
				new AddOrderLogThread(orderLogDao, orderList).start();
			}
		}
		return true;
	}
	
	private void queryOrderPay(List<OrderTemp> orderTempList, 
			List<Order> orderList, List<String> orderNoList, List<String> isPayOrderNoList) {
		for (OrderTemp orderTemp : orderTempList) {
			orderNoList.add(orderTemp.getOrderNo());
		}
		List<OrderPay> orderPayList = orderPayDao.selectByOrderNos(orderNoList);//查询所有到时未付款的支付
		orderNoList.clear();
		for (int i = 0; i < orderTempList.size(); i++) {
			OrderTemp orderTemp = orderTempList.get(i);
			int indexOf = orderPayList.indexOf(new OrderPay(orderTemp.getOrderNo()));
			OrderPay orderPay = null;
			if (indexOf != -1) {//可能没有支付信息
				orderPay = orderPayList.get(indexOf);
				Integer payType = orderPay.getPayType();
				if (payType != null) {
					if (payType == OrderPayType.WECHAT.getValue()) {//如果是微信 
						logger.info(MessageHelper.getMessage("service")+"orderPayByWeiChatService");
						WeiChatPayQueryResult orderPayQuery = orderPayByWeiChatService.orderPayQuery(null, orderTemp.getOrderNo());
						if(orderPayQuery != null && Constant.WECHAT_SUCCESS.equals(orderPayQuery.getTradeState())){
							orderTempList.remove(orderTemp);
							isPayOrderNoList.add(orderPay.getOrderNo());
							i--;
							continue;
						}
					}else if (payType == OrderPayType.ALIPAY.getValue()) {//如果是支付宝 
						logger.info(MessageHelper.getMessage("service")+"orderPayByAliPayService");
						AlipayQueryResult orderPayQuery = orderPayByAliPayService.orderPayQuery(orderTemp.getOrderNo(), null);
						if(orderPayQuery != null &&Constant.ALIPAY_SUCCESS.equals(orderPayQuery.getTradeStatus())){
							orderTempList.remove(orderTemp);
							isPayOrderNoList.add(orderPay.getOrderNo());
							i--;
							continue;
						}
					}else if (payType == OrderPayType.UNIONPAY.getValue()) {//如果是银联
						logger.info(MessageHelper.getMessage("service")+"orderPayByACPService");
						ACPPayQueryResult orderPayByACPGateQuery = orderPayByACPService.orderPayByACPGateQuery(orderTemp.getOrderNo(), 
								new SimpleDateFormat(Constant.UNIONPAY_TIME_FORMAT).format(orderPay.getPayTime()), null);
						if(orderPayByACPGateQuery != null && Constant.UNIONPAY_SUCCESS.equals(orderPayByACPGateQuery.getOrigRespCode())){
							orderTempList.remove(orderTemp);
							isPayOrderNoList.add(orderPay.getOrderNo());
							i--;
							continue;
						}
					}
				}
			}
			Order order = new Order(orderTemp.getOrderNo());
			order.setStatus(OrderStatus.CANCEL.getValue());
			orderList.add(order);//待修改订单取消状态
			orderNoList.add(orderTemp.getOrderNo());//待删除临时订单
		}
	}
	
	/**
	 * 查询到位已支付执行方法,修改订单状态、订单支付、临时订单为已支付、添加日志
	 */
	private void updateOrderStatusToIsPay(List<String> orderNoList) {
		List<Order> orderList = new ArrayList<Order>();
		List<OrderPay> orderPayList = new ArrayList<OrderPay>();
		List<OrderTemp> orderTempList = new ArrayList<OrderTemp>();
		for (String orderNo : orderNoList) {
			Order order = new Order(orderNo);
			order.setStatus(OrderStatus.HASPAIED.getValue());//已付款
			orderList.add(order);
			OrderPay orderPay = new OrderPay(orderNo);
			orderPay.setIsPay(Constant.ORDERPAY_ISPAY);//已支付
			orderPayList.add(orderPay);
			OrderTemp orderTemp = new OrderTemp(orderNo);
			orderTemp.setStatus(OrderStatus.HASPAIED.getValue());//已付款
			orderTempList.add(orderTemp);
		}
		orderDao.updateOrderStatus(orderList);
		orderPayDao.updateOrderPayIsPay(orderPayList);
		orderTempDao.updateOrderStatus(orderTempList);
		new AddOrderLogThread(orderLogDao,orderList).start();//新建线程添加日志
	}
}
