package com.spfood.oms.order.manager;

import java.util.List;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.oms.order.intf.domain.OrderExchange;
import com.spfood.oms.order.intf.domain.OrderExchangeCommodity;
import com.spfood.oms.order.intf.searchCriteria.OrderExchangeCriteria;
/**
 * 
 * @author Administrator
 *
 */
public interface OrderExchangeQueryManager {

	//oms换货展示
	public PageInfo<OrderExchange> queryOrderExchangeByParas(OrderExchangeCriteria orderExchangeCriteria);

	//用户中心列表展示
	public PageInfo<OrderExchange> getExchangeByCriteria(OrderExchangeCriteria orderExchangeCriteria);
	
	//查询多件商品
	public List<OrderExchangeCommodity> getOrderExchangeByOrderExCode(String[] exCodes);

	public List<OrderExchangeCommodity> getOrderExchangeByOrderExCodes(List<String> exCodes);
}
