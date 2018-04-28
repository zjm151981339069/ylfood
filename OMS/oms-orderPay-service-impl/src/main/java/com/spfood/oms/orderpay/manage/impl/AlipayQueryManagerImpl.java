package com.spfood.oms.orderpay.manage.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.spfood.oms.order.intf.OrderManagerService;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.orderpay.intf.domain.AlipayQueryResult;
import com.spfood.oms.orderpay.manage.AlipayQueryManager;
import com.spfood.oms.orderpay.pay.alipay.AlipayClientFactory;
@Component
public class AlipayQueryManagerImpl implements AlipayQueryManager{
    @Autowired
    private OrderManagerService orderManagerService;
    @Autowired
    private AlipayClientFactory alipayClientFactory;
    @Override
    public AlipayQueryResult orderPayQuery(String out_trade_no, String trade_no) {
      //转化支付的金额为两位小数点的浮点数
      Map<String, String> maps = new HashMap<String, String>();  
      //优先选用trade_no 支付宝交易号
      if(trade_no!=null){
          maps.put("trade_no",trade_no);  
          if(out_trade_no!=null){
              maps.put("out_trade_no", out_trade_no);  
          }
      }else{
          maps.put("out_trade_no", out_trade_no);  
      }
      AlipayTradeQueryResponse   alipayTradeQueryResponse =null;
      try {  
          alipayTradeQueryResponse = alipayClientFactory.orderQuery(maps); 
      } catch (Exception e) {  
          e.printStackTrace();  
      } 
      if(alipayTradeQueryResponse!=null){
          AlipayQueryResult alipayQueryResult = new AlipayQueryResult();
          if(alipayTradeQueryResponse.isSuccess()){
              //创建支付查询结果
              alipayQueryResult.setBuyerLogonId(alipayTradeQueryResponse.getBuyerLogonId());
              alipayQueryResult.setBuyerUserId(alipayTradeQueryResponse.getBuyerUserId());
              alipayQueryResult.setOutTradeNo(alipayTradeQueryResponse.getOutTradeNo());
              alipayQueryResult.setReceiptAmount(new BigDecimal(alipayTradeQueryResponse.getReceiptAmount()));
              alipayQueryResult.setSendPayDate(alipayTradeQueryResponse.getSendPayDate());
              alipayQueryResult.setTotalAmount(new BigDecimal(alipayTradeQueryResponse.getTotalAmount()));
              alipayQueryResult.setTradeNo(alipayTradeQueryResponse.getTradeNo());
              alipayQueryResult.setTradeStatus(alipayTradeQueryResponse.getTradeStatus());
              alipayQueryResult.setSubCode(alipayTradeQueryResponse.getSubCode());
              alipayQueryResult.setSubMsg(alipayTradeQueryResponse.getSubMsg());
              return alipayQueryResult;
          } else {
              alipayQueryResult.setSubCode(alipayTradeQueryResponse.getSubCode());
              alipayQueryResult.setSubMsg(alipayTradeQueryResponse.getSubMsg());
              return alipayQueryResult;
          }
      }        
      return null;
    }

    @Override
    public Boolean orderRefundQuery(String out_trade_no, String trade_no,
            String out_request_no, String refund_amount) {
      Order order = orderManagerService.getOrderByOrderNo(out_trade_no);
      if(order!=null){
          //请求参数
          Map<String, String> maps = new HashMap<String, String>();  
          //优先选用trade_no 支付宝交易号
          if(trade_no!=null){
              maps.put("trade_no",trade_no);   
              if(out_trade_no!=null){
                  maps.put("out_trade_no", out_trade_no);  
              }
          }else{
              maps.put("out_trade_no", out_trade_no);  
          }
          maps.put("out_request_no",out_request_no);  
          AlipayTradeFastpayRefundQueryResponse AlipayTradeFastpayRefundQueryResponse = null;
          try {  
              AlipayTradeFastpayRefundQueryResponse = alipayClientFactory.orderRefundQuery(maps); 
          } catch (Exception e) {  
              e.printStackTrace();  
          } 
          //如果查询成功
          if(AlipayTradeFastpayRefundQueryResponse.isSuccess()){    
              //商家订单金额
              BigDecimal orderamount = order.getComAmount();
              Float floatValue = orderamount.floatValue();
              Float total_num=(float)(Math.round(floatValue*100)/100);
              //退款金额
              String refundAmount = AlipayTradeFastpayRefundQueryResponse.getRefundAmount();
              //查询订单总金额
              String totalAmount = AlipayTradeFastpayRefundQueryResponse.getTotalAmount();
              //如果查询结果和系统订单退款金额和订单总金额结果一致就返回true
              if(refundAmount.equals(refund_amount)&&totalAmount.equals(total_num.toString())){
                  return true;
              }
          }        
      }       
      return false;        
    }

}
