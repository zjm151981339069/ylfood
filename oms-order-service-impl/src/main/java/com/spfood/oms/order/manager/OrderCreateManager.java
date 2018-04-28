package com.spfood.oms.order.manager;

import java.util.List;

import com.spfood.oms.order.intf.domain.CreateOrderMessage;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderLog;


/*
 * @Author:Isidore Han
 * @Date:2016/12
 */

public interface OrderCreateManager {

	public CreateOrderMessage addOrder(Order order);
	
	public CreateOrderMessage addExchangeOrder(Order order);

	public boolean addOrderLog(OrderLog orderLog);

	public boolean addOrderLog(List<OrderLog> orderLogList);

}
