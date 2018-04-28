package com.spfood.oms.orderinfosyn.manager;

import java.util.List;

import com.spfood.oms.order.intf.domain.Order;

public interface OrderSynToNextSystemsManager {
    /**
     * 发送订单到下级系统
     * @param orders订单列表
     */
    public Boolean orderSynToNextSystems(List<Order> orders);
    /**
     * 发送订单到下级系统
     * @param order 订单
     */
    public Boolean orderSynToNextSystems(Order order);
    /**
     * 定时发送订单信息到下级系统
     */
    public void quartzOrderSynToNextSystems();
}
