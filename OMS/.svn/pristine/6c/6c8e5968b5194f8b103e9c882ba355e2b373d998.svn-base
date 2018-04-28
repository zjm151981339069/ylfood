package com.spfood.oms.order.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.oms.order.intf.domain.OrderCommodity;

public interface OrderCommodityDao extends BaseDao<OrderCommodity> {

	/**
	 * 通过多个订单号查询订单对应的商品
	 * @param orderNoList
	 * @return
	 */
	List<OrderCommodity> selectByOrderNoList(List<String> orderNoList);

}
