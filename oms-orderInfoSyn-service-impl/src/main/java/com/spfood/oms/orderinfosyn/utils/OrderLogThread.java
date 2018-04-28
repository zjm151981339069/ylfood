package com.spfood.oms.orderinfosyn.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.oms.order.intf.OrderCreateService;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderLog;
import com.spfood.oms.orderinfosyn.manager.OrderSynToNextSystemsManager;
import com.spfood.oms.orderinfosyn.manager.impl.OrderStatusSynManageImpl;
import com.spfood.oms.orderinfosyn.utils.OrderDefaultStatus;


/**
 * 执行订单完成的线程
 * @author Administrator
 *
 */
@Service
public class OrderLogThread{

	@Autowired
	private OrderCreateService orderCreateService;
	@Autowired
	private OrderSynToNextSystemsManager manager;
	private static final Logger logger = Logger.getLogger(OrderStatusSynManageImpl.class);
	
	public void addOrderLog(Order order,String oprContent){
		OrderLog orderLog = new OrderLog();
		orderLog.setOrderNo(order.getOrderNo());
		if (order.getStatus() != 1 && order.getStatus() != 2) {
			orderLog.setOprator("银犁专送");
			orderLog.setOpratorCode("YLWL8888");
		}else {
			orderLog.setOprator(order.getCustomer());  //操作人
			orderLog.setOpratorCode(order.getCustomerCode());//操作人编码
		}
		orderLog.setOrderNo(order.getOrderNo());
		orderLog.setOprContent(oprContent);
		orderLog.setOprTime(new Date());
		orderCreateService.addOrderLog(orderLog);
		logger.info("订单日志信息："+ orderLog.toString());
	}
	
	public void addOrderLog(List<Order> orderList, String oprContent) {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
		List<OrderLog> logList = new ArrayList<OrderLog>();
		for (Order order : orderList) {
			OrderLog orderLog = new OrderLog();
			if (order.getStatus() != OrderDefaultStatus.UNPAIED_STATUS.getValue()
					&& order.getStatus() != OrderDefaultStatus.HASPAIED_STATUS.getValue()) {
				orderLog.setOprator("银犁生鲜");
				orderLog.setOpratorCode("YLWL8888");
			} else {
				orderLog.setOprator(order.getCustomer()); // 操作人
				orderLog.setOpratorCode(order.getCustomerCode());// 操作人编码
			}
			orderLog.setOprContent(oprContent);
			orderLog.setOrderNo(order.getOrderNo());
			orderLog.setOprTime(new Date());
			logList.add(orderLog);
		}
		orderCreateService.addOrderLog(logList);
		logger.info("生成付款日志表时间:" + "   " + sdf.format(new Date()) + "   当前线程名："
				+ Thread.currentThread().getName());
		logger.info("订单日志信息：" + logList.toString());
	}

}
