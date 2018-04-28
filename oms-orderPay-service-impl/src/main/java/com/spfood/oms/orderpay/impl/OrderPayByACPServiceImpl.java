package com.spfood.oms.orderpay.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.orderpay.intf.OrderPayByACPService;
import com.spfood.oms.orderpay.intf.domain.ACPPayBackgroundResult;
import com.spfood.oms.orderpay.intf.domain.ACPPayFrontResult;
import com.spfood.oms.orderpay.intf.domain.ACPPayQueryResult;
import com.spfood.oms.orderpay.manage.ACPQueryManager;
import com.spfood.oms.orderpay.manage.OrderPayByACPManager;

@Service
public class OrderPayByACPServiceImpl implements OrderPayByACPService {
    @Autowired
    OrderPayByACPManager orderPayByACPManager;
    @Autowired
    ACPQueryManager aCPQueryManager;

    public String orderPayByACPGatewayPay(Order order,String channelType) {  
        if (order != null&&channelType!=null&&!"".equals(channelType)) {
            return orderPayByACPManager.GatewayPay(order,channelType);
        } else {
            return null;
        }

    }

    public ACPPayBackgroundResult orderPayByACPNotify(Map reqParam,String encoding) {
        if (reqParam != null&&encoding!=null&&!"".equals(encoding)) {
            return orderPayByACPManager.notify(reqParam,encoding);
        }
        return null;
    }

     public ACPPayFrontResult orderPayByACPReturn(Map acpReturnParms){
         if (acpReturnParms != null) {
             return orderPayByACPManager.orderPayByACPReturn(acpReturnParms);
         }
        return null;
    }

    @Override
    public ACPPayQueryResult orderPayByACPGateQuery(String orderId,
            String txnTime, String queryId) {
        if (queryId!=null&&!"".equals(queryId)) {
            return aCPQueryManager.orderPayByACPGateQuery(orderId, txnTime, queryId);
        }else if(orderId!=null&&!"".equals(orderId)&&txnTime!=null&&!"".equals(txnTime)){               

            return aCPQueryManager.orderPayByACPGateQuery(orderId, txnTime, queryId);
        }
        return null;
    }

    

}
