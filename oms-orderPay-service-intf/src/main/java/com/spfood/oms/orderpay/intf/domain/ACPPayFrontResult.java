package com.spfood.oms.orderpay.intf.domain;

import java.math.BigDecimal;

import com.spfood.kernel.domain.DomainObject;

/**
 * acp前台支付返回信息对象
 * @author Administrator
 *
 */
public class ACPPayFrontResult implements DomainObject{
    private static final long serialVersionUID = 1L;
    private String version; // 版本号
    private String encoding; // 编码方式
    private String signature; // 签名
    private String signMethod; // 签名方法
    private String txnType; // 交易类型
    private String txnSubType; // 交易子类
    private String bizType; // 产品类型
    private String accessType; // 接入类型
    private String merId; // 商户代码
    private String orderId; // 商户订单号
    private String txnTime; // 订单发送时间
    private String txnAmt; // 交易金额
    private String currencyCode; // 交易币种
    private String reqReserved; // 请求方保留域
    private String reserved; // 保留域
    private String queryId; // 交易查询流水号
    private String respCode; // 响应码
    private String respMsg; // 应答信息
    private String accNo; // 账号
    private String payCardType; // 支付卡类型
    private String payType; // 支付方式
    private String tn; // 银联订单号
    private String signPubKeyCert; // 签名公钥证书
    private Boolean isSuccessPay;  //是否支付成功
    private BigDecimal totalAmout; //订单金额
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Boolean getIsSuccessPay() {
        return isSuccessPay;
    }

    public void setIsSuccessPay(Boolean isSuccessPay) {
        this.isSuccessPay = isSuccessPay;
    }

    public BigDecimal getTotalAmout() {
        return totalAmout;
    }

    public void setTotalAmout(BigDecimal totalAmout) {
        this.totalAmout = totalAmout;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSignMethod() {
        return signMethod;
    }

    public void setSignMethod(String signMethod) {
        this.signMethod = signMethod;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public String getTxnSubType() {
        return txnSubType;
    }

    public void setTxnSubType(String txnSubType) {
        this.txnSubType = txnSubType;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
    }

    public String getTxnAmt() {
        return txnAmt;
    }

    public void setTxnAmt(String txnAmt) {
        this.txnAmt = txnAmt;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getReqReserved() {
        return reqReserved;
    }

    public void setReqReserved(String reqReserved) {
        this.reqReserved = reqReserved;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
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

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getTn() {
        return tn;
    }

    public void setTn(String tn) {
        this.tn = tn;
    }

    public String getSignPubKeyCert() {
        return signPubKeyCert;
    }

    public void setSignPubKeyCert(String signPubKeyCert) {
        this.signPubKeyCert = signPubKeyCert;
    }

    @Override
    public String toString() {
        return "ACPPayFrontResult [version=" + version + ", encoding="
                + encoding + ", signature=" + signature + ", signMethod="
                + signMethod + ", txnType=" + txnType + ", txnSubType="
                + txnSubType + ", bizType=" + bizType + ", accessType="
                + accessType + ", merId=" + merId + ", orderId=" + orderId
                + ", txnTime=" + txnTime + ", txnAmt=" + txnAmt
                + ", currencyCode=" + currencyCode + ", reqReserved="
                + reqReserved + ", reserved=" + reserved + ", queryId="
                + queryId + ", respCode=" + respCode + ", respMsg=" + respMsg
                + ", accNo=" + accNo + ", payCardType=" + payCardType
                + ", payType=" + payType + ", tn=" + tn + ", signPubKeyCert="
                + signPubKeyCert + ", isSuccessPay=" + isSuccessPay + "]";
    }

}
