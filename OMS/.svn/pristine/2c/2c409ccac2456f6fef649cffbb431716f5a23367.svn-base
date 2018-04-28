package com.spfood.oms.order.thread;

import java.util.Date;

import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderLog;
import com.spfood.oms.order.utils.OrderLogText;
import com.spfood.oms.order.utils.OrderType;

public class HaspaiedOrderStrategy implements Strategy {

	@Override
	public OrderLog getOrderLog(Order order) {
		OrderLog orderLog = new OrderLog(order.getOrderNo());
		orderLog.setOprTime(new Date());
		if (order.getType() == OrderType.ORDINARY.getValue()) {
			orderLog.setOprContent(OrderLogText.ORDER_PAY);
			orderLog.setOprator(OrderLogText.OPRATOR_SYSTEM);
			orderLog.setOpratorCode(OrderLogText.OPRATOR_SYSTEM_CODE);
		}else if (order.getType() == OrderType.CHANGE.getValue()) {
			orderLog.setOprContent(OrderLogText.ADD_ORDER);
			orderLog.setOprator(order.getCustomer());
			orderLog.setOpratorCode(order.getCustomerCode());
		}
		return orderLog;
	}


}
