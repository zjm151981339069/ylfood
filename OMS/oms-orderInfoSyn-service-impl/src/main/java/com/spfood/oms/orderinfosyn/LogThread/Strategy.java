package com.spfood.oms.orderinfosyn.LogThread;

import java.util.List;

import com.spfood.oms.order.dao.OrderDao;
import com.spfood.oms.order.dao.OrderLogDao;
import com.spfood.oms.order.intf.domain.Order;

/**
 * 定义策略活动的接口
 * @author Administrator
 *
 */
public interface Strategy {

	public boolean addOrderLog(Order order, OrderLogDao orderLogDao);
	
	public boolean addOrderLog(List<Order> orderList, OrderLogDao orderLogDao);
}
