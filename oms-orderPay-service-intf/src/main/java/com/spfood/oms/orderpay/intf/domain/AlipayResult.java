package com.spfood.oms.orderpay.intf.domain;


import com.spfood.kernel.domain.DomainObject;

/**
 * 支付宝支付前台返回接口回传对象
 * @author Administrator
 *
 */
public class AlipayResult implements DomainObject{
    private static final long serialVersionUID = 1L;
    //订单编号
    private String outTradeNo;
    //交易号
    private String tradeNo;
    //订单支付状态
    private String tradeStatus;
    //通知时间
    private String date;
    //通知校验ID
    private String notifyId;
    //买家支付宝用户号
    private String buyUserId;
    //支付宝账号
    private String alipayId;
    //支付金额
    private String totalAmount;
    public String getOutTradeNo() {
        return outTradeNo;
    }
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
    public String getTradeNo() {
        return tradeNo;
    }
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }
    public String getTradeStatus() {
        return tradeStatus;
    }
    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }
    public String getNotifyId() {
        return notifyId;
    }
    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }
    public String getBuyUserId() {
        return buyUserId;
    }
    public void setBuyUserId(String buyUserId) {
        this.buyUserId = buyUserId;
    }
    public String getAlipayId() {
        return alipayId;
    }
    public void setAlipayId(String alipayId) {
        this.alipayId = alipayId;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
    @Override
    public String toString() {
        return "AlipayResult [outTradeNo=" + outTradeNo + ", tradeNo="
                + tradeNo + ", tradeStatus=" + tradeStatus + ", date=" + date
                + ", notifyId=" + notifyId + ", buyUserId=" + buyUserId
                + ", alipayId=" + alipayId + ", totalAmount=" + totalAmount
                + "]";
    }
    
    
    
    
    
    
}
