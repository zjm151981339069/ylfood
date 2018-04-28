package com.spfood.oms.orderpay.manage;

import java.util.Map;

import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.orderpay.intf.domain.ACPPayBackgroundResult;
import com.spfood.oms.orderpay.intf.domain.ACPPayFrontResult;
import com.spfood.oms.orderpay.intf.domain.ACPPayQueryResult;

public interface OrderPayByACPManager {
    public String GatewayPay(Order order,String channelType);
    
    public ACPPayBackgroundResult notify(Map reqParam,String encoding);
    
    public ACPPayFrontResult orderPayByACPReturn(Map acpReturnParms);
    
    //银联支付查询
    public ACPPayQueryResult orderPayByACPQuery(ACPPayBackgroundResult aCPPayBackgroundResult);
    //银联支付申请退款
    public void orderPayByACPRefund(ACPPayBackgroundResult aCPPayBackgroundResult);
    
}
