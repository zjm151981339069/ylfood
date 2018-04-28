package com.spfood.oms.orderinfosyn.manager;

import java.util.List;

import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderBill;
import com.spfood.oms.order.intf.domain.OrderPay;

public interface SendOrderMessageQueueManager {

    void sendOrderImformToTS(List<Order> orders);

    void sendOrderImformToFMS(List<Order> orders);

    void sendOrderImformToWOS(List<Order> orders);

    public boolean sendOrderPayInfoToFMS(List<OrderPay> orderPays);
    
    public boolean sendOrderBillToFMS(OrderBill orderBill); 
}
