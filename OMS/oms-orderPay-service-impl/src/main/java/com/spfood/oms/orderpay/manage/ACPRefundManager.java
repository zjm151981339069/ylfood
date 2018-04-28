package com.spfood.oms.orderpay.manage;

import com.spfood.oms.orderpay.intf.domain.ACPPayBackgroundResult;

public interface ACPRefundManager {
    /**
     * 银联退款处理
     */
    public void orderPayByACPRefund(ACPPayBackgroundResult aCPPayBackgroundResult); 
}
