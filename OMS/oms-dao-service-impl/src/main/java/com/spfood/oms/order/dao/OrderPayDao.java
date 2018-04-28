package com.spfood.oms.order.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.oms.order.intf.domain.OrderPay;

public interface OrderPayDao extends BaseDao<OrderPay> {

	/**
	 * 批量查询订单支付
	 * @param orderNos
	 * @return
	 */
	List<OrderPay> selectByOrderNos(List<String> orderNos);

	/**
	 * 通过订单编号修改订单支付
	 * @param orderPay
	 * @return
	 */
	int updateByOrderNoSelective(OrderPay orderPay);

	/**
	 * 修改订单支付表为已支付(批量)
	 * @param orderPayList
	 * @return
	 */
	int updateOrderPayIsPay(List<OrderPay> orderPayList);

	/**
	 * 修改订单支付表为已发送FMS(批量)
	 * @param orderPayList
	 */
	void updateBatchIsSendFms(List<OrderPay> orderPayList);

	/**
	 * 查询要同步到FMS的订单支付信息(查询订单表中订单状态为3的数据) z20170329
	 */
	List<OrderPay> getUnSynOrderPayInfo();

}
