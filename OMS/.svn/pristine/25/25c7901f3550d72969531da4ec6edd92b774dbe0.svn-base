package com.spfood.oms.orderRetrieve.intf;



import com.spfood.kernel.dao.PageInfo;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.searchCriteria.OrderSearchCriteria;


 /**
  * 
 * @author huangcj
 * @date 2017年1月17日
 * Des:订单检索服务
 *
  */
public interface OrderRetrieveService {
	
	/**
	 * 分页查询订单
	 * @param OrderSearchCriteria
	 * @return PageInfo<Order>
	 */
	public PageInfo<Order> getOrderPageByParam(OrderSearchCriteria orderSearchCriteria);
	
	/**
	 * 通过订单号查询订单详情
	 * @param orderNo
	 * @return Order
	 */
	public Order getOrderDetailByOrderNo(String orderNo);
	
	/**
	 * 查询订单数量
	 * @param order
	 * @return orderCount
	 */
	public Long getOrderCountByParam(OrderSearchCriteria orderSearchCriteria);

}
