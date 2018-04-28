package com.spfood.oms.orderpay.intf.domain;

import com.spfood.kernel.domain.DomainObject;

/**
 * 微信支付退款订单查询返回结果
 * @author Administrator
 *
 */
public class WeiChatRefundQueryResult implements DomainObject{
    private static final long serialVersionUID = 1L;
    
    private String refundStatus;//退款状态：SUCCESS—退款成功，FAIL—退款失败，PROCESSING—退款处理中
                                //CHANGE—转入代发，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，资金回流到商户的现金帐号，需要商户人工干预，通过线下或者财付通转账的方式进行退款。
    private String refundFee;//退款金额   
    private String refundId;//退款单号
    private String refundCount;//当前退款单在 原交易订单的的第几个退款单
    private String refundRecvAccout;//退款入账账户
    private String refundAccoutTime;//退款成功时间 
    private String refundMoneySource;//退款资金来源
    private String feeType;//订单金额货币类型
    
    public String getRefundStatus() {
        return refundStatus;
    }
    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }
    public String getRefundFee() {
        return refundFee;
    }
    public void setRefundFee(String refundFee) {
        this.refundFee = refundFee;
    }
    public String getRefundCount() {
        return refundCount;
    }
    public void setRefundCount(String refundCount) {
        this.refundCount = refundCount;
    }
    public String getRefundId() {
        return refundId;
    }
    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }
    public String getRefundRecvAccout() {
        return refundRecvAccout;
    }
    public void setRefundRecvAccout(String refundRecvAccout) {
        this.refundRecvAccout = refundRecvAccout;
    }
    public String getRefundAccoutTime() {
        return refundAccoutTime;
    }
    public void setRefundAccoutTime(String refundAccoutTime) {
        this.refundAccoutTime = refundAccoutTime;
    }
    public String getRefundMoneySource() {
        return refundMoneySource;
    }
    public void setRefundMoneySource(String refundMoneySource) {
        this.refundMoneySource = refundMoneySource;
    }
    public String getFeeType() {
        return feeType;
    }
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }
    
    
    
    
    
}
