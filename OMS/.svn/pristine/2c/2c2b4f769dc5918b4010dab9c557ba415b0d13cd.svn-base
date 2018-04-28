package com.spfood.oms.orderpay.manage;

import com.spfood.oms.orderpay.intf.domain.WeiChatPayQueryResult;
import com.spfood.oms.orderpay.intf.domain.WeiChatRefundQueryResult;
/**
 * 微信交易查询
 * @author Administrator
 *
 */
public interface WeichatQueryManager {
    /**
     * 订单交易查询
     * @param transaction_id   微信支付交易号
     * @param out_trade_no 订单编号
     * 以上两个参数二选一，优先使用微信支付交易号
     * 
     * @return 订单支付查询结果   
     */
    public WeiChatPayQueryResult orderPayQuery(String transactionId, String outTradeNo);
    
    /**
     * 微信支付申请退款查询
     * @param outRefundNo 退款订单编号
     * @return  退款查询结果对象
     */
    public WeiChatRefundQueryResult WeiChatRefundQuery(String outRefundNo);
}
