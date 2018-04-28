package com.spfood.oms.orderpay.intf.domain;

import com.spfood.kernel.domain.DomainObject;
/**
 * 微信H5 页面js调用微信支付的所需参数对象
 * @author Administrator
 *
 */
public class WeiChatPay implements DomainObject{
    private static final long serialVersionUID = 1L;
    //应用Id
    private String appid;
    //时间戳
    private String timestamp;
    //随机字符串
    private String nonceStr;
    //请求包
    private String packages;
    //最终的签名
    private String finalsign;
    //签名方式
    private String signType;
    
    
    public WeiChatPay() {
        super();
    }
    public WeiChatPay(String appid, String timestamp, String nonceStr,
            String packages, String finalsign,String signType) {
        super();
        this.appid = appid;
        this.timestamp = timestamp;
        this.nonceStr = nonceStr;
        this.packages = packages;
        this.finalsign = finalsign;
        this.signType = signType;
    }
    public String getAppid() {
        return appid;
    }
    public void setAppid(String appid) {
        this.appid = appid;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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
    public String getFinalsign() {
        return finalsign;
    }
    public void setFinalsign(String finalsign) {
        this.finalsign = finalsign;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public String getSignType() {
        return signType;
    }
    public void setSignType(String signType) {
        this.signType = signType;
    }
  
}
