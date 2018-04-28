package com.spfood.oms.order.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.searchCriteria.OrderSearchCriteria;

public interface OrderDao extends BaseDao<Order> {

	List<Order> selectByOrderNos(List<String> orderNos);

	int updateByOrderNoSelective(Order order);

	void updateBatchCancelOrder(List<Order> orderList);

	Order selectOrderDetailByOrderNo(String orderNo);

	int cancelOrderDao(String orderNo);

	int updateOrderStatus(List<Order> orderList);

	List<Order> selectOrderDetailListByOrderNo(List<String> orderNoList);
	
	Order selectOne(OrderSearchCriteria orderSearchCriteria);

	Order selectOrderAndCommodity(String orderNo);
	
}
