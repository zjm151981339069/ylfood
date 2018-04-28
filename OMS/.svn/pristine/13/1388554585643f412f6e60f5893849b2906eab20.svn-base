package com.spfood.oms.orderpay.intf.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.spfood.kernel.domain.DomainObject;

/**
 * 支付宝统一下单支付查询结果对象
 * @author Administrator
 *
 */
public class AlipayQueryResult implements DomainObject{
    private static final long serialVersionUID = 1L;
    private String tradeNo;//支付宝交易号
    private String outTradeNo;//商家订单号
    private String buyerLogonId;//买家支付宝账号 
    private String tradeStatus;//交易状态
    private BigDecimal totalAmount ;//交易的订单金额
    private BigDecimal receiptAmount;//实收金额
    private Date sendPayDate ;//本次交易打款给卖家的时间
    private String buyerUserId;//买家在支付宝的用户id 
    private String subCode;//业务返回码
    private String subMsg;//业务返回码描述
    public String getTradeNo() {
        return tradeNo;
    }
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }
    public String getOutTradeNo() {
        return outTradeNo;
    }
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
    public String getBuyerLogonId() {
        return buyerLogonId;
    }
    public void setBuyerLogonId(String buyerLogonId) {
        this.buyerLogonId = buyerLogonId;
    }
    public String getTradeStatus() {
        return tradeStatus;
    }
    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }
   
    public String getBuyerUserId() {
        return buyerUserId;
    }
    public void setBuyerUserId(String buyerUserId) {
        this.buyerUserId = buyerUserId;
    }
    public Date getSendPayDate() {
        return sendPayDate;
    }
    public void setSendPayDate(Date sendPayDate) {
        this.sendPayDate = sendPayDate;
    }
    
    
    public String getSubCode() {
        return subCode;
    }
    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }
    public String getSubMsg() {
        return subMsg;
    }
    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }
    
    
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    public BigDecimal getReceiptAmount() {
        return receiptAmount;
    }
    public void setReceiptAmount(BigDecimal receiptAmount) {
        this.receiptAmount = receiptAmount;
    }
    @Override
    public String toString() {
        return "AlipayQueryResult [tradeNo=" + tradeNo + ", outTradeNo="
                + outTradeNo + ", buyerLogonId=" + buyerLogonId
                + ", tradeStatus=" + tradeStatus + ", totalAmount="
                + totalAmount + ", receiptAmount=" + receiptAmount
                + ", sendPayDate=" + sendPayDate + ", buyerUserId="
                + buyerUserId + ", subCode=" + subCode + ", subMsg=" + subMsg
                + "]";
    }

    
    
    
    
}
