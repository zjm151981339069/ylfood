package com.spfood.oms.orderpay.manage;

import com.spfood.oms.orderpay.intf.domain.WeiChatRefundQueryResult;
/**
 * 微信支付申请退款
 * @author Administrator
 *
 */
public interface WeiChatRefundManager {
    /**
     * 微信支付申请退款
     * @param outRefundNo 退款订单编号
     * @param transactionId 支付交易号
     * @param outTradeNo 订单编号
     * @param refundFee 申请退款金额
     * @param opUserId 操作员账号，默认为商户号
     * 以上两个参数：支付交易号，订单编号二选一，优先使用微信支付交易号
     * @return 是否退款成功  
     * 
     */
    public WeiChatRefundQueryResult WeiChatRefund(String outRefundNo,String transactionId, String outTradeNo,Integer refundFee,String opUserId);
}
