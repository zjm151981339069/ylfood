package com.spfood.oms.orderpay.pay.alipay;  

import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.StringUtils;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
@Component
public class AlipayClientFactory { 
    @Autowired
    private  AlipayConfig alipayConfig;
    @Autowired
    private  AlipayClient client;
    
    static Logger logger = Logger.getLogger(AlipayClientFactory.class);
    /** 
     * appAuthToken 
     * 如ISV代替商家调用当面付接口，需将商户授权后获取的app_auth_token带上；如商家申请当面付自己调用，则传null bizContent 
     * JSON格式 商户的请求参数 
     */  
    // 手机网页支付 网站支付  
    public String ydAndPc_Pay(Map<String, String> maps)  
            throws AlipayApiException {  
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();  
        String NotifyUrl = maps.get("NotifyUrl");  
        String ReturnUrl = maps.get("ReturnUrl");  
        // 后台回调  
        if (!StringUtils.isEmpty(NotifyUrl)) {  
            alipayRequest.setNotifyUrl(NotifyUrl);  
            // bizContent 中不需要 公共参数  
            maps.remove("NotifyUrl");  
        }  
        // 页面回调  
        if (!StringUtils.isEmpty(ReturnUrl)) {  
            alipayRequest.setReturnUrl(ReturnUrl);  
            // bizContent 中不需要 公共参数  
            maps.remove("ReturnUrl");  
        }  
        String bizCon = JSON.toJSONString(maps);  
        alipayRequest.setBizContent(bizCon);  
        String form = "";  
        try {  
            form = client.pageExecute(alipayRequest).getBody();  
        } catch (AlipayApiException e) {  
            form = "err";  
            e.printStackTrace();  
        } // 调用SDK生成表单  
        return form;  
    }  
    public AlipayClientFactory(AlipayConfig alipayConfig) {
        super();
        this.alipayConfig = alipayConfig;
    }
    //二维码支付
    public AlipayTradePrecreateResponse QRCodePay(Map<String, String> maps){
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();//创建API对应的req
        String NotifyUrl = maps.get("NotifyUrl");  
        String ReturnUrl = maps.get("ReturnUrl");  
        // 后台回调  
        if (!StringUtils.isEmpty(NotifyUrl)) {  
            request.setNotifyUrl(NotifyUrl);  
            // bizContent 中不需要 公共参数  
            maps.remove("NotifyUrl");  
        }  
        // 页面回调  
        if (!StringUtils.isEmpty(ReturnUrl)) {  
            request.setReturnUrl(ReturnUrl);  
            // bizContent 中不需要 公共参数  
            maps.remove("ReturnUrl");  
        }  
        //设置商业参数
        String bizCon = JSON.toJSONString(maps); 
        request.setBizContent(bizCon);          
        AlipayTradePrecreateResponse response = null;
        try {
            //执行二维码支付请求
            response = client.execute(request);
            logger.info("预支付请求的响应================"+response);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return response;
    }
    //订单退款
    public AlipayTradeRefundResponse TradeRefund(Map<String, String> maps){
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        //设置商业参数
        String bizCon = JSON.toJSONString(maps); 
        request.setBizContent(bizCon);    
        AlipayTradeRefundResponse response = null;
        try {
            response = client.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            logger.info("调用成功");
        }
        return response;
    }
    //订单交易关闭
    public AlipayTradeCloseResponse orderClose(Map<String, String> maps){
        AlipayTradeCloseRequest  request = new AlipayTradeCloseRequest ();
        //设置商业参数
        String bizCon = JSON.toJSONString(maps); 
        request.setBizContent(bizCon);    
        AlipayTradeCloseResponse  response = null;
        try {
            response = client.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            logger.info("调用成功");
        }
        return response;
    }
    //订单查询
    public  AlipayTradeQueryResponse  orderQuery(Map<String, String> maps){
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        //设置商业参数
        String bizCon = JSON.toJSONString(maps); 
        request.setBizContent(bizCon);    
        AlipayTradeQueryResponse  response = null;
        try {
            response = client.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            logger.info("调用成功");
        }
        return response;
    }
  //订单查询
    public AlipayTradeFastpayRefundQueryResponse orderRefundQuery(Map<String, String> maps){
        AlipayTradeFastpayRefundQueryRequest  request = new AlipayTradeFastpayRefundQueryRequest ();
        //设置商业参数
        String bizCon = JSON.toJSONString(maps); 
        request.setBizContent(bizCon);    
        AlipayTradeFastpayRefundQueryResponse response = null;
        try {
            response = client.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            logger.info("调用成功");
        }
        return response;
    }

}  