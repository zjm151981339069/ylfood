package com.spfood.oms.order.thread;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.spfood.oms.order.dao.OrderLogDao;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderLog;
import com.spfood.oms.order.manager.impl.OrderCreateManagerImpl;

public class AddOrderLogThread extends Thread{
	private static final Logger logger = Logger.getLogger(AddOrderLogThread.class);
	
	private OrderLogDao orderLogDao;
	private List<Order> orderList;
	private String operator;
	private String operatorCode;

	public AddOrderLogThread(OrderLogDao orderLogDao, Order order) {
		super();
		this.orderLogDao = orderLogDao;
		orderList = new ArrayList<Order>();
		orderList.add(order);
	}
	
	public AddOrderLogThread(OrderLogDao orderLogDao, Order order,
			String operator, String operatorCode) {
		super();
		this.orderLogDao = orderLogDao;
		orderList = new ArrayList<Order>();
		orderList.add(order);
		this.operator = operator;
		this.operatorCode = operatorCode;
	}
	
	public AddOrderLogThread(OrderLogDao orderLogDao, List<Order> orderList) {
		super();
		this.orderLogDao = orderLogDao;
		this.orderList = orderList;
	}

	@Override
	public void run() {
		logger.info("新建线程添加日志: ");
		try {
			List<OrderLog> orderLogList = new ArrayList<OrderLog>();
			for (Order order : orderList) {
				Strategy strategy = StrategyFactory.getInstance().creator(order.getStatus());
				OrderLog orderLog = strategy.getOrderLog(order);
				if (operator != null) {
					orderLog.setOprator(operator);
					orderLog.setOpratorCode(operatorCode);
				}
				orderLogList.add(orderLog);
			}
			logger.info("日志内容: "+orderLogList.toString());
			orderLogDao.insertInBatch(orderLogList);
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
}
