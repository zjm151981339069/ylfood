package com.spfood.oms.orderpay.intf;

import java.awt.image.BufferedImage;
import java.util.Map;

import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.orderpay.intf.domain.AlipayQueryResult;
import com.spfood.oms.orderpay.intf.domain.AlipayResult;
/**
 * 支付宝支付接口
 * @author Administrator
 *
 */
public interface OrderPayByAliPayService {

    /**
     * 使用支付宝手机网页支付  网站支付，        客户手机内置了支付宝客户端则先请求打开支付宝客户端，若打开失败调用支付宝的H5页面完成支付
     * @param order 支付订单
     * @return 支付宝支付请求页面
     */
    public String orderPayByAliPayYdAndPc_Pay(Order order);  

    /**
     * 二位码支付  
     * @param order 支付订单
     * @return 二维码支付地址
     */
    public String orderPayByAliPayQRCodePay(Order order);
    
    /**
     * 支付回转页面获取    
     * @param alipayReturnParms  页面回转请求参数
     * @return 支付宝支付结果对象
     */
    public AlipayResult orderPayByAlipayReturn(Map alipayReturnParms);
    
    /**
     * 支付验证通知
     * @param alipayNotifyParms  支付验证请求通知
     * @return  验证是否通过
     */
    public AlipayResult orderPayByAlipayNotify(Map alipayNotifyParms);
    
    /**
     * 支付宝支付查询
     * @param outTradeNo 商家系统订单号
     * @param tradeNo 支付宝交易号
     * @return 支付交易结果    
     * 
     * out_trade_no和trade_no不能同时为null
     */
    public AlipayQueryResult orderPayQuery(String outTradeNo, String tradeNo);
    
  
}
