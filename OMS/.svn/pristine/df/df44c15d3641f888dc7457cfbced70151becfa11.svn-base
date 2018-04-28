package com.spfood.oms.orderinfosyn.manager;

import java.util.List;

import com.spfood.oms.order.intf.domain.OrderPay;

public interface OrderPaySynFms {
    /**
     * 支付信息到activemq
     * @param orderPay
     * @return 1为成功 ,-1为失败
     */
    public int orderPaySynFms(OrderPay orderPay);

    /**
     * 批量同步支付信息到activemq
     * @param orderPay
     * @return 1为成功 ,-1为失败
     */
	public int orderPaySynFms(List<OrderPay> orderPayList);
}
