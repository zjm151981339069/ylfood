package com.spfood.oms.orderpay.intf.domain;

import com.spfood.kernel.domain.DomainObject;
/**
 * 微信后台验证接口传回的参数对象
 * @author Administrator
 *
 */
public class WeiChatPayNotifyResults implements DomainObject{
    private static final long serialVersionUID = 1L;
    //是否成功支付
    private Boolean isSuccessPay;
    //返回到微信服务器单的数据
    private String resXml;    
    //微信支付结果详细
    private WxPayResultDetail wxPayResultDetail;
    
    
    public WeiChatPayNotifyResults(Boolean isSuccessPay, String resXml,
            WxPayResultDetail wxPayResultDetail) {
        super();
        this.isSuccessPay = isSuccessPay;
        this.resXml = resXml;
        this.wxPayResultDetail = wxPayResultDetail;
    }
    public Boolean getIsSuccessPay() {
        return isSuccessPay;
    }
    public void setIsSuccessPay(Boolean isSuccessPay) {
        this.isSuccessPay = isSuccessPay;
    }
    public String getResXml() {
        return resXml;
    }
    public void setResXml(String resXml) {
        this.resXml = resXml;
    }
    public WxPayResultDetail getWxPayResultDetail() {
        return wxPayResultDetail;
    }
    public void setWxPayResultDetail(WxPayResultDetail wxPayResultDetail) {
        this.wxPayResultDetail = wxPayResultDetail;
    }    
  
   
    
    
    
}
