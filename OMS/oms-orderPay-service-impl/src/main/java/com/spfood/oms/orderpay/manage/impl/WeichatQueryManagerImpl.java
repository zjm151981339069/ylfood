package com.spfood.oms.orderpay.manage.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spfood.oms.order.intf.OrderManagerService;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderStatus;
import com.spfood.oms.orderinfosyn.intf.OrderStatusSynService;
import com.spfood.oms.orderpay.intf.domain.WeiChatPayQueryResult;
import com.spfood.oms.orderpay.intf.domain.WeiChatRefundQueryResult;
import com.spfood.oms.orderpay.manage.WeichatQueryManager;
import com.spfood.oms.orderpay.pay.weichatpay.GetWxOrderno;
import com.spfood.oms.orderpay.pay.weichatpay.RequestHandler;
import com.spfood.oms.orderpay.pay.weichatpay.TenpayUtil;
import com.spfood.oms.orderpay.pay.weichatpay.WeiChatConfig;
import com.spfood.oms.orderpay.util.DataChangeUtil;
@Component
public class WeichatQueryManagerImpl implements WeichatQueryManager{
    @Autowired
    private OrderManagerService orderManagerService;
    @Autowired
    private OrderStatusSynService orderStatusSynService;
    @Autowired
    public WeiChatConfig weiChatConfig;
    Logger logger = Logger.getLogger(WeichatQueryManagerImpl.class);
    @Override
    public WeiChatPayQueryResult orderPayQuery(String transactionId,
            String outTradeNo) {
        //提交查询订单编号选择方式：微信支付交易号，订单编号
        String orderNoType;
        //对应编号类型下的编号
        String orderNo;
        Map<String, String> queryId = new HashMap<String, String>();
        //优先使用微信支付交易号
        if(transactionId!=null&&!"".equals(transactionId)){
            orderNoType="transaction_id";
            orderNo=transactionId;
        }else {
            orderNoType="out_trade_no";
            orderNo=outTradeNo;
        }       
        // 公众开发号
        String appid = weiChatConfig.getAppId();
        // 设置随机字符串
        String currTime = TenpayUtil.getCurrTime();
        // 8位日期
        String strTime = currTime.substring(8, currTime.length());
        // 四位随机数
        String strRandom = TenpayUtil.buildRandom(4) + "";
        // 10位序列号,可以自行调整。
        String strReq = strTime + strRandom;
        // 商户号
        String mch_id = weiChatConfig.getPartner();
        // 随机数
        String nonce_str = strReq;        
        //签名方式
        String sign_type = weiChatConfig.getSignType();
        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("appid", appid);
        packageParams.put("mch_id", mch_id);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put(orderNoType, orderNo);
        RequestHandler reqHandler = new RequestHandler(null, null);
        reqHandler.init(appid, weiChatConfig.getAppsecret(),weiChatConfig.getPartnerKery());
        String sign = reqHandler.createSign(packageParams);
        // 支付查询请求的xml
        String xml = "<xml>" 
                + "<appid>" + appid + "</appid>" 
                + "<mch_id>"+ mch_id + "</mch_id>" 
                + "<nonce_str>" + nonce_str+ "</nonce_str>" 
                + "<sign>" + sign + "</sign>"
                + "<"+orderNoType+">"+ orderNo + "</"+orderNoType+">" 
                + "</xml>";
        //订单支付查询url
        String createOrderURL = weiChatConfig.getOrderQueryUrl();
        //支付查询结果Map
        Map payQuery=null;
        //支付查询结果
        WeiChatPayQueryResult weiChatPayQueryResult = new WeiChatPayQueryResult();
        try {
            payQuery = GetWxOrderno.getPayQuery(createOrderURL, xml);
        } catch (Exception e1) {
            e1.printStackTrace();
        } 
        //返回状态成功，获取查询的相关结果
        if(payQuery!=null){
            if("SUCCESS".equalsIgnoreCase(payQuery.get("return_code").toString())){
                weiChatPayQueryResult.setBankType((String) (payQuery.get("bank_type")));
                if(payQuery.get("cash_fee")!=null){
                    weiChatPayQueryResult.setCashFee(DataChangeUtil.pennyMoneyStringToBigDecimal((String) (payQuery.get("cash_fee"))));
                }              
                weiChatPayQueryResult.setOpenId((String) (payQuery.get("openid")));
                weiChatPayQueryResult.setTimeEnd((String) (payQuery.get("time_end")));
                if(payQuery.get("total_fee")!=null){
                    weiChatPayQueryResult.setTotalFee(DataChangeUtil.pennyMoneyStringToBigDecimal((String) (payQuery.get("total_fee"))));
                }            
                weiChatPayQueryResult.setTradeState((String) (payQuery.get("trade_state")));
                String status = (String) (payQuery.get("trade_state"));
                String queryOrderNo = (String) (payQuery.get("out_trade_no"));
                Order order = orderManagerService.getOrderByOrderNo(queryOrderNo);
                //查询的订单状态为SUCCESS
                if("SUCCESS".equals(status)&&order!=null){
                    //如果系统中的订单状态还没有改过状态为已支付，则更改订单的状态为已支付 
                    if(OrderStatus.HASPAIED.getValue()>order.getStatus()){
                        orderStatusSynService.tunrToHasPaied(queryOrderNo);
                    }
                }
                weiChatPayQueryResult.setTradeType((String) (payQuery.get("trade_type")));
                weiChatPayQueryResult.setReturnMsg(payQuery.get("return_msg").toString());
                weiChatPayQueryResult.setReturnCode(payQuery.get("return_code").toString());
                return weiChatPayQueryResult;
            }else {
                weiChatPayQueryResult.setReturnMsg(payQuery.get("return_msg").toString());
                weiChatPayQueryResult.setReturnCode(payQuery.get("return_code").toString());
                logger.info("=================**********************************************");
                return weiChatPayQueryResult;
            }                     
        }
        return weiChatPayQueryResult;
    }

