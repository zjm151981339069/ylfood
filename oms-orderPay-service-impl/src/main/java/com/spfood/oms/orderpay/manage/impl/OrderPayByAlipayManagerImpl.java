package com.spfood.oms.orderpay.manage.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.spfood.oms.order.intf.OrderManagerService;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderStatus;
import com.spfood.oms.orderinfosyn.intf.OrderStatusSynService;
import com.spfood.oms.orderpay.intf.domain.AlipayResult;
import com.spfood.oms.orderpay.manage.OrderPayByAlipayManager;
import com.spfood.oms.orderpay.pay.alipay.AlipayClientFactory;
import com.spfood.oms.orderpay.pay.alipay.AlipayConfig;
import com.spfood.oms.orderpay.util.DataChangeUtil;
@Component
public class OrderPayByAlipayManagerImpl implements OrderPayByAlipayManager{
    @Autowired
    private OrderManagerService orderManagerService;
    @Autowired
    private OrderStatusSynService orderStatusSynService;
    @Autowired
    public AlipayConfig alipayConfig;
    @Autowired
    private AlipayClientFactory alipayClientFactory;
    Logger logger = Logger.getLogger(OrderPayByAlipayManagerImpl.class);
    /**
     * 手机网站支付
     */
    public String orderPayByAlipay(Order order) {
        Map<String, String> maps = new HashMap<String, String>();  
        //转化支付的金额为两位小数点的浮点数
        BigDecimal orderamount = order.getOrderAmount();
        Float total_num=DataChangeUtil.bigDecimalToTwoPonintFloat(orderamount);
        maps.put("out_trade_no", order.getOrderNo());  
        maps.put("total_amount", total_num.toString());  
        maps.put("subject", "Iphone6 16G");  //后期要传入 
        maps.put("body", "银犁食品");  //后期要传入 
        maps.put("product_code", "QUICK_WAP_PAY");  
        //下面两个 参数的 KEY 不要乱写 要和工具类里面对应  
        maps.put("ReturnUrl", alipayConfig.getAlipayReturnUrl());  
        maps.put("NotifyUrl", alipayConfig.getAlipayNotifyUrl());  
        try {  
            String form = alipayClientFactory.ydAndPc_Pay(maps);  
            if (!form.equals("err")) {  
                return form;
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;
    }
    /**
     * 二维码支付,返回支付的二维码地址
     */
    public String QRCodePay(Order order) {
        //转化支付的金额为两位小数点的浮点数
        BigDecimal orderamount = order.getOrderAmount();
        Float total_num=DataChangeUtil.bigDecimalToTwoPonintFloat(orderamount);
        Map<String, String> maps = new HashMap<String, String>();  
        maps.put("out_trade_no", order.getOrderNo());  
        maps.put("total_amount", total_num.toString());  
        maps.put("subject", "Iphone6 16G");  //后期要传入 
        maps.put("body", "Iphone6 16G");  //后期要传入 
        // 下面两个回转和通过地址参数的 KEY 不要乱写 要和工具类里面对应  
        maps.put("ReturnUrl", alipayConfig.getAlipayReturnUrl());  
        maps.put("NotifyUrl", alipayConfig.getAlipayNotifyUrl());  
        AlipayTradePrecreateResponse qrCodePay=null;
        try {   
            qrCodePay = alipayClientFactory.QRCodePay(maps); 
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        String qrCodeUrl="";
        if(qrCodePay!=null){
            qrCodeUrl = qrCodePay.getQrCode();
        }
        return qrCodeUrl;       
    }
    /**
     * 前台跳转
     */
    public AlipayResult orderPayByAlipayReturn(Map alipayReturnParms) {
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = alipayReturnParms;
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");         
            params.put(name, valueStr);
        }          
        boolean signVerified=false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, alipayConfig.getAlipayPublicKey(), alipayConfig.getCharset(), alipayConfig.getSignType());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }  
        //验证成功
        if(signVerified){
            logger.info("*************前台跳转验证成功*************");
            String[] out_trade_no= (String[]) requestParams.get("out_trade_no");
            String[] trade_no = (String[]) requestParams.get("trade_no");
            String[] total_amount = (String[]) requestParams.get("total_amount");
            //获取支付宝的通知返回参数       
            AlipayResult alipayResult = new AlipayResult();
            alipayResult.setOutTradeNo(out_trade_no[0]);
            alipayResult.setTradeNo(trade_no[0]);
            alipayResult.setTotalAmount(total_amount[0]);
            return alipayResult;
        }else{
            //验证失败
            return null;
        }
        
    }
    /**
     * 
     * 后台验证
     * 
     */
    public AlipayResult orderPayByAlipayNotify(Map requestParams) {
        Map<String,String> params = new HashMap<String,String>();
        /**
         * 获取封装请求参数
         */
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        //验证是否成功
        boolean signVerified=false;   
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, alipayConfig.getAlipayPublicKey(), alipayConfig.getCharset(), alipayConfig.getSignType());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        //验签成功
        if(signVerified){       
            logger.info("*******************    签名验证成功     **********************");
          //请根据您的业务逻辑来编写程序
            String[] trade_status = (String[])requestParams.get("trade_status");
            String[] out_trade_no = (String[])requestParams.get("out_trade_no");
            String[] total_amount = (String[])requestParams.get("total_amount");
            String[] app_id = (String[])requestParams.get("app_id");    
            /**
             *    注意：
             *         状态TRADE_SUCCESS的通知触发条件是商户签约的产品支持退款功能的前提下，买家付款成功；
             *         交易状态TRADE_FINISHED的通知触发条件是商户签约的产品不支持退款功能的前提下，买家付款成功；或者，商户签约的产品支持退款功能的前提下，交易已经成功并且已经超过可退款期限。
             *
             */            
            if("TRADE_FINISHED".equals(trade_status[0])){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序
                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                logger.info("**************该回调订单编号"+out_trade_no[0]+" 的状态为 TRADE_FINISHED******************");
                //通过支付宝传递的订单编号，查询本系统的订单
                Order order = orderManagerService.getOrderByOrderNo(out_trade_no[0]);
                if(order!=null){
                    logger.info("******************本地系统中存在该订单**************");
                    //判断该订单是否改为已付款
                    Integer status = order.getStatus();
                    //没有付款继续判断  total_amount，seller_id：是否与系统订单参数一致
                    //更改订单状态为已支付
                    if(status<OrderStatus.HASPAIED.getValue()){
                        logger.info("************本地系统该订单的状态为未支付*************");
                        BigDecimal orderamount = order.getOrderAmount();
                        Float total_count=DataChangeUtil.bigDecimalToTwoPonintFloat(orderamount);
                        String appId = alipayConfig.getAppid();
                        //如果订单的金额、开发者Id相匹配  ，更改订单状态为已支付
                        if(appId.equals(app_id[0])&&total_count.equals(total_amount)){
                            orderStatusSynService.tunrToHasPaied(out_trade_no[0]);
                            logger.info("***************更改本地系统的订单状态为已支付************************");
                        }
                    }
                    
                }
            } else if ("TRADE_SUCCESS".equals(trade_status[0])){
                logger.info("**************该回调订单编号"+out_trade_no[0]+" 的状态为 TRADE_SUCCESS******************");
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序
                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
                Order order = orderManagerService.getOrderByOrderNo(out_trade_no[0]);
                if(order!=null){
                    logger.info("******************本地系统中存在该订单**************");
                    //判断该订单是否改为已付款
                    Integer status = order.getStatus();
                    //没有付款继续判断  total_amount，seller_id：是否与系统订单参数一致
                    //更改订单状态为已支付
                    if(status<OrderStatus.HASPAIED.getValue()){
                        logger.info("************本地系统该订单的状态为未支付*************");
                        BigDecimal orderamount = order.getOrderAmount();
                        Float total_count=DataChangeUtil.bigDecimalToTwoPonintFloat(orderamount);
                        String appId = alipayConfig.getAppid();
                        logger.info("系统订单金额："+total_count);
                        logger.info("支付宝系统金额："+total_amount[0]);
                        boolean isSuit  = true;
                        if(!appId.equals(app_id[0])){
                            isSuit=false;
                            logger.info("AppId实际与通知不一致");
                        }
                        if(!total_count.toString().equals(total_amount[0])){
                            isSuit=false;
                            logger.info("支付金额实际与通知不一致");
                        }
                        //如果订单的金额、开发者Id相匹配  ，更改订单状态为已支付
                        if(isSuit){
                            orderStatusSynService.tunrToHasPaied(out_trade_no[0]);
                            logger.info("***************更改本地系统的订单状态为已支付************************");
                        }
                    }
                    
                }   
            /**
             * 未付款交易超时关闭，或支付完成后全额退款  
             */
            }else if ("TRADE_CLOSED".equals(trade_status[0])) {
                logger.info("**************该回调订单编号"+out_trade_no[0]+" 的状态为 TRADE_CLOSED******************");
                Order order = orderManagerService.getOrderByOrderNo(out_trade_no[0]);
                if(order!=null){
                    logger.info("******************本地系统中存在该订单**************");
                    //判断该订单是否已经做过取消处理
                    Integer status = order.getStatus();
                    //没有付款继续判断  total_amount，seller_id：是否与系统订单参数一致
                    if(status<OrderStatus.HASPAIED.getValue()){       
                        logger.info("************本地系统该订单的状态为未支付*************");
                        //该订单的的状态更改为取消
//                        orderStatusSynService.turnToCancel(orderNo, operator, operatorCode)(orderNoList);
                        logger.info("***************更改本地系统的订单状态为已取消************************");
                    }
                }
            }         
            // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
            //            try {
            //                response.getWriter().write("success");
            //            } catch (IOException e) {
            //                e.printStackTrace();
            //            }            
            //获取支付宝的通知返回参数       
            AlipayResult alipayResult = new AlipayResult();
            String[] buyer_logon_id= (String[])requestParams.get("buyer_logon_id");
            String[] buyer_id =(String[]) requestParams.get("buyer_id");
            String[] notify_time = (String[])requestParams.get("notify_time");
            String[] notify_id = (String[]) requestParams.get("notify_id");
            String[] out_trade_no2= (String[]) requestParams.get("out_trade_no");
            String[] trade_no = (String[]) requestParams.get("trade_no");
            String[] trade_status2 = (String[]) requestParams.get("trade_status");
            alipayResult.setAlipayId(buyer_logon_id[0]);
            alipayResult.setBuyUserId(buyer_id[0]);
            alipayResult.setDate(notify_time[0]);
            alipayResult.setNotifyId(notify_id[0]);
            alipayResult.setOutTradeNo(out_trade_no2[0]);
            alipayResult.setTradeNo(trade_no[0]);
            alipayResult.setTradeStatus(trade_status2[0]);
            alipayResult.setTotalAmount(total_amount[0]);
            return alipayResult;
        }else{
            // TODO 验签失败则记录异常日志，并在response中返回failure.
            //            try {
            //                response.getWriter().write("failure");
            //            } catch (IOException e) {
            //                e.printStackTrace();
            //            }
            logger.info("*******************    签名验证失败     **********************");
            return null;
        }       
    }

}
