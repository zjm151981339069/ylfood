package com.spfood.oms.orderpay.pay.weichatpay;

public class Sign {
    public static String createSign(String appid,String mch_id,String device_info,String body,String nonce_str){
        String A="appid="+appid+"&body="+body+"&device_info="+device_info+"&mch_id="+mch_id+"&nonce_str="+nonce_str;
        String SignTemp=A+"&key=192006250b4c09247ec02edce69f6a2d";
        return null;       
    }
}
