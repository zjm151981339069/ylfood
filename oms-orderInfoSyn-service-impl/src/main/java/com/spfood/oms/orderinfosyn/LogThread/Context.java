package com.spfood.oms.orderinfosyn.LogThread;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spfood.oms.order.dao.OrderLogDao;
import com.spfood.oms.order.intf.domain.Order;
/**
 * 日志执行端口
 * @author Administrator
 *
 */
public class Context {

	private Strategy strategy;
	
	public Context(Strategy strategy) {
		this.strategy = strategy;
	}

	public boolean executeStrategy(Order order,OrderLogDao orderLogDao) {
		return strategy.addOrderLog(order,orderLogDao);
	}
	public boolean executeStrategy(List<Order> orderList,OrderLogDao orderLogDao) {
		return strategy.addOrderLog(orderList,orderLogDao);
	}
}
