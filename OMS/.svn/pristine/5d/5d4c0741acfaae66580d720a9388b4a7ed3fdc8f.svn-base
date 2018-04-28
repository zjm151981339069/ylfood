package com.spfood.oms.order.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.kernel.exception.BizException;
import com.spfood.kernel.utils.MessageHelper;
import com.spfood.oms.order.intf.OrderCreateService;
import com.spfood.oms.order.intf.domain.CreateOrderMessage;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderLog;
import com.spfood.oms.order.manager.OrderCreateManager;


/*
 * @Author:Isidore Han
 * @Date:2016/12
 */
@Service
public class OrderCreateServiceImpl implements OrderCreateService  {
	private static final Logger logger = Logger.getLogger(OrderCreateServiceImpl.class);

	@Autowired
	private OrderCreateManager orderCreateManager; 

	@Override
	public CreateOrderMessage addOrder(Order order) {
		try {
			logger.info(MessageHelper.getMessage("function") + "addOrder"
					+ MessageHelper.getMessage("parameter")  + order);
			CreateOrderMessage addOrder = orderCreateManager.addOrder(order);
			logger.info(MessageHelper.getMessage("return")+addOrder);
			return addOrder;
		} catch (Exception e) {
			logger.error(e);
			throw new BizException("oms.service.addOrder", e,order);
		}
	}
	
	@Override
	public CreateOrderMessage addExchangeOrder(Order order) {
		try {
			logger.info(MessageHelper.getMessage("function") + "addExchangeOrder"
					+ MessageHelper.getMessage("parameter")  + order);
			CreateOrderMessage addExchangeOrder = orderCreateManager.addExchangeOrder(order);
			logger.info(MessageHelper.getMessage("return") +addExchangeOrder);
			return addExchangeOrder;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.addExchangeOrder", e,order);
		}
	}
	
	@Override
	public boolean addOrderLog(OrderLog orderLog) {
		try {
			logger.info(MessageHelper.getMessage("function") + "addOrderLog"
					+ MessageHelper.getMessage("parameter")  + orderLog);
			boolean addOrderLog = orderCreateManager.addOrderLog(orderLog);
			logger.info(MessageHelper.getMessage("return") +addOrderLog);
			return addOrderLog;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.addOrderLog", e,orderLog);
		}
	}

	@Override
	public boolean addOrderLog(List<OrderLog> orderLogList) {
		try {
			logger.info(MessageHelper.getMessage("function") + "addOrderLog"
					+ MessageHelper.getMessage("parameter")  + orderLogList);
			boolean addOrderLog = orderCreateManager.addOrderLog(orderLogList);
			logger.info(MessageHelper.getMessage("return") +addOrderLog);
			return addOrderLog;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("oms.service.addOrderLog", e,orderLogList);
		}
	}
	
}
