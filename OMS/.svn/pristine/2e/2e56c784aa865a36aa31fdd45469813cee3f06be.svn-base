package com.spfood.oms.orderinfosyn.LogThread;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.spfood.oms.order.dao.OrderLogDao;
import com.spfood.oms.order.intf.OrderCreateService;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderLog;
/**
 * 待付款日志
 * @author Administrator
 *
 */
public class AddUnpaied implements Strategy {

	private static final Logger logger = Logger.getLogger(AddUnpaied.class);
	@Override
	public boolean addOrderLog(Order order,OrderLogDao orderLogDao) {
		OrderLog orderLog = new OrderLog();
		orderLog.setOrderNo(order.getOrderNo());
		orderLog.setOprator(order.getCustomer());
		orderLog.setOpratorCode(order.getCustomerCode());
		orderLog.setOprTime(new Date());
		orderLog.setOprContent("订单已生成，请尽快付款");
		
		logger.info("订单日志信息："+ orderLog.toString());
		orderLogDao.insert(orderLog);
		return true;
	}
	@Override
	public boolean addOrderLog(List<Order> orderList,OrderLogDao orderLogDao) {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
		List<OrderLog> logList = new ArrayList<OrderLog>();
		for (Order order : orderList) {
			OrderLog orderLog = new OrderLog();
			orderLog.setOrderNo(order.getOrderNo());
			orderLog.setOprator(order.getCustomer());
			orderLog.setOpratorCode(order.getCustomerCode());
			orderLog.setOprTime(new Date());
			orderLog.setOprContent("订单已生成，请尽快付款");
			logList.add(orderLog);
		}
		orderLogDao.insertInBatch(logList);
		logger.info("生成付款日志表时间:" + "   " + sdf.format(new Date()) + "   当前线程名："
				+ Thread.currentThread().getName());
		logger.info("订单日志信息：" + logList.toString());
		return true;
	}

}
