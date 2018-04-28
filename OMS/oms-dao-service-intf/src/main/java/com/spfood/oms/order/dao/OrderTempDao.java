package com.spfood.oms.order.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.oms.order.intf.domain.OrderTemp;

public interface OrderTempDao extends BaseDao<OrderTemp>{

	void deleteByOrderNoInBatch(List<String> orderNoList);

	int updateByOrderNoSelective(OrderTemp orderTemp);

	int updateOrderStatus(List<OrderTemp> orderTempList);

}
