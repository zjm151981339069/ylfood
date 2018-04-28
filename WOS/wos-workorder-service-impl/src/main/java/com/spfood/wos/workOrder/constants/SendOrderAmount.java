package com.spfood.wos.workOrder.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SendOrderAmount {    
    //单线程模式下每次定时调度发送已支付订单到WMS的一个批次数量
    @Value("#{configProperties['order.oprate.singlethread.onetime.amount']}")
    public Integer orderAmount;   
    //单线程模式下的每次定时调度的最大订单提交数量  ，超过该数量就调用调用多线程模式下的订单信息同步
    @Value("#{configProperties['order.oprate.singlethread.totalmount']}")
    public Integer maxOrderAmount ;    
    //每个线程提交订单的数量
    @Value("#{configProperties['order.oprate.multithread.onethread.amount']}")
    public Integer threadOrderAmount;
    public Integer getOrderAmount() {
        return orderAmount;
    }
    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }
    public Integer getThreadOrderAmount() {
        return threadOrderAmount;
    }
    public void setThreadOrderAmount(Integer threadOrderAmount) {
        this.threadOrderAmount = threadOrderAmount;
    }
    public Integer getMaxOrderAmount() {
        return maxOrderAmount;
    }
    public void setMaxOrderAmount(Integer maxOrderAmount) {
        this.maxOrderAmount = maxOrderAmount;
    }
    

}

