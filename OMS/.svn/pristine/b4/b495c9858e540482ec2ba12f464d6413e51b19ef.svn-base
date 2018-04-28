package com.spfood.oms.order.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.searchCriteria.OrderSearchCriteria;

public interface OrderDao extends BaseDao<Order> {

	/**
	 * 多个订单编号查询订单
	 * @param orderNos
	 * @return
	 */
	List<Order> selectByOrderNos(List<String> orderNos);
	
	/**
	 * 通过订单编号修改订单(如果有状态则必须大于当前状态)
	 * @param order
	 * @return
	 */
	int updateByOrderNoSelective(Order order);

	/**
	 * 取消订单(批量)
	 * @param orderList
	 */
	void updateBatchCancelOrder(List<Order> orderList);

	/**
	 * 订单编号查询订单详情
	 * @param orderNo
	 * @return
	 */
	Order selectOrderDetailByOrderNo(String orderNo);

	/**
	 * 单个取消订单
	 * @param orderNo
	 * @return
	 */
	int cancelOrderDao(String orderNo);

	/**
	 * 修改订单状态(状态必须大于当前状态)
	 * @param orderList
	 * @return
	 */
	int updateOrderStatus(List<Order> orderList);

	/**
	 * 查询订单详情(批量查询)
	 * @param orderNoList
	 * @return
	 */
	List<Order> selectOrderDetailListByOrderNo(List<String> orderNoList);
	
	/**
	 * 订单条件查询订单
	 * @param orderSearchCriteria
	 * @return
	 */
	Order selectOne(OrderSearchCriteria orderSearchCriteria);

	/**
	 * 通过订单编号查询订单和关联商品
	 * @param orderNo
	 * @return
	 */
	Order selectOrderAndCommodity(String orderNo);
	
	/**
	 * 通过订单编号查询订单和关联商品(批量)
	 * @param orderNoList
	 * @return
	 */
	List<Order> selectOrderAndCommodity(List<String> orderNoList);
	
}
