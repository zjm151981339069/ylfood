package com.spfood.oms.orderpay.manage.impl;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spfood.oms.order.intf.OrderManagerService;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderStatus;
import com.spfood.oms.orderinfosyn.intf.OrderStatusSynService;
import com.spfood.oms.orderpay.intf.domain.WeiChatAppPay;
import com.spfood.oms.orderpay.intf.domain.WeiChatPay;
import com.spfood.oms.orderpay.intf.domain.WeiChatPayNotifyResults;
import com.spfood.oms.orderpay.intf.domain.WeiChatPayQueryResult;
import com.spfood.oms.orderpay.intf.domain.WeiChatQRPayResults;
import com.spfood.oms.orderpay.intf.domain.WxPayResultDetail;
import com.spfood.oms.orderpay.manage.OrderPayByWeiChatManager;
import com.spfood.oms.orderpay.manage.WeichatQueryManager;
import com.spfood.oms.orderpay.pay.weichatpay.CommonUtil;
import com.spfood.oms.orderpay.pay.weichatpay.GetWxOrderno;
import com.spfood.oms.orderpay.pay.weichatpay.RequestHandler;
import com.spfood.oms.orderpay.pay.weichatpay.Sha1Util;
import com.spfood.oms.orderpay.pay.weichatpay.TenpayUtil;
import com.spfood.oms.orderpay.pay.weichatpay.WeiChatConfig;
import com.spfood.oms.orderpay.util.DataChangeUtil;
import com.spfood.oms.orderpay.util.XmlUtil;

@Component
public class OrderPayByWeiChatManagerImpl implements OrderPayByWeiChatManager {
    @Autowired
    private OrderManagerService orderManagerService;
    @Autowired
    private OrderStatusSynService orderStatusSynService;
    @Autowired
    private WeichatQueryManager weichatQueryManager;
    @Autowired
    public WeiChatConfig weiChatConfig;
    Logger logger = Logger.getLogger(OrderPayByWeiChatManagerImpl.class);

