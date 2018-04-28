package com.spfood.oms.orderRetrieve.manager;



import com.spfood.kernel.dao.PageInfo;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.searchCriteria.OrderSearchCriteria;



/**
 * 
* @author huangcj
* @date 2017年1月16日
* Des:订单检索业务层接口
*
 */
public interface OrderRetrieveManager {

	
	 public PageInfo<Order> findOrderPageByParam(OrderSearchCriteria orderSearchCriteria);
	 
	 public Order findOrderDetailByOrderNo(String orderNo);
	 
	 public Long findOrderCountByParam(OrderSearchCriteria orderSearchCriteria);
	
}

	
