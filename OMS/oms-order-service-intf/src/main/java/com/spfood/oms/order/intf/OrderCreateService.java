package com.spfood.oms.order.intf;

import java.util.List;

import com.spfood.oms.order.intf.domain.CreateOrderMessage;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderLog;

public interface OrderCreateService {

	/**
	 * 生成订单
	 * @param order :订单详情
	 * @return CreateOrderMessage 包含成功或失败 ,生成的单号或商品异常信息
	 */
	public CreateOrderMessage addOrder(Order order);
	
	/**
	 * 生成换货订单
	 * @param order :订单详情
	 * @return CreateOrderMessage 包含成功或失败 ,生成的单号或商品异常信息
	 */
	public CreateOrderMessage addExchangeOrder(Order order);
	
	/**
	 * 添加订单日志
	 * @param orderLog 订单日志对象
	 * @return true/false
	 */
	public boolean addOrderLog(OrderLog orderLog);
	
	/**
	 * 批量添加订单日志
	 * @param orderLogList
	 * @return true/false
	 */
	public boolean addOrderLog(List<OrderLog> orderLogList);
	
}
