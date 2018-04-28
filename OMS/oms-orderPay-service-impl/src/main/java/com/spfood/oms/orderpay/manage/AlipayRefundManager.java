package com.spfood.oms.orderpay.manage;

import java.math.BigDecimal;

import com.spfood.oms.orderpay.intf.domain.AlipayProcessMessage;

public interface AlipayRefundManager {
    /**
     * 统一支付订单退款接口
     * @param out_trade_no 商家订单编号
     * @param trade_no 支付交易号
     * @param refund_amount 退款金额
     * @param out_request_no 退款请求号：   用于多次请求退款的唯一标识
     * @return 处理结果
     */
    public AlipayProcessMessage orderRefundByAlipay(String out_trade_no,String trade_no,BigDecimal refund_amount,String out_request_no);
}
