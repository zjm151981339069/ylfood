package com.spfood.oms.orderRetrieve.impl;





import org.springframework.beans.factory.annotation.Autowired;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.kernel.exception.BizException;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.searchCriteria.OrderSearchCriteria;
import com.spfood.oms.orderRetrieve.manager.OrderRetrieveManager;
import com.spfood.oms.orderRetrieve.intf.OrderRetrieveService;


/**
 * 
* @author huangcj
* @date 2017年1月16日
* Des:订单检索服务接口实现类
*
 */
public class OrderRetrieveServiceImpl implements OrderRetrieveService  {
	
	@Autowired
	private OrderRetrieveManager orderRetrieveManager;

	@Override
	public PageInfo<Order> getOrderPageByParam(OrderSearchCriteria orderSearchCriteria) {
		try {
			return orderRetrieveManager.findOrderPageByParam(orderSearchCriteria);
		} catch (Exception e) {
			throw new BizException("oms.service.getOrderPageByParam", e, orderSearchCriteria);
		}
		
	}

	@Override
	public Order getOrderDetailByOrderNo(String orderNo) {
		
		try {
			return orderRetrieveManager.findOrderDetailByOrderNo(orderNo);
		} catch (Exception e) {
			throw new BizException("oms.service.getOrderDetailByOrderNo", e.getCause(),orderNo);
		}
		
	}

	@Override
	public Long getOrderCountByParam(OrderSearchCriteria orderSearchCriteria) {
		
		try {
			return orderRetrieveManager.findOrderCountByParam(orderSearchCriteria);
		} catch (Exception e) {
			throw new BizException("oms.service.getOrderDetailByOrderNo", e.getCause(),orderSearchCriteria);
		}
		
	}

	
	 
   
}
