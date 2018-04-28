package com.spfood.oms.orderpay.intf.domain;

import com.spfood.kernel.domain.DomainObject;

/**
 * 银联支付交易查询的返回结果对象
 * @author Administrator
 *
 */
public class ACPPayQueryResult implements DomainObject{
    private static final long serialVersionUID = 1L;
    private String orderId ;//商户订单号
    private String currencyCode ;//交易币种
    private String txnAmt ;//交易金额
    private String txnTime ;//  订单发送时间
    private String payType ;//  支付方式
    private String accNo ;//账号
    private String payCardType ;//支付卡类型    
    private String queryId;//交易查询流水号
    private String traceNo;// 系统跟踪号
    private String traceTime;//交易传输时间
    private String settleDate;//清算日期
    private String settleCurrencyCode;//清算币种
    private String settleAmt;//清算金额
    private String origRespCode;//原交易应答码
    private String origRespMsg;//应答码
    private String respCode;//应答信息    
    private String respMsg;//应答信息
    private String txnType;//交易类型： 00：查询交易，01：消费，02：预授权，03：预授权完成，04：退货，05：圈存，11：代收，12：代付，13：账单支付，14：转账（保留），21：批量交易，22：批量查询    
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getQueryId() {
        return queryId;
    }
    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }
    public String getTraceNo() {
        return traceNo;
    }
    public void setTraceNo(String traceNo) {
        this.traceNo = traceNo;
    }
    public String getTraceTime() {
        return traceTime;
    }
    public void setTraceTime(String traceTime) {
        this.traceTime = traceTime;
    }
    public String getSettleDate() {
        return settleDate;
    }
    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }
    public String getSettleCurrencyCode() {
        return settleCurrencyCode;
    }
    public void setSettleCurrencyCode(String settleCurrencyCode) {
        this.settleCurrencyCode = settleCurrencyCode;
    }
    public String getSettleAmt() {
        return settleAmt;
    }
    public void setSettleAmt(String settleAmt) {
        this.settleAmt = settleAmt;
    }
    public String getRespMsg() {
        return respMsg;
    }
    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }
    public String getCurrencyCode() {
        return currencyCode;
    }
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    public String getTxnAmt() {
        return txnAmt;
    }
    public void setTxnAmt(String txnAmt) {
        this.txnAmt = txnAmt;
    }
    public String getTxnTime() {
        return txnTime;
    }
    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
    }
    public String getPayType() {
        return payType;
    }
    public void setPayType(String payType) {
        this.payType = payType;
    }
    public String getAccNo() {
        return accNo;
    }
    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }
    public String getPayCardType() {
        return payCardType;
    }
    public void setPayCardType(String payCardType) {
        this.payCardType = payCardType;
    }
    public String getOrigRespCode() {
        return origRespCode;
    }
    public void setOrigRespCode(String origRespCode) {
        this.origRespCode = origRespCode;
    }
    public String getOrigRespMsg() {
        return origRespMsg;
    }
    public void setOrigRespMsg(String origRespMsg) {
        this.origRespMsg = origRespMsg;
    }
    public String getRespCode() {
        return respCode;
    }
    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }
    public String getTxnType() {
        return txnType;
    }
    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }
    @Override
    public String toString() {
        return "ACPPayQueryResult [orderId=" + orderId + ", currencyCode="
                + currencyCode + ", txnAmt=" + txnAmt + ", txnTime=" + txnTime
                + ", payType=" + payType + ", accNo=" + accNo
                + ", payCardType=" + payCardType + ", queryId=" + queryId
                + ", traceNo=" + traceNo + ", traceTime=" + traceTime
                + ", settleDate=" + settleDate + ", settleCurrencyCode="
                + settleCurrencyCode + ", settleAmt=" + settleAmt
                + ", origRespCode=" + origRespCode + ", origRespMsg="
                + origRespMsg + ", respCode=" + respCode + ", respMsg="
                + respMsg + ", txnType=" + txnType + "]";
    }
    
    
    

}
