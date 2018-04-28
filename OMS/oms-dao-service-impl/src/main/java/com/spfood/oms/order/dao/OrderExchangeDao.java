package com.spfood.oms.order.dao;
/**
 * 换货单Dao层
 */

import com.spfood.kernel.dao.BaseDao;
import com.spfood.oms.order.intf.domain.OrderExchange;
import com.spfood.oms.order.intf.searchCriteria.OrderExchangeCriteria;

public interface OrderExchangeDao extends BaseDao<OrderExchange> {

	/**
	 * 条件查询换货单总数
	 * @param orderExchangeCriteria
	 * @return
	 */
	long selectExchangeCount(OrderExchangeCriteria orderExchangeCriteria);

}