    @Override
    public WeiChatRefundQueryResult WeiChatRefundQuery(String outRefundNo) {
        // 公众开发号
        String appid = weiChatConfig.getAppId();
        // 设置随机字符串
        String currTime = TenpayUtil.getCurrTime();
        // 8位日期
        String strTime = currTime.substring(8, currTime.length());
        // 四位随机数
        String strRandom = TenpayUtil.buildRandom(4) + "";
        // 10位序列号,可以自行调整。
        String strReq = strTime + strRandom;
        // 商户号
        String mch_id = weiChatConfig.getPartner();
        // 随机数
        String nonce_str = strReq;        
        //签名方式
        String sign_type = weiChatConfig.getSignType();
        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("appid", appid);
        packageParams.put("mch_id", mch_id);
        packageParams.put("nonce_str", nonce_str);        
        packageParams.put("out_refund_no", outRefundNo);        
        RequestHandler reqHandler = new RequestHandler(null, null);
        reqHandler.init(appid, weiChatConfig.getAppsecret(), weiChatConfig.getPartnerKery());
        String sign = reqHandler.createSign(packageParams);

        // 退款查询请求的xml
        String xml = "<xml>" 
                + "<appid>" + appid + "</appid>" 
                + "<mch_id>"+ mch_id + "</mch_id>" 
                + "<nonce_str>" + nonce_str+ "</nonce_str>" 
                + "<sign>" + sign + "</sign>"
                + "<sign_type>" + sign_type + "</sign_type>"
                + "<out_refund_no>"+ outRefundNo + "</out_refund_no>" 
                + "</xml>";
        //微信退款查询url
        String createOrderURL = weiChatConfig.getOrderRefundQueryUrl();
        //退款查询结果Map
        Map refundQuery=null;
        //支付查询结果
        WeiChatRefundQueryResult weiChatRefundQueryResult = new WeiChatRefundQueryResult();
        try {
            refundQuery = GetWxOrderno.getRefundQuery(createOrderURL, xml);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        //返回状态成功，获取查询的相关结果
        if(refundQuery!=null){
            weiChatRefundQueryResult.setRefundId((String)refundQuery.get("refund_id"));
            weiChatRefundQueryResult.setRefundCount((String)refundQuery.get("refund_count"));
            weiChatRefundQueryResult.setRefundFee((String)refundQuery.get("refund_fee_$n"));
            weiChatRefundQueryResult.setRefundStatus((String)refundQuery.get("refund_status_$n"));
            weiChatRefundQueryResult.setFeeType((String)refundQuery.get("fee_type"));
            weiChatRefundQueryResult.setRefundMoneySource((String)refundQuery.get("refund_account_$n"));
            weiChatRefundQueryResult.setRefundAccoutTime((String)refundQuery.get("refund_accout_$n"));
            weiChatRefundQueryResult.setRefundRecvAccout((String)refundQuery.get("refund_recv_accout_$n"));
            return weiChatRefundQueryResult;
        }else{
            return null;
        }
        
    }

}
