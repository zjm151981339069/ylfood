package com.spfood.oms.orderpay.manage.impl;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.orderpay.intf.domain.WeiChatRefundQueryResult;
import com.spfood.oms.orderpay.manage.WeiChatRefundManager;
import com.spfood.oms.orderpay.manage.WeichatQueryManager;
import com.spfood.oms.orderpay.pay.weichatpay.GetWxOrderno;
import com.spfood.oms.orderpay.pay.weichatpay.RequestHandler;
import com.spfood.oms.orderpay.pay.weichatpay.TenpayUtil;
import com.spfood.oms.orderpay.pay.weichatpay.WeiChatConfig;
import com.spfood.oms.orderpay.util.DataChangeUtil;
@Component
public class WeiChatRefundManagerImpl implements WeiChatRefundManager{
    @Autowired
    private WeichatQueryManager weichatQueryManager;
    @Autowired
    public WeiChatConfig weiChatConfig;
    @Override
    public WeiChatRefundQueryResult WeiChatRefund(String outRefundNo,
            String transactionId, String outTradeNo, Integer refundFee,
            String opUserId) {
        // 公众开发号
        String appid = weiChatConfig.getAppId();
        // 设置随机字符串
        String currTime = TenpayUtil.getCurrTime();
        // 8位日期
        String strTime = currTime.substring(8, currTime.length());
        // 四位随机数
        String strRandom = TenpayUtil.buildRandom(4) + "";
        // 10位序列号,可以自行调整。
        String strReq = strTime + strRandom;
        // 商户号
        String mch_id = weiChatConfig.getPartner();
        // 随机数
        String nonce_str = strReq;        
        //签名方式
        String sign_type = weiChatConfig.getSignType();
        //获得订单
        Order order = null;/*orderManagerService.getOrderByOrderno(outTradeNo);*/
        if(order==null){
            return null;
        }
        //订单支付总金额
        String totalFree = DataChangeUtil.bigDecimalMoneyToPennyMoney(order.getOrderAmount());
        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("appid", appid);
        packageParams.put("mch_id", mch_id);
        packageParams.put("nonce_str", nonce_str);        
        packageParams.put("out_trade_no", outTradeNo);        
        packageParams.put("transaction_id", transactionId);        
        packageParams.put("out_refund_no", outRefundNo);        
        packageParams.put("total_fee", totalFree);        
        packageParams.put("refund_fee", refundFee.toString());        
        packageParams.put("op_user_id", opUserId);               
        RequestHandler reqHandler = new RequestHandler(null, null);
        reqHandler.init(appid, weiChatConfig.getAppsecret(), weiChatConfig.getPartnerKery());
        String sign = reqHandler.createSign(packageParams);

        // 退款查询请求的xml
        String xml = "<xml>" +
                     "<appid>" + appid + "</appid>" + 
                     "<mch_id>" + mch_id + "</mch_id>" +
                     "<nonce_str>" + nonce_str + "</nonce_str>" +
                     "<sign>" + sign + "</sign>"+ 
                     "<transaction_id>" + transactionId + "</transaction_id>"+                   
                     "<sign_type>" + sign_type + "</sign_type>"+ 
                     "<out_refund_no>"+ outRefundNo + "</out_refund_no>"+
                     "<out_trade_no>"+ outTradeNo + "</out_trade_no>"+
                     "</xml>";
        //微信退款url
        String refundOrderURL = weiChatConfig.getRefundUrl();
        //退款结果Map
        Map refund=null;
        try {
            refund = GetWxOrderno.refund(refundOrderURL, xml);
        } catch (Exception e1) {
            e1.printStackTrace();
        } 
        if(refund!=null){
            //业务成功继续发起退款查询得到退款查询结果
            WeiChatRefundQueryResult weiChatRefundQuery = weichatQueryManager.WeiChatRefundQuery(outRefundNo);
            return weiChatRefundQuery;
        }else{
            return null;
        }
    }

}
