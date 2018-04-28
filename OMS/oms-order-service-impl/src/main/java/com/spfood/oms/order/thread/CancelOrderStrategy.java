package com.spfood.oms.order.thread;

import java.util.Date;

import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderLog;
import com.spfood.oms.order.utils.OrderLogText;

public class CancelOrderStrategy implements Strategy {

	@Override
	public OrderLog getOrderLog(Order order) {
		OrderLog orderLog = new OrderLog(order.getOrderNo());
		orderLog.setOprTime(new Date());
		orderLog.setOprContent(OrderLogText.CANCEL_ORDER);
		orderLog.setOprator(OrderLogText.OPRATOR_SYSTEM);
		orderLog.setOpratorCode(OrderLogText.OPRATOR_SYSTEM_CODE);
		return orderLog;
	}

}
