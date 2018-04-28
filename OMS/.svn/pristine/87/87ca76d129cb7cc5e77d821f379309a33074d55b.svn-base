package com.spfood.oms.orderpay.manage;


import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.orderpay.intf.domain.WeiChatAppPay;
import com.spfood.oms.orderpay.intf.domain.WeiChatPay;
import com.spfood.oms.orderpay.intf.domain.WeiChatPayNotifyResults;
import com.spfood.oms.orderpay.intf.domain.WeiChatQRPayResults;

public interface OrderPayByWeiChatManager {
    //商户已有H5商城网站，用户通过消息或扫描二维码在微信内打开网页时，可以调用微信支付完成下单购买的流程。
    public WeiChatPay h5Pay(String remoteAddr,Order order,String authCode,String useId);
    
    public WeiChatQRPayResults QRPay(Order order);
    

    public WeiChatPayNotifyResults notify(String weiChatPayNotifyParms);
    //App支付
    public WeiChatAppPay APPPay(String remoteAddr,Order order);
}
