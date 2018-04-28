package com.spfood.oms.orderpay.intf.domain;

import com.spfood.kernel.domain.DomainObject;
/**
 * 微信APP支付     调用微信支付的所需参数对象
 * @author Administrator
 *
 */
public class WeiChatAppPay implements DomainObject{
    private static final long serialVersionUID = 1L;
    //应用Id
    private String appid;
    //商户号
    private String partnerId;
    //预支付交易会话ID
    private String prepayId;
    //时间戳
    private String timeStamp;
    //随机字符串
    private String nonceStr;
    //扩展字段
    private String packages;
    //最终的签名
    private String finalSign;
    
    public WeiChatAppPay() {
        super();
    }
    public WeiChatAppPay(String appid, String partnerId, String prepayId,
            String timeStamp, String nonceStr, String packages, String finalSign) {
        super();
        this.appid = appid;
        this.partnerId = partnerId;
        this.prepayId = prepayId;
        this.timeStamp = timeStamp;
        this.nonceStr = nonceStr;
        this.packages = packages;
        this.finalSign = finalSign;
    }
    public String getAppid() {
        return appid;
    }
    public void setAppid(String appid) {
        this.appid = appid;
    }
    public String getPartnerId() {
        return partnerId;
    }
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
    public String getPrepayId() {
        return prepayId;
    }
    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }
    public String getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
    public String getNonceStr() {
        return nonceStr;
    }
    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }
    public String getPackages() {
        return packages;
    }
    public void setPackages(String packages) {
        this.packages = packages;
    }
    public String getFinalSign() {
        return finalSign;
    }
    public void setFinalSign(String finalSign) {
        this.finalSign = finalSign;
    }
    @Override
    public String toString() {
        return "WeiChatAppPay [appid=" + appid + ", partnerId=" + partnerId
                + ", prepayId=" + prepayId + ", timeStamp=" + timeStamp
                + ", nonceStr=" + nonceStr + ", packages=" + packages
                + ", finalSign=" + finalSign + "]";
    }
    

  
}
