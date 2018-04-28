package com.spfood.oms.order.manager.impl;

/**
 * 换货单审核管理
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.oms.order.dao.OrderDao;
import com.spfood.oms.order.dao.OrderExchangeDao;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderCommodity;
import com.spfood.oms.order.intf.domain.OrderExchange;
import com.spfood.oms.order.intf.domain.OrderExchangeCommodity;
import com.spfood.oms.order.manager.OrderCreateManager;
import com.spfood.oms.order.manager.OrderExchangeManage;
import com.spfood.oms.order.utils.InOrderExchange;
import com.spfood.oms.order.utils.OrderStatus;
import com.spfood.oms.order.utils.OrderType;

@Service
@Transactional
public class OrderExchangeManageImpl implements OrderExchangeManage {

	@Autowired
	private OrderCreateManager orderCreateManager;
	@Autowired
	private OrderExchangeDao orderExchangeDao;
	@Autowired
	private OrderDao orderDao;

	//查询换货单
	@Override
	public OrderExchange selectOrderExchangeByExId(Long exId) {
		
		return orderExchangeDao.selectById(exId);
	}
	
	//修改换货单
	@Override
	public void updateOrderExchangeByExId(OrderExchange orderExchange) {

		orderExchangeDao.updateById(orderExchange);
		
	}
	
	// 审核不通过操作
	@Override
	public void cancelOrderExchangeByExid(OrderExchange orderExchange) {
		orderExchangeDao.updateById(orderExchange);
	}

	// 审核通过操作
	@Override
	public boolean passNewOrderExchange(OrderExchange orderExchange) {

		Long exId = orderExchange.getExId();
		// 获取换货商品信息
		orderExchange = orderExchangeDao.selectById(exId);
		// 未审核状态的才能操作
		if (orderExchange != null && orderExchange.getAuditstatus() == 0 && orderExchange.getExchangeCommodityLists()!= null) {
			try {
				Order order = this.setNewOrderDetail(orderExchange);
				// 传递对象进行订单生成
				orderCreateManager.addExchangeOrder(order);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	// 设置新订单默认信息
	public Order setNewOrderDetail(OrderExchange orderExchange) {

		//OrderDetail oldOrderDetail = orderManagerDao.getOrderDetail(orderExchange.getOrderno());// 获得之前订单
		Order oldOrderDetail = orderDao.selectOrderDetailByOrderNo(orderExchange.getOrderNo());
		String newOrderCode = orderExchange.getExCode();
		List<OrderExchangeCommodity> exchangeList = orderExchange.getExchangeCommodityLists();
		List<OrderCommodity> orderList = new ArrayList<OrderCommodity>();
		for (OrderExchangeCommodity product : exchangeList) {
			OrderCommodity newProduct = new OrderCommodity();
			// 设置换货商品
			newProduct.setComId(null);
			newProduct.setCode( product.getCode());  //设置商品编码
			newProduct.setName(product.getName());   //设置商品名称
			newProduct.setCount(product.getCount()); //设置商品数量
			newProduct.setOrderNo(newOrderCode);    //设置商品关联订单号
			newProduct.setPrice(InOrderExchange.CHANGE_ORDER_PRICE);
			newProduct.setSubTotal(InOrderExchange.CHANGE_ORDER_SUBTOTAL);
			newProduct.setActPrice(InOrderExchange.CHANGE_ORDER_ACTPRICE);
			if (oldOrderDetail != null) {
				List<OrderCommodity> orderCommList = oldOrderDetail.getOrderCommList();
				if (orderCommList != null) {
					//设置新订单商品的类别
					for (OrderCommodity oldProduct : orderCommList) {
						if (product.getCode().contains(oldProduct.getCode())) {
							newProduct.setType(oldProduct.getType());
							newProduct.setTypeName(oldProduct.getTypeName());
							newProduct.setIsPackage(oldProduct.getIsPackage());//设置是否组装
							newProduct.setPictureUrl(oldProduct.getPictureUrl());//原订单图片
							newProduct.setAreaCode(oldProduct.getAreaCode());//原订单温区编码
							newProduct.setAreaName(oldProduct.getAreaName());//原订单温区名
						}
					}
				}
			}
			orderList.add(newProduct);
		}
		Order newOrderDetail = new Order();
		if (oldOrderDetail != null) {
			newOrderDetail.setCustomer(oldOrderDetail.getCustomer()); // 购货人
			newOrderDetail.setCustomerCode(oldOrderDetail.getCustomerCode());
			newOrderDetail.setZone(oldOrderDetail.getZone()); // 收货人所在地区？？？？？
//			newOrderDetail.setBillType(oldOrderDetail.getBillType()); // 发票类型
//			newOrderDetail.setBillTitle(oldOrderDetail.getBillTitle()); // 发票抬头
//			newOrderDetail.setBillContent(oldOrderDetail.getBillContent()); // 发票内容
//			newOrderDetail.setUserComments(oldOrderDetail.getUserComments()); // 用户留言
			newOrderDetail.setCityCode(oldOrderDetail.getCityCode());
			newOrderDetail.setCityName(oldOrderDetail.getCityName());
			

		}
		newOrderDetail.setOrderCommList(orderList);
		newOrderDetail.setOrderNo(newOrderCode); // 编码
		newOrderDetail.setReceiver(orderExchange.getReceiver()); // 收货人
		newOrderDetail.setReceiverCode(orderExchange.getReceiverCode());
		newOrderDetail.setPhone(orderExchange.getPhone()); // 收货人电话
		newOrderDetail.setAddr(orderExchange.getAddr()); // 收货人详情地址？？？？？
		if (orderExchange.getSite() != null && orderExchange.getSiteCode() != null) {
			newOrderDetail.setSite(orderExchange.getSite()); // 提货点
			newOrderDetail.setSiteCode(orderExchange.getSiteCode()); // 提货点
		}else{
			newOrderDetail.setSite(oldOrderDetail.getSite()); // 提货点
			newOrderDetail.setSiteCode(oldOrderDetail.getSiteCode()); // 提货点
		}

		//设置默认信息
		newOrderDetail.setOrderId(null); // 订单ID 自增长
		newOrderDetail.setType(OrderType.CHANGE.getValue()); // 订单类型 1
		newOrderDetail.setStatus(OrderStatus.HASPAIED.getValue()); // 订单状态 2 已付款
		newOrderDetail.setCarriage(InOrderExchange.CHANGE_ORDER_CARRIAGE); // 运费
		
		newOrderDetail.setOrderAmount(InOrderExchange.CHANGE_ORDER_AMOUNT); // 订单总金额
		newOrderDetail.setComAmount(InOrderExchange.CHANGE_ORDER_COMAMOUNT); //商品总金额
		newOrderDetail.setDiscount(InOrderExchange.CHANGE_ORDER_DISCOUNT); // 折扣金额
		
		Date da = new Date();
		long day = 3600 * 1000;
		da.setTime(da.getTime() / day * day +5*60*60*1000);//这个相当于取整，分钟和秒了
		newOrderDetail.setDeliverTime(da); // 期望到货时间向后取整加4个小时
		newOrderDetail.setModifier(null); // 配送时间更改人
		newOrderDetail.setModifyTime(null); // 配送时间更改时间
		newOrderDetail.setCreateTime(new Date()); // 设置订单的生成时间
		newOrderDetail.setSign(null); // 订单标记
		newOrderDetail.setOrderId(null);
		return newOrderDetail;
		
	}

}