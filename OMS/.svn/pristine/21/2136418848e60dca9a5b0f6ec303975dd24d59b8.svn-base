package com.spfood.oms.order.manager;

import java.util.List;
import java.util.Map;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderPay;
import com.spfood.oms.order.intf.domain.OrderTemp;
import com.spfood.oms.order.intf.searchCriteria.OrderSearchCriteria;


/*
 * @Author:Isidore Han
 * @Date:2016/12
 */
public interface OrderQueryManager {

	
	public PageInfo<Order> queryOrderByParas(OrderSearchCriteria orderSearchCriteria);
	
	public Order queryOrderDetail(String orderNo);
	
	public Order queryOrderByOrderNo(String orderNo);
	
	public List<Order> queryOrderListByOrderNoList(List<String> orderNos);

	public List<OrderTemp> getOrderTempByStatus(Integer status);

	public OrderPay getOrderPayByOrderNo(String orderNo);

	public List<OrderPay> getOrderPayByOrderNos(List<String> orderNos);

	public List<String> getOrderTempOrderNosByStatus(Integer status);

	public Long getOrderTempCountByStatus(Integer status);

	public OrderPay queryOrderPayByOrderNo(String orderNo);

	public List<Order> queryOrderDetailList(List<String> orderNoList);

	
	
	
	/**
	 * 查询要同步到FMS的订单支付信息(查询订单表中订单状态为3的数据) z20170329
	 */
	public List<OrderPay> getUnSynOrderPayInfo();

	

	
}

	