    /**
     * 支付授权后到正式的支付接口中创建H5的js支付接口调用的支付参数，并实现支付的调用
     */
    public WeiChatPay h5Pay(String remoteAddr, Order order, String authCode,
            String useId) {
        // 网页授权后获取传递的参数
        BigDecimal orderamount = order.getOrderAmount();
        String userId = useId;
        String orderNo = order.getOrderNo();
        String code = authCode;
        String finalmoney =  DataChangeUtil.bigDecimalMoneyToPennyMoney(orderamount);   

        // 商户相关资料
        String appid = weiChatConfig.getAppId();
        String appsecret = weiChatConfig.getAppsecret();
        String partner = weiChatConfig.getPartner();
        String partnerkey = weiChatConfig.getPartnerKery();

        // 获取openId
        String openId = "";
        String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                + appid + "&secret=" + appsecret + "&code=" + code
                + "&grant_type=authorization_code";

        JSONObject jsonObject = CommonUtil.httpsRequest(URL, "GET", null);
        if (null != jsonObject) {
            openId = jsonObject.getString("openId");
        }
        // 获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
        String currTime = TenpayUtil.getCurrTime();
        // 8位日期
        String strTime = currTime.substring(8, currTime.length());
        // 四位随机数
        String strRandom = TenpayUtil.buildRandom(4) + "";
        // 10位序列号,可以自行调整。
        String strReq = strTime + strRandom;
        // 商户号
        String mch_id = partner;
        // 随机数
        String nonce_str = strReq;
        // 商品描述根据情况修改
        String body = " ";
        // 附加数据
        String attach = userId;
        // 商户订单号
        String out_trade_no = orderNo;
        // 订单生成的机器 IP,顾客终端ip
        String spbill_create_ip = remoteAddr;
        // 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
        String notify_url = weiChatConfig.getNotifyUrl();
        String trade_type = "JSAPI";
        String openid = openId;
        // 产品id 非必输
        // String product_id = "";
        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("appid", appid);
        packageParams.put("mch_id", mch_id);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", body);
        packageParams.put("attach", attach);
        packageParams.put("out_trade_no", out_trade_no);
        packageParams.put("total_fee", finalmoney);
        packageParams.put("spbill_create_ip", spbill_create_ip);
        packageParams.put("notify_url", notify_url);
        packageParams.put("trade_type", trade_type);
        packageParams.put("openid", openid);
        RequestHandler reqHandler = new RequestHandler(null, null);
        reqHandler.init(appid, appsecret, partnerkey);
        String sign = reqHandler.createSign(packageParams);
        String xml = "<xml>" + "<appid>" + appid + "</appid>" 
                + "<mch_id>"+ mch_id + "</mch_id>"
                + "<nonce_str>" + nonce_str+ "</nonce_str>" 
                + "<sign>" + sign + "</sign>"
                + "<body><![CDATA[" + body + "]]></body>" 
                + "<attach>" + attach+ "</attach>" 
                + "<out_trade_no>" + out_trade_no+ "</out_trade_no>"
                + "<total_fee>" + finalmoney+ "</total_fee>" 
                + "<spbill_create_ip>" + spbill_create_ip+ "</spbill_create_ip>" 
                + "<notify_url>" + notify_url+ "</notify_url>" 
                + "<trade_type>" + trade_type+ "</trade_type>" 
                + "<openid>" + openid + "</openid>"
                + "</xml>";
        logger.info(xml);
        // 统一支付订单请求url
        String createOrderURL = weiChatConfig.getUnifiedorderUrl();
        // 预支付订单id
        String prepay_id = "";
        try {
            prepay_id = GetWxOrderno.getPayNo(createOrderURL, xml);
            if (prepay_id.equals("")) {
                return null;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        SortedMap<String, String> finalpackage = new TreeMap<String, String>();
        String appid2 = appid;
        String timestamp = Sha1Util.getTimeStamp();
        String nonceStr2 = nonce_str;
        String prepay_id2 = "prepay_id=" + prepay_id;
        String packages = prepay_id2;
        finalpackage.put("appId", appid2);
        finalpackage.put("timeStamp", timestamp);
        finalpackage.put("nonceStr", nonceStr2);
        finalpackage.put("package", packages);
        finalpackage.put("signType", weiChatConfig.getSignType());
        String finalsign = reqHandler.createSign(finalpackage);
        WeiChatPay weiChatPay = new WeiChatPay(appid2, timestamp, nonceStr2,
                packages, finalsign,weiChatConfig.getSignType());
        return weiChatPay;

    }
    /**
     * 二维码支付，根据支付链接地址生成二维码图片
     */
    public WeiChatQRPayResults QRPay(Order order) {
        String orderNo = order.getOrderNo();
        WeiChatQRPayResults WeiChatQRPayResults = new WeiChatQRPayResults();
        Order orderByOrderNo = orderManagerService.getOrderByOrderNo(orderNo);
        //系统中不存在该订单,返回错误码    -1
        if(orderByOrderNo==null){
            WeiChatQRPayResults.setErrCode("-1");
            return WeiChatQRPayResults;
        }
        //系统中该订单已取消，返回错误码为  0
        if(orderByOrderNo!=null&&OrderStatus.CANCEL.getValue()==orderByOrderNo.getStatus()){
            WeiChatQRPayResults.setErrCode("0");
            return WeiChatQRPayResults;
        }
        //系统中该订单已支付，返回错误码为  2
        if(orderByOrderNo!=null&&OrderStatus.UNPAIED.getValue()<orderByOrderNo.getStatus()){
            WeiChatQRPayResults.setErrCode("2");
            return WeiChatQRPayResults;
        }
        
        //判断订单存在并且为待支付
        if(orderByOrderNo!=null&&OrderStatus.UNPAIED.getValue()==orderByOrderNo.getStatus()){
            BigDecimal orderamount = order.getOrderAmount();
            String finalmoney =  DataChangeUtil.bigDecimalMoneyToPennyMoney(orderamount);   
            // 商户相关资料
            String appid = weiChatConfig.getAppId();
            String appsecret = weiChatConfig.getAppsecret();
            String partner = weiChatConfig.getPartner();
            String partnerkey = weiChatConfig.getPartnerKery();
            // 设置随机字符串
            String currTime = TenpayUtil.getCurrTime();
            // 8位日期
            String strTime = currTime.substring(8, currTime.length());
            // 四位随机数
            String strRandom = TenpayUtil.buildRandom(4) + "";
            // 10位序列号,可以自行调整。
            String strReq = strTime + strRandom;
            // 商户号
            String mch_id = partner;
            // 随机数
            String nonce_str = strReq;
            // 商品描述根据情况修改
            String body = "银犁食品";
            //商品详情
            String detail = "银犁食品";
            // 商户订单号
            String out_trade_no = orderNo;
            // 订单生成的机器 IP,调用微信支付的机器ip
            InetAddress addr = null;
            try {
                addr = InetAddress.getLocalHost();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            String spbill_create_ip = addr.getHostAddress().toString();
            // 这里notify_url是 支付完成后微信发给该链接信息，可以判断是否支付成功，改变订单状态等。
            String notify_url = weiChatConfig.getNotifyUrl();
            // 二维码支付方式
            String trade_type = "NATIVE";
            // 非必输
            // String product_id = "";
            SortedMap<String, String> packageParams = new TreeMap<String, String>();
            packageParams.put("appid", appid);
            packageParams.put("mch_id", mch_id);
            packageParams.put("nonce_str", nonce_str);
            packageParams.put("body", body);
            packageParams.put("out_trade_no", out_trade_no);
            packageParams.put("total_fee", finalmoney);
            packageParams.put("spbill_create_ip", spbill_create_ip);
            packageParams.put("notify_url", notify_url);
            packageParams.put("trade_type", trade_type);
            packageParams.put("detail", detail);
            RequestHandler reqHandler = new RequestHandler(null, null);
            reqHandler.init(appid, appsecret, partnerkey);
            String sign = reqHandler.createSign(packageParams);
            // 统一支付请求的xml
            String xml = "<xml>" 
                    + "<appid>" + appid + "</appid>" 
                    + "<mch_id>" + mch_id + "</mch_id>" 
                    + "<nonce_str>" + nonce_str + "</nonce_str>" 
                    + "<sign>" + sign + "</sign>"
                    + "<body><![CDATA[" + body + "]]></body>" 
                    + "<out_trade_no>" + out_trade_no + "</out_trade_no>" 
                    + "<total_fee>" + finalmoney+ "</total_fee>" 
                    + "<spbill_create_ip>" + spbill_create_ip+ "</spbill_create_ip>"
                    + "<notify_url>" + notify_url+ "</notify_url>" 
                    + "<trade_type>" + trade_type+ "</trade_type>" 
                    + "<detail>" + detail+ "</detail>" 
                    + "</xml>";
            logger.info(xml);
            // 微信二维码支付对应统一订单的支付地址
            String code_url = "";
            String createOrderURL =weiChatConfig.getUnifiedorderUrl();
            try {
				code_url = GetWxOrderno.getPayUrl(createOrderURL, xml);
                System.out.println("微信二维码支付的地址=="+code_url);
                //微信支付系统中存在该订单编号  设置错误编码为 1
                if (code_url.equals("")) {
                    WeiChatQRPayResults.setErrCode("1");
                    return WeiChatQRPayResults;
                }
                WeiChatQRPayResults.setPayUrl(code_url);
            } catch (Exception e1) {
                e1.printStackTrace();    
            // 返回支付的二维码图片地址
            }
            return WeiChatQRPayResults;
        }else {
            return null;
        }
    }

    /**
     * 支付验证，发送支付是否成功信息到微信支付服务器，同时处理自身的业务逻辑：写入支付表，更改订单状态
     */
    public WeiChatPayNotifyResults notify(
            String weiChatPayNotifyParms) {

        String resXml = "";
        String notityXml = "";
        notityXml = weiChatPayNotifyParms;
        logger.info("微信回调xml============="+notityXml);
        Map m = XmlUtil.parseXmlToList2(notityXml);
        WxPayResultDetail wpr = new WxPayResultDetail();
        Boolean isSuccessPay = false;
        try {
            //获取通知参数
            wpr.setAppId(m.get("appid").toString());
            wpr.setBankType(m.get("bank_type").toString());
            //现金支付金额
            String cashFee = m.get("cash_fee").toString();
            wpr.setCashFee(DataChangeUtil.pennyMoneyStringToBigDecimal(cashFee));
            wpr.setFeeType(m.get("fee_type").toString());
            wpr.setIsSubscribe(m.get("is_subscribe").toString());
            wpr.setMchId(m.get("mch_id").toString());
            wpr.setNonceStr(m.get("nonce_str").toString());
            wpr.setOpenId(m.get("openid").toString());
            wpr.setOutTradeNo(m.get("out_trade_no").toString());
            wpr.setResultCode(m.get("result_code").toString());
            wpr.setSign(m.get("sign").toString());
            wpr.setTimeEnd(m.get("time_end").toString());
            //订单总金额
            String totalfee = m.get("total_fee").toString();
            wpr.setTotalFee(DataChangeUtil.pennyMoneyStringToBigDecimal(totalfee));
            wpr.setTradeType(m.get("trade_type").toString());
            wpr.setTransactionId(m.get("transaction_id").toString());
            if ("SUCCESS".equals(wpr.getResultCode())) {
                // 支付成功
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                //处理相关的商业业务逻辑
                //先判断该笔订单是否已经处理，如果未处理继续判断订单编号和支付金额是否正确
                Order order = orderManagerService.getOrderByOrderNo(m.get("out_trade_no").toString());
                if(order!=null){
                    if(order.getStatus()<OrderStatus.HASPAIED.getValue()){
                        BigDecimal orderamount = order.getOrderAmount();
                        //系统订单金额
                        String orderMoney = DataChangeUtil.bigDecimalMoneyToPennyMoney(orderamount);
                        //支付的金额
                        String totalFee = m.get("total_fee").toString();
                        //判断支付的金额是否和系统订单一致，如果一致,发起订单支付查询结果匹配就更改订单的状态为已付款
                        if(totalFee.equals(orderMoney)){
                            logger.info("订单金额匹配："+order.getOrderNo());
                            WeiChatPayQueryResult orderPayQuery = weichatQueryManager.orderPayQuery(m.get("transaction_id").toString(), null);
                            //查询结果匹配更改订单状态
                            String bigDecimalMoneyToPennyMoney = DataChangeUtil.bigDecimalToTwoPonintFloat(order.getOrderAmount()).toString();
                            DataChangeUtil.bigDecimalToTwoPonintFloat(order.getOrderAmount());
                            logger.info("查询金额"+orderPayQuery.getTotalFee());
                            logger.info("订单金额"+bigDecimalMoneyToPennyMoney);
                            logger.info("查询状态"+orderPayQuery.getTradeState());   
                            if((orderPayQuery.getTotalFee().toString().equals(bigDecimalMoneyToPennyMoney))&&orderPayQuery.getTradeState().equals("SUCCESS")){
                                //更改订单状态为已付款
                                orderStatusSynService.tunrToHasPaied(order.getOrderNo());
                                logger.info("回调更改订单状态："+order.getOrderNo());
                            }
                        }
                    }
                }           
                isSuccessPay = true;                      
            } else {
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                        + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                isSuccessPay = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        WeiChatPayNotifyResults weiChatPayNotifyResults = new WeiChatPayNotifyResults(
                isSuccessPay, resXml, wpr);
        return weiChatPayNotifyResults;
    }

    public static String getWeiChatNotifyXML(HttpServletRequest request){
        String inputLine;
        String notityXmls = "";
        try {
            while ((inputLine = request.getReader().readLine()) != null) {
                notityXmls += inputLine;
            }
            request.getReader().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notityXmls;
    }

    @Override
    public WeiChatAppPay APPPay(String remoteAddr,Order order) {
        //订单信息
        BigDecimal orderamount = order.getOrderAmount();
        String orderNo = order.getOrderNo();
        String finalmoney =  DataChangeUtil.bigDecimalMoneyToPennyMoney(orderamount);   

        // 商户相关资料
        String appid = weiChatConfig.getAppId();
        String appsecret = weiChatConfig.getAppsecret();
        String partner = weiChatConfig.getPartner();
        String partnerkey = weiChatConfig.getPartnerKery();

        String currTime = TenpayUtil.getCurrTime();
        // 8位日期
        String strTime = currTime.substring(8, currTime.length());
        // 四位随机数
        String strRandom = TenpayUtil.buildRandom(4) + "";
        // 10位序列号,可以自行调整。
        String strReq = strTime + strRandom;
        // 商户号
        String mch_id = partner;
        // 随机数
        String nonce_str = strReq;
        // 商品描述根据情况修改
        String body = " ";
        // 商户订单号
        String out_trade_no = orderNo;
        // 订单生成的机器 IP,顾客终端ip
        String spbill_create_ip = remoteAddr;
        // 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
        String notify_url = weiChatConfig.getNotifyUrl();
        String trade_type = "APP";
        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("appid", appid);
        packageParams.put("mch_id", mch_id);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", body);
        packageParams.put("out_trade_no", out_trade_no);
        packageParams.put("total_fee", finalmoney);
        packageParams.put("spbill_create_ip", spbill_create_ip);
        packageParams.put("notify_url", notify_url);
        packageParams.put("trade_type", trade_type);
        RequestHandler reqHandler = new RequestHandler(null, null);
        reqHandler.init(appid, appsecret, partnerkey);
        String sign = reqHandler.createSign(packageParams);
        String xml = "<xml>" + "<appid>" + appid + "</appid>" 
                + "<mch_id>"+ mch_id + "</mch_id>"
                + "<nonce_str>" + nonce_str+ "</nonce_str>" 
                + "<sign>" + sign + "</sign>"
                + "<body><![CDATA[" + body + "]]></body>" 
                + "<out_trade_no>" + out_trade_no+ "</out_trade_no>"
                + "<total_fee>" + finalmoney+ "</total_fee>" 
                + "<spbill_create_ip>" + spbill_create_ip+ "</spbill_create_ip>" 
                + "<notify_url>" + notify_url+ "</notify_url>" 
                + "<trade_type>" + trade_type+ "</trade_type>" 
                + "</xml>";       
        // 统一支付订单请求url
        String createOrderURL = weiChatConfig.getUnifiedorderUrl();
        // 预支付订单id
        String prepay_id = "";
        try {
            logger.info("************App支付发起统一订单支付请求***********");
            prepay_id =GetWxOrderno.getPayNo(createOrderURL, xml);
            logger.info("************App支付发起统一订单支付请求预支付ID"+prepay_id+"**********");
            if (prepay_id.equals("")) {
                logger.info("===========App支付发起统一订单支付请求失败===========");
                return null;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        SortedMap<String, String> finalpackage = new TreeMap<String, String>();
        String appid2 = appid;
        String timestamp = Sha1Util.getTimeStamp();
        String nonceStr2 = nonce_str;
        String prepay_id2 = "prepay_id=" + prepay_id;
        String packages = prepay_id2;
        finalpackage.put("appid", appid2);
        finalpackage.put("partnerid", partner);
        finalpackage.put("prepayid", prepay_id);
        finalpackage.put("timestamp", timestamp);
        finalpackage.put("noncestr", nonceStr2);
        finalpackage.put("package", packages);
        finalpackage.put("signtype", weiChatConfig.getSignType());
        String finalsign = reqHandler.createSign(finalpackage);
        WeiChatAppPay weiChatAppPay = new WeiChatAppPay(appid2, partner, prepay_id, timestamp, nonceStr2, packages, finalsign);
        logger.info("******创建APP支付参数对象："+weiChatAppPay);
        return weiChatAppPay;

    }
   
    

}
