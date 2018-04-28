package com.spfood.oms.orderinfosyn.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrderSynToNextSystemsOneTimeAmount {    
    @Value("#{configProperties['order.syn.oneTimeAmount']}")
    public Integer oneTimeAmount;

    public Integer getOneTimeAmount() {
        return oneTimeAmount;
    }

    public void setOneTimeAmount(Integer oneTimeAmount) {
        this.oneTimeAmount = oneTimeAmount;
    }
    
}
