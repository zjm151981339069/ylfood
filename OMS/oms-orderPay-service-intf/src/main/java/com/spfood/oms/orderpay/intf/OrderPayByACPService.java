package com.spfood.oms.orderpay.intf;

import java.util.Map;

import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.orderpay.intf.domain.ACPPayBackgroundResult;
import com.spfood.oms.orderpay.intf.domain.ACPPayFrontResult;
import com.spfood.oms.orderpay.intf.domain.ACPPayQueryResult;
/**
 * 银联支付接口
 * @author Administrator
 *
 */
public interface OrderPayByACPService {

    /**
     * 网关支付    适用于PC网页  移动端 网页发起支付
     * @param order 支付订单
     * @param channelType  支付通道类型             电脑、平板使用  ：  pc  手机使用  ：   phone  
     * @return  用于跳转到银联的html页面
     */
    public String orderPayByACPGatewayPay(Order order,String channelType);

    /**
     * 银联支付验证通知   
     * 
     * 注意：银联的回调验证是Post提交方式
     * 
     * 
     * @param acpNotifyParms  验证通知获取请求参数
     * @return 验证是否成功 boolean
     */
    public ACPPayBackgroundResult orderPayByACPNotify(Map reqParam,String encoding);
    
    /**
     * 银联前台通知参数获取
     * @param acpReturnParms 前台跳转请求参数
     * @return  银联支付结果对象
     */
    public ACPPayFrontResult orderPayByACPReturn(Map acpReturnParms);
    
    /**
     * 订单交易查询：适用于支付，退款交易
     * @param orderId 订单编号
     * @param txnTime 订单发送时间
     * @param queryId 查询编号
     * @return 交易查询结果
     * 
     * 待查询交易的流水号。如果是订单发送时间、商户订单号不送时，该域必送
     */
    public ACPPayQueryResult orderPayByACPGateQuery(String orderId,String txnTime,String queryId);
    
    
}
