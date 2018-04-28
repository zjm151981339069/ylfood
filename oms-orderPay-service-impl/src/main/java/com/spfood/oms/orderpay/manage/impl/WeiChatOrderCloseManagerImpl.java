package com.spfood.oms.orderpay.manage.impl;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spfood.oms.orderpay.intf.domain.WeiChatProcessMessage;
import com.spfood.oms.orderpay.manage.WeiChatOrderCloseManager;
import com.spfood.oms.orderpay.pay.weichatpay.GetWxOrderno;
import com.spfood.oms.orderpay.pay.weichatpay.RequestHandler;
import com.spfood.oms.orderpay.pay.weichatpay.TenpayUtil;
import com.spfood.oms.orderpay.pay.weichatpay.WeiChatConfig;
@Component
public class WeiChatOrderCloseManagerImpl implements WeiChatOrderCloseManager{
    @Autowired
    public WeiChatConfig weiChatConfig;

    @Override
    public WeiChatProcessMessage weiChatOrderClose(String outTradeNo) {
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
        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("appid", appid);
        packageParams.put("mch_id", mch_id);
        packageParams.put("nonce_str", nonce_str);        
        packageParams.put("out_trade_no", outTradeNo);                   
        RequestHandler reqHandler = new RequestHandler(null, null);
        reqHandler.init(appid, weiChatConfig.getAppsecret(), weiChatConfig.getPartnerKery());
        String sign = reqHandler.createSign(packageParams);

        // 订单关闭请求的xml
        String xml = "<xml>" +
                     "<appid>" + appid + "</appid>" + 
                     "<mch_id>" + mch_id + "</mch_id>" +
                     "<nonce_str>" + nonce_str + "</nonce_str>" +
                     "<sign>" + sign + "</sign>"+                
                     "<sign_type>" + sign_type + "</sign_type>"+ 
                     "<out_trade_no>"+ outTradeNo + "</out_trade_no>"+
                     "</xml>";
        //微信订单关闭url
        String orderCloseURL = weiChatConfig.getOrderCloseUrl();
        //退款结果Map
        Map close=null;
        try {
            close = GetWxOrderno.orderClose(orderCloseURL, xml);
        } catch (Exception e1) {
            e1.printStackTrace();
        } 
        WeiChatProcessMessage weiChatProcessMessage = new WeiChatProcessMessage();
        weiChatProcessMessage.setReturnCode((String)close.get("result_code"));
        weiChatProcessMessage.setReturnMsg((String)close.get("return_msg"));
        if(close!=null){
            //如果处理结果成功就设置为true
            if("SUCCESS".equals(close.get("result_code"))){
                weiChatProcessMessage.setIsSuccess(true);
            }
        }else {
            weiChatProcessMessage.setIsSuccess(false);
        }

        return weiChatProcessMessage;
    }

}
