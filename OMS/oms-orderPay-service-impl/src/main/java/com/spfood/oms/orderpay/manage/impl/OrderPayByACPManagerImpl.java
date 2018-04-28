package com.spfood.oms.orderpay.manage.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spfood.oms.order.intf.OrderManagerService;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderStatus;
import com.spfood.oms.orderinfosyn.intf.OrderStatusSynService;
import com.spfood.oms.orderpay.intf.domain.ACPPayBackgroundResult;
import com.spfood.oms.orderpay.intf.domain.ACPPayFrontResult;
import com.spfood.oms.orderpay.intf.domain.ACPPayQueryResult;
import com.spfood.oms.orderpay.manage.OrderPayByACPManager;
import com.spfood.oms.orderpay.pay.acppay.AcpConfig;
import com.spfood.oms.orderpay.pay.acppay.sdk.AcpService;
import com.spfood.oms.orderpay.pay.acppay.sdk.LogUtil;
import com.spfood.oms.orderpay.pay.acppay.sdk.SDKConfig;
import com.spfood.oms.orderpay.util.DataChangeUtil;

@Component
public class OrderPayByACPManagerImpl implements OrderPayByACPManager {
    @Autowired
    private OrderManagerService orderManagerService;
    @Autowired
    private OrderStatusSynService orderStatusSynService;
    @Autowired
    public AcpConfig acpConfig;
    Logger logger = Logger.getLogger(OrderPayByACPManagerImpl.class);
    // 银联网关支付 适用于pc网页 wap支付
    public String GatewayPay(Order order, String channelType) {
        HashMap<String, String> requestData = new HashMap<String, String>();
        /*** 银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改 ***/
        requestData.put("version", acpConfig.getVersion()); // 版本号，全渠道默认值
        requestData.put("encoding", acpConfig.getEncodingUtf8()); // 字符集编码，可以使用UTF-8,GBK两种方式
        requestData.put("signMethod", "01"); // 签名方法，只支持 01：RSA方式证书加密
        requestData.put("txnType", "01"); // 交易类型 ，01：消费
        requestData.put("txnSubType", "01"); // 交易子类型， 01：自助消费
        requestData.put("bizType", "000201"); // 业务类型，B2C网关支付，手机wap支付
        // 根据pc和手机网页设置参数
        String type = "07";
        if (channelType.equals("phone")) {
            type = "08";
        }
        if (channelType.equals("pc")) {
            type = "07";
        }
        requestData.put("channelType", type); // 渠道类型，这个字段区分B2C网关支付和手机wap支付；07：PC,平板
                                              // 08：手机
        requestData.put("accessType", "0"); // 接入类型，0：直连商户
        requestData.put("currencyCode", "156"); // 交易币种（境内商户一般是156 人民币）
        requestData.put("frontUrl", acpConfig.getFrontUrl());
        requestData.put("backUrl", acpConfig.getBackUrl());

        /*** 商户接入参数 ***/
        BigDecimal orderamount = order.getOrderAmount();

        requestData.put("merId",acpConfig.getMerId()); // 商户号码，请改成自己申请的正式商户号或者open上注册得来的777测试商户号
        requestData.put("orderId", order.getOrderNo()); // 商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则
        String currentTime = AcpConfig.getCurrentTime();
        logger.info("currentTime**********************"+currentTime);
        requestData.put("txnTime",currentTime); // 订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
        requestData.put("txnAmt", "" + DataChangeUtil.bigDecimalMoneyToPennyMoney(orderamount)); // 交易金额，单位分，不要带小数点
        // requestData.put("reqReserved", "透传字段");
        // //请求方保留域，如需使用请启用即可；透传字段（可以实现商户自定义参数的追踪）本交易的后台通知,对本交易的交易状态查询交易、对账文件中均会原样返回，商户可以按需上传，长度为1-1024个字节

        /** 请求参数设置完毕，以下对请求参数进行签名并生成html表单，将表单写入浏览器跳转打开银联页面 **/
        Map<String, String> submitFromData = AcpService.sign(requestData,
                acpConfig.getEncodingUtf8()); // 报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。

        String requestFrontUrl = SDKConfig.getConfig().getFrontRequestUrl(); // 获取请求银联的前台地址：对应属性文件acp_sdk.properties文件中的acpsdk.frontTransUrl
        String html = AcpService.createAutoFormHtml(requestFrontUrl,
                submitFromData, acpConfig.getEncodingUtf8()); // 生成自动跳转的Html表单

        LogUtil.writeLog("打印请求HTML，此为请求报文，为联调排查问题的依据：" + html);
        // 将生成的html写到浏览器中完成自动跳转打开银联支付页面；这里调用signData之后，将html写到浏览器跳转到银联页面之前均不能对html中的表单项的名称和值进行修改，如果修改会导致验签不通过
        return html;

    }
    /**
     * 后台验证
     */
    public ACPPayBackgroundResult notify(Map reqParam,String encoding) {
        try {
            Map<String, String> valideData = null;
            logger.info("**************银联回调验证************");
            //构建验证数据
            if (null != reqParam && !reqParam.isEmpty()) {
                Iterator<Entry<String, String[]>> it = reqParam.entrySet().iterator();
                valideData = new HashMap<String, String>(reqParam.size());
                while (it.hasNext()) {
                    Entry<String, String[]> e = it.next();
                    String key = (String) e.getKey();
                    String[] strings = (String[]) e.getValue();
                    String value = strings[0];
                    try {
                        value = new String(value.getBytes(encoding), encoding);
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }
                    valideData.put(key, value);
                }
            }
            // 重要！验证签名前不要修改reqParam中的键值对的内容，否则会验签不过
            if (!AcpService.validate(valideData, encoding)) {
                LogUtil.writeLog("验证签名结果[失败].");
                logger.info("**********银联回调签名验证失败***************");
                return null;
            } else {
                LogUtil.writeLog("验证签名结果[成功].");         
                logger.info("***********银联回调签名验证成功**************");
                String customerInfo = valideData.get("customerInfo");
                if (null != customerInfo) {
                    Map<String, String> customerInfoMap = AcpService
                            .parseCustomerInfo(customerInfo, "UTF-8");
                    LogUtil.writeLog("customerInfoMap明文: " + customerInfoMap);
                }
                String accNo = valideData.get("accNo");
                // 如果配置了敏感信息加密证书，可以用以下方法解密
                if (null != accNo) {
                    accNo = AcpService.decryptData(accNo, "UTF-8");
                    LogUtil.writeLog("accNo明文: " + accNo);
                }
                //封装通知返回对象结果
                ACPPayBackgroundResult aCPPayBackgroundResult = new ACPPayBackgroundResult();
                aCPPayBackgroundResult.setAccessType(valideData.get("accessType"));
                aCPPayBackgroundResult.setAccNo(valideData.get("accNo"));
                aCPPayBackgroundResult.setAccSplitData(valideData.get("accSplitData"));
                aCPPayBackgroundResult.setBindId(valideData.get("bindId"));
                aCPPayBackgroundResult.setBizType(valideData.get("bizType"));
                aCPPayBackgroundResult.setCurrencyCode(valideData.get("currencyCode"));
                aCPPayBackgroundResult.setEncoding(valideData.get("encoding"));
                aCPPayBackgroundResult.setExchangeDate(valideData.get("exchangeDate"));
                aCPPayBackgroundResult.setExchangeRate(valideData.get("exchangeRate"));
                aCPPayBackgroundResult.setMerId(valideData.get("merId"));
                aCPPayBackgroundResult.setOrderId(valideData.get("orderId"));
                aCPPayBackgroundResult.setPayCardIssueName(valideData.get("payCardIssueName"));
                aCPPayBackgroundResult.setPayCardNo(valideData.get("payCardNo"));
                aCPPayBackgroundResult.setPayCardType(valideData.get("payCardType"));
                aCPPayBackgroundResult.setPayType(valideData.get("payType"));
                aCPPayBackgroundResult.setQueryId(valideData.get("queryId"));
                aCPPayBackgroundResult.setReqReserved(valideData.get("reqReserved"));
                aCPPayBackgroundResult.setReserved(valideData.get("reserved"));
                aCPPayBackgroundResult.setRespCode(valideData.get("respCode"));
                aCPPayBackgroundResult.setRespMsg(valideData.get("respMsg"));
                aCPPayBackgroundResult.setSettleAmt(valideData.get("settleAmt"));
                aCPPayBackgroundResult.setSettleCurrencyCode(valideData.get("settleCurrencyCode"));
                aCPPayBackgroundResult.setSettleDate(valideData.get("settleDate"));
                aCPPayBackgroundResult.setSignature(valideData.get("signature"));
                aCPPayBackgroundResult.setSignMethod(valideData.get("signMethod"));
                aCPPayBackgroundResult.setSignPubKeyCert(valideData.get("signPubKeyCert"));
                aCPPayBackgroundResult.setTraceNo(valideData.get("traceNo"));
                aCPPayBackgroundResult.setTraceTime(valideData.get("traceTime"));
                aCPPayBackgroundResult.setTxnAmt(valideData.get("txnAmt"));
                aCPPayBackgroundResult.setTxnSubType(valideData.get("txnSubType"));
                aCPPayBackgroundResult.setTxnTime(valideData.get("txnTime"));
                aCPPayBackgroundResult.setTxnType(valideData.get("txnType"));
                aCPPayBackgroundResult.setVersion(valideData.get("version")); 
                aCPPayBackgroundResult.setTotalAmount(DataChangeUtil.pennyMoneyStringToBigDecimal(valideData.get("txnAmt")));
                logger.info("***********后台封装传递结果"+aCPPayBackgroundResult+"***********");
                String respCode = valideData.get("respCode");
                // 判断respCode=00、A6后，对涉及资金类的交易，请再发起查询接口查询，确定交易成功后更新数据库。
                if (("00").equals(respCode)||("06").equals(respCode)) {
                    logger.info("**********银联资金类交易***********");
                    // 交易已受理(不代表交易已成功），等待接收后台通知更新订单状态,也可以主动发起 查询交易确定交易状态。
                    String txnType = valideData.get("txnType");
                    // 获取订单编号
                    String orderId = valideData.get("orderId"); 
                    //交易金额
                    String txnAmt = valideData.get("txnAmt");               
                    Order order = orderManagerService.getOrderByOrderNo(orderId);
                    //系统中存在该订单
                    if(order!=null){
                        BigDecimal orderamount = order.getOrderAmount();
                        String orderMoney = DataChangeUtil.bigDecimalMoneyToPennyMoney(orderamount);
                        logger.info("***************系统中存在该订单"+order.getOrderNo()+"************");
                        logger.info("*****系统订单*****"+order);
                        //判断是消费,订单状态为未支付，订单金额一致
                        if(txnType.equals("01")&&(order.getStatus()<OrderStatus.HASPAIED.getValue())&&txnAmt.equals(orderMoney)){
                            //发起订单查询判断
                            //更改订单的状态为已支付
                            List<String> orderNoList= new ArrayList<String>();
                            orderNoList.add(orderId);
                            //发起查询请求，判断该笔订单的支付结果
                            orderStatusSynService.tunrToHasPaied(orderNoList);
                            aCPPayBackgroundResult.setIsSuccessPay(true);
                            logger.info("*************更改系统中的订单状态为已付款********");
                        }
                        //判断为退款
                        if(txnType.equals("04")){
                            //发起订单查询，判断交易是否成功，更改系统中的订单
                            
                        }
                    }else {
                        logger.info("********系统中不存在该订单********");
                    }
                }
                return aCPPayBackgroundResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
       
    }
    /**
     * 前台返回
     * 
     */
    public ACPPayFrontResult orderPayByACPReturn(Map respParam) {
        try {
            Map<String, String> valideData = null;
            StringBuffer page = new StringBuffer();
            if (null != respParam && !respParam.isEmpty()) {
                Iterator<Entry<String, String[]>> it = respParam.entrySet().iterator();
                valideData = new HashMap<String, String>(respParam.size());
                while (it.hasNext()) {
                    Entry<String, String[]> e = it.next();

                    String key = (String) e.getKey();
                    String[] values = (String[])e.getValue();
                    String value = values[0];

                    try {
                        value = new String(value.getBytes(acpConfig.getEncodingUtf8()),
                                acpConfig.getEncodingUtf8());
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }

                    valideData.put(key, value);
                }
            }
            ACPPayFrontResult acpPayFrontResult = new ACPPayFrontResult();
            //如果验签失败
            if (!AcpService.validate(valideData, acpConfig.getEncodingUtf8())) {
                LogUtil.writeLog("验证签名结果[失败].");
                logger.info("********返回系统验证失败*******");
                return null;
            // 签名验证通过设置传递到前台的支付信息 
            } else {    
                LogUtil.writeLog("验证签名结果[成功].");
                logger.info("********返回系统验证成功*******");
                acpPayFrontResult.setAccessType(valideData.get("accessType"));
                acpPayFrontResult.setAccNo(valideData.get("accNo"));
                acpPayFrontResult.setBizType(valideData.get("bizType"));
                acpPayFrontResult.setCurrencyCode(valideData.get("currencyCode"));
                acpPayFrontResult.setEncoding(valideData.get("encoding"));
                acpPayFrontResult.setMerId(valideData.get("merId"));
                acpPayFrontResult.setOrderId(valideData.get("orderId"));
                acpPayFrontResult.setPayCardType(valideData.get("payCardType"));
                acpPayFrontResult.setPayType(valideData.get("payType"));
                acpPayFrontResult.setQueryId(valideData.get("queryId"));
                acpPayFrontResult.setReqReserved(valideData.get("reqReserved"));
                acpPayFrontResult.setReserved(valideData.get("reserved"));
                acpPayFrontResult.setRespCode(valideData.get("respCode"));
                acpPayFrontResult.setRespMsg(valideData.get("respMsg"));
                acpPayFrontResult.setSignature(valideData.get("signature"));
                acpPayFrontResult.setSignMethod(valideData.get("signMethod"));
                acpPayFrontResult.setSignPubKeyCert(valideData.get("signPubKeyCert"));
                acpPayFrontResult.setTn(valideData.get("tn"));
                acpPayFrontResult.setTxnAmt(valideData.get("txnAmt"));
                acpPayFrontResult.setTxnSubType(valideData.get("txnSubType"));
                acpPayFrontResult.setTxnTime(valideData.get("txnTime"));
                acpPayFrontResult.setTxnType(valideData.get("txnType"));
                acpPayFrontResult.setVersion(valideData.get("version"));
                acpPayFrontResult.setTotalAmout(DataChangeUtil.pennyMoneyStringToBigDecimal(valideData.get("txnAmt")));
            }
            return acpPayFrontResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
       
    }

    /**
     * 退款处理
     */
    @Override
    public void orderPayByACPRefund(ACPPayBackgroundResult aCPPayBackgroundResult) {
        // TODO Auto-generated method stub
      
    }
    @Override
    public ACPPayQueryResult orderPayByACPQuery(
            ACPPayBackgroundResult aCPPayBackgroundResult) {
        // TODO Auto-generated method stub
        return null;
    }
}
