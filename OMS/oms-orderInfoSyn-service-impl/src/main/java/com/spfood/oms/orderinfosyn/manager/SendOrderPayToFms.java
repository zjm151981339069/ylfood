package com.spfood.oms.orderinfosyn.manager;


public interface SendOrderPayToFms {
    
    /**
     * 定时发送财务数据给财务系统
     */
    public void sendOrderPaysToFms();
}
