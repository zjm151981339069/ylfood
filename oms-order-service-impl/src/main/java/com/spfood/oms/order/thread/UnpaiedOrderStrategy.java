package com.spfood.oms.order.thread;

import java.util.Date;

import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderLog;
import com.spfood.oms.order.utils.OrderLogText;

public class UnpaiedOrderStrategy implements Strategy {

	@Override
	public OrderLog getOrderLog(Order order) {
		OrderLog orderLog = new OrderLog();
		orderLog.setOrderNo(order.getOrderNo());
		orderLog.setOprTime(new Date());
		orderLog.setOprContent(OrderLogText.ADD_ORDER);
		orderLog.setOprator(order.getCustomer());
		orderLog.setOpratorCode(order.getCustomerCode());
		return orderLog;
	}
}
