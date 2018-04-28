package com.spfood.oms.orderpay.manage;

import com.spfood.oms.orderpay.intf.domain.AlipayQueryResult;

public interface AlipayQueryManager {
    /**
     * 支付宝统一支付下单订单支付查询
     * @param out_trade_no  商家订单编号
     * @param trade_no 支付宝交易号
     * @return  支付查询结果对象
     */
    public AlipayQueryResult orderPayQuery(String out_trade_no,String trade_no);
    /**
     * 查询退款交易
     * @param out_trade_no  商家系统订单
     * @param trade_no 支付交易订单号
     * @param out_request_no 退款交易号
     * @param refund_amount 退款金额
     * @return 查询退款订单结果
     */
    public Boolean orderRefundQuery(String out_trade_no,String trade_no,String out_request_no,String refund_amount );
}
