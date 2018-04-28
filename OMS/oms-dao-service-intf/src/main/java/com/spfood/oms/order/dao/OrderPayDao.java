package com.spfood.oms.order.dao;

import java.util.List;

import com.spfood.kernel.dao.BaseDao;
import com.spfood.oms.order.intf.domain.OrderPay;

public interface OrderPayDao extends BaseDao<OrderPay> {

	List<OrderPay> selectByOrderNos(List<String> orderNos);

	int updateByOrderNoSelective(OrderPay orderPay);

	int updateOrderPayIsPay(List<OrderPay> orderPayList);

	void updateBatchIsSendFms(List<OrderPay> orderPayList);

}
