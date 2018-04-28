package com.spfood.oms.orderpay.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.orderpay.intf.OrderPayByWeiChatService;
import com.spfood.oms.orderpay.intf.domain.WeiChatAppPay;
import com.spfood.oms.orderpay.intf.domain.WeiChatPay;
import com.spfood.oms.orderpay.intf.domain.WeiChatPayNotifyResults;
import com.spfood.oms.orderpay.intf.domain.WeiChatPayQueryResult;
import com.spfood.oms.orderpay.intf.domain.WeiChatQRPayResults;
import com.spfood.oms.orderpay.manage.OrderPayByWeiChatManager;
import com.spfood.oms.orderpay.manage.WeichatQueryManager;

@Service
public class OrderPayByWeiChatServiceImpl implements OrderPayByWeiChatService {
    @Autowired
    OrderPayByWeiChatManager orderPayByWeiChatManager;
    @Autowired
    WeichatQueryManager weeichatQueryManager;

    public WeiChatPay orderPayByWeiChatWebPay(String remoteAddrIP,Order order,String authCode,String useId) {
        if (order!=null) {
            return orderPayByWeiChatManager.h5Pay(remoteAddrIP,order,authCode,useId); 
        }
        return null;
    }

    public WeiChatPayNotifyResults weiChatPayNotify(String weiChatPayNotifyParms) {
        if (weiChatPayNotifyParms != null ) {
            return orderPayByWeiChatManager.notify(weiChatPayNotifyParms);
        }
        return null;
    }

    public WeiChatQRPayResults orderPayByWeiChatQRPay(Order order) {
            return orderPayByWeiChatManager.QRPay(order);
    }

    @Override
    public WeiChatPayQueryResult orderPayQuery(String transactionId,
            String outTradeNo) {
        if((transactionId!=null&&!"".equals(transactionId))||(outTradeNo!=null&&!"".equals(outTradeNo))){
            return weeichatQueryManager.orderPayQuery(transactionId, outTradeNo);
        }
        return null;
    }

    @Override
    public WeiChatAppPay APPPay(String remoteAddr, Order order) {
        if(remoteAddr!=null&&!"".equals(remoteAddr)&&order!=null){
            return orderPayByWeiChatManager.APPPay(remoteAddr, order);
        }
        return null;
    }

}
