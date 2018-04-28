package com.spfood.oms.orderpay.intf;

import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.orderpay.intf.domain.WeiChatAppPay;
import com.spfood.oms.orderpay.intf.domain.WeiChatPay;
import com.spfood.oms.orderpay.intf.domain.WeiChatPayNotifyResults;
import com.spfood.oms.orderpay.intf.domain.WeiChatPayQueryResult;
import com.spfood.oms.orderpay.intf.domain.WeiChatQRPayResults;
/**
 * 微信支付接口
 * @author Administrator
 *
 */
public interface OrderPayByWeiChatService {
    
    /**
     * 微信二维码支付, 顾客扫描给定的二维码实现支付        
     * 
     * 注意：
     *     二维码图片支付的有效时间为2小时，逾期请重新调用该接口生成二维码图片
     * 
     * @param orders
     * @return 二维码图片URL
     */
    public WeiChatQRPayResults orderPayByWeiChatQRPay(Order order);
    
    /**
     * 微信H5页面调用支付
     * @param remoteAddrIP  远程使用者IP
     * @param order  支付订单
     * @param authCode 微信授权的授权码
     * @param useId  用户Id
     * @return H5页面调用JS支付需要的参数
     */
    public WeiChatPay orderPayByWeiChatWebPay(String remoteAddrIP,Order order,String authCode,String useId);   

    /**
     * 支付验证
     * @param weiChatPayNotifyParms  支付验证请求参数
     * @return  支付验证结果
     */
    public WeiChatPayNotifyResults weiChatPayNotify(String weiChatPayNotifyParms);
    
    /**
     * 微信支付交易查询
     * @param transactionId  交易号
     * @param outTradeNo 商家系统订单号
     * @return
     */
    public WeiChatPayQueryResult orderPayQuery(String transactionId,
            String outTradeNo);
    /**
     * 微信APP支付
     * @param remoteAddr  远程用户Ip
     * @param order 支付订单
     * @return
     */
    public WeiChatAppPay APPPay(String remoteAddr,Order order);
   
}
