package com.spfood.oms.orderinfosyn.LogThread;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.spfood.oms.order.dao.OrderLogDao;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderLog;
/**
 * 废弃日志
 * @author Administrator
 *
 */
public class AddThrowAway implements Strategy {

	private static final Logger logger = Logger.getLogger(AddHasPaiedLog.class);
	@Override
	public boolean addOrderLog(Order order,OrderLogDao orderLogDao) {
		System.out.println(order);
		OrderLog orderLog = new OrderLog();
		orderLog.setOrderNo(order.getOrderNo());
		orderLog.setOprator(order.getCustomer());
		orderLog.setOpratorCode(order.getCustomerCode());
		orderLog.setOprTime(new Date());
		orderLog.setOprContent("订单已废弃");
		System.out.println(orderLog);
		try {
			orderLogDao.insert(orderLog);
			logger.info("订单日志信息："+ orderLog.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
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
			orderLog.setOprContent("订单已废弃");
			logList.add(orderLog);
		}
		orderLogDao.insertInBatch(logList);
		logger.info("生成付款日志表时间:" + "   " + sdf.format(new Date()) + "   当前线程名："
				+ Thread.currentThread().getName());
		logger.info("订单日志信息：" + logList.toString());
		return true;
	}

}
