package com.spfood.oms.orderpay.manage.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spfood.oms.orderpay.intf.domain.ACPPayQueryResult;
import com.spfood.oms.orderpay.manage.ACPQueryManager;
import com.spfood.oms.orderpay.pay.acppay.AcpConfig;
import com.spfood.oms.orderpay.pay.acppay.sdk.AcpService;
import com.spfood.oms.orderpay.pay.acppay.sdk.LogUtil;
import com.spfood.oms.orderpay.pay.acppay.sdk.SDKConfig;
@Component
public class ACPQueryManagerImpl implements ACPQueryManager{
    @Autowired
    public AcpConfig acpConfig;
    @Override
    public ACPPayQueryResult orderPayByACPGateQuery(String orderId,String txnTime,String queryId) {
     Map<String, String> data = new HashMap<String, String>();
     // 版本号
     data.put("version", acpConfig.getVersion());
     // 字符集编码 可以使用UTF-8,GBK两种方式
     data.put("encoding", acpConfig.getEncodingUtf8());
     // 签名方法
     data.put("signMethod", "01");
     // 交易类型 
     data.put("txnType","00");
     // 交易子类型
     data.put("txnSubType","00");
     // 业务类型
     data.put("bizType","000201");

     /*** 商户接入参数 ***/
     data.put("merId", acpConfig.getMerId());
     // 接入类型，商户接入固定填0，不需修改
     data.put("accessType", "0");
     /**
      * 确保至少输入orderId，txnTime或者queryId
      */
     // ****商户订单号，每次发交易测试需修改为被查询的交易的订单号
     if(orderId!=null&&txnTime!=null){
         data.put("orderId", orderId);
         data.put("txnTime",txnTime);
         if(queryId!=null){
             data.put("queryId",queryId);
         }
     }else {
         data.put("queryId",queryId);
     }
    
     /** 请求参数设置完毕，以下对请求参数进行签名并发送http post请求，接收同步应答报文-------------> **/
     // 报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
     Map<String, String> reqData = AcpService.sign(data,acpConfig.getEncodingUtf8());
     // 交易请求url从配置文件读取对应属性文件acp_sdk.properties中的 acpsdk.singleQueryUrl
     String url = SDKConfig.getConfig().getSingleQueryUrl();
     // 发送请求报文并接受同步应答（默认连接超时时间30秒，读取返回结果超时时间30秒）;这里调用signData之后，
     // 调用submitUrl之前不能对submitFromData中的键值对做任何修改，如果修改会导致验签不通过
     Map<String, String> rspData = AcpService.post(reqData, url,
             acpConfig.getEncodingUtf8());
     if(!rspData.isEmpty()){
         //验签成功
         if(AcpService.validate(rspData,acpConfig.getEncodingUtf8())){
             LogUtil.writeLog("验证签名成功");
             ACPPayQueryResult acpPayQueryResult = new ACPPayQueryResult();
             //设置返回对象信息
             acpPayQueryResult.setAccNo(rspData.get("accNo"));
             acpPayQueryResult.setCurrencyCode(rspData.get("currencyCode"));
             acpPayQueryResult.setOrderId(rspData.get("orderId"));
             acpPayQueryResult.setOrigRespCode(rspData.get("origRespCode"));
             acpPayQueryResult.setOrigRespMsg(rspData.get("origRespMsg"));
             acpPayQueryResult.setPayCardType(rspData.get("payCardType"));
             acpPayQueryResult.setPayType(rspData.get("payType"));
             acpPayQueryResult.setQueryId(rspData.get("queryId"));
             acpPayQueryResult.setRespCode(rspData.get("respCode"));
             acpPayQueryResult.setRespMsg(rspData.get("respMsg"));
             acpPayQueryResult.setSettleAmt(rspData.get("settleAmt"));
             acpPayQueryResult.setSettleCurrencyCode(rspData.get("settleCurrencyCode"));
             acpPayQueryResult.setSettleDate(rspData.get("settleDate"));
             acpPayQueryResult.setTraceNo(rspData.get("traceNo"));
             acpPayQueryResult.setTraceTime(rspData.get("traceTime"));
             acpPayQueryResult.setTxnAmt(rspData.get("txnAmt"));
             acpPayQueryResult.setTxnTime(rspData.get("txnTime"));
             acpPayQueryResult.setTxnType(rspData.get("txnType"));           
             //根据查询的结果做相应的处理，也可以在B2C系统中根据查询结果做相应的处理
             if("00".equals(rspData.get("respCode"))){//如果查询交易成功
                 //处理被查询交易的应答码逻辑
                 String origRespCode = rspData.get("origRespCode");
                 if("00".equals(origRespCode)){
                     //交易成功，更新商户订单状态
                 }else if("03".equals(origRespCode) ||
                          "04".equals(origRespCode) ||
                          "05".equals(origRespCode)){
                     //需再次发起交易状态查询交易 
                 }else{
                     //其他应答码为失败请排查原因
                 }
             }else{//查询交易本身失败，或者未查到原交易，检查查询交易报文要素
             }
             //返回查询的结果
             return acpPayQueryResult;
         }else{
             LogUtil.writeErrorLog("验证签名失败");
             return null;
         }
     }else{
         //未返回正确的http状态
         LogUtil.writeErrorLog("未获取到返回报文或返回http状态码非200");
     }
     return null;
    }

}
