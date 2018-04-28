package com.spfood.oms.order.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.oms.order.intf.domain.OrderCommodity;

public interface OrderCommodityDao extends BaseDao<OrderCommodity> {

	List<OrderCommodity> selectByOrderNoList(List<String> orderNoList);
	
	public List<OrderCommodity> findOrderCommodityListByOrderNos(List<String> orderNos);
}
