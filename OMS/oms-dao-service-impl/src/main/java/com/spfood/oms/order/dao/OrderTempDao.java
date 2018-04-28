package com.spfood.oms.order.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.oms.order.intf.domain.OrderTemp;

public interface OrderTempDao extends BaseDao<OrderTemp>{

	/**
	 * 删除临时订单(批量)
	 * @param orderNoList
	 */
	void deleteByOrderNoInBatch(List<String> orderNoList);

	/**
	 * 通过订单编号修改临时表订单
	 * @param orderTemp
	 * @return
	 */
	int updateByOrderNoSelective(OrderTemp orderTemp);

	/**
	 * 修改临时表订单状态(批量)
	 * @param orderTempList
	 * @return
	 */
	int updateOrderStatus(List<OrderTemp> orderTempList);

}
