package com.spfood.oms.orderpay.intf.domain;

import com.spfood.kernel.domain.DomainObject;
/**
 * 微信支付二维码请求返回结果
 * @author Administrator
 *
 */
public class WeiChatQRPayResults implements DomainObject{
    private static final long serialVersionUID = 1L;
    //二维码支付url
    private String payUrl; //支付请求异常该url为空
    //错误编码
    private String errCode; //  "-1" 系统中不存在该订单编号    "0" 该订单已取消     "1" 其他错误（支付微信系统中存在该订单编号或支付金额参数异常）      "2" 该订单已支付   
    
    public String getPayUrl() {
        return payUrl;
    }
    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }
    public String getErrCode() {
        return errCode;
    }
    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
    @Override
    public String toString() {
        return "WeiChatQRPayResults [payUrl=" + payUrl + ", errCode=" + errCode
                + "]";
    }
    
    
}
