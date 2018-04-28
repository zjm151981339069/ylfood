package com.spfood.oms.orderpay.intf.domain;

import java.math.BigDecimal;

import com.spfood.kernel.domain.DomainObject;

/**
 * 微信支付交易查询返回结果
 * 
 * @author Administrator
 *
 */
public class WeiChatPayQueryResult implements DomainObject{
    private static final long serialVersionUID = 1L;
    private String openId; // 公众账号ID
    private String tradeType; // 支付类型
    private String tradeState;// 交易状态
    private String bankType; // 付款银行
    private BigDecimal totalFee; // 订单总金额
    private String timeEnd; // 支付完成时间
    private BigDecimal cashFee;// 现金支付金额
    private String returnMsg ;//返回信息
    private String returnCode;//返回状态码

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public BigDecimal getCashFee() {
        return cashFee;
    }

    public void setCashFee(BigDecimal cashFee) {
        this.cashFee = cashFee;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    @Override
    public String toString() {
        return "WeiChatPayQueryResult [openId=" + openId + ", tradeType="
                + tradeType + ", tradeState=" + tradeState + ", bankType="
                + bankType + ", totalFee=" + totalFee + ", timeEnd=" + timeEnd
                + ", cashFee=" + cashFee + ", returnMsg=" + returnMsg
                + ", returnCode=" + returnCode + "]";
    }


}
