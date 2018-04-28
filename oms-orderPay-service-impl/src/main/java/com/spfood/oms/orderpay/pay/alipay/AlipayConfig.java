package com.spfood.oms.orderpay.pay.alipay;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class AlipayConfig {
    // 私匙
    @Value("#{configProperties['wap.alipay.rsa_private_key']}")
    public  String rsaPrivateKey ;
    // 接入地址  
    @Value("#{configProperties['wap.alipay.url']}")
    public  String url;  
    // APPID
    @Value("#{configProperties['wap.alipay.appid']}")
    public  String appid ;  
    // 编码方式
    @Value("#{configProperties['wap.alipay.charset']}")
    public  String charset ;  
    //签名方式 
    @Value("#{configProperties['wap.alipay.sign_type']}")
    public  String signType;   
    // 数据传输格式
    @Value("#{configProperties['wap.alipay.format']}")
    public  String format;  
    // 支付宝的公匙
    @Value("#{configProperties['wap.alipay.alipay_public_key']}")
    public  String alipayPublicKey ;     
    //回调通知地址
    @Value("#{configProperties['wap.alipay.notify.url']}")
    public  String alipayNotifyUrl ; 
    //支付成功前台显示地址
    @Value("#{configProperties['wap.alipay.return.url']}")
    public  String alipayReturnUrl ; 
    //错误页面
    @Value("#{configProperties['wap.alipay.error.page']}")
    public  String errorPage ;
    public String getRsaPrivateKey() {
        return rsaPrivateKey;
    }
    public void setRsaPrivateKey(String rsaPrivateKey) {
        this.rsaPrivateKey = rsaPrivateKey;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getAppid() {
        return appid;
    }
    public void setAppid(String appid) {
        this.appid = appid;
    }
    public String getCharset() {
        return charset;
    }
    public void setCharset(String charset) {
        this.charset = charset;
    }
    public String getSignType() {
        return signType;
    }
    public void setSignType(String signType) {
        this.signType = signType;
    }
    public String getFormat() {
        return format;
    }
    public void setFormat(String format) {
        this.format = format;
    }
    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }
    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }
    public String getAlipayNotifyUrl() {
        return alipayNotifyUrl;
    }
    public void setAlipayNotifyUrl(String alipayNotifyUrl) {
        this.alipayNotifyUrl = alipayNotifyUrl;
    }
    public String getAlipayReturnUrl() {
        return alipayReturnUrl;
    }
    public void setAlipayReturnUrl(String alipayReturnUrl) {
        this.alipayReturnUrl = alipayReturnUrl;
    }
    public String getErrorPage() {
        return errorPage;
    }
    public void setErrorPage(String errorPage) {
        this.errorPage = errorPage;
    }
    
}
