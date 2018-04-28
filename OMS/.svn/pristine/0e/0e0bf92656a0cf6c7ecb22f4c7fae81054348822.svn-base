package com.spfood.oms.orderpay.manage.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipay.api.response.AlipayTradeRefundResponse;
import com.spfood.oms.order.intf.OrderManagerService;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.orderpay.intf.domain.AlipayProcessMessage;
import com.spfood.oms.orderpay.manage.AlipayRefundManager;
import com.spfood.oms.orderpay.pay.alipay.AlipayClientFactory;
@Component
public class AlipayRefundManagerImpl implements AlipayRefundManager{
    @Autowired
    private OrderManagerService orderManagerService;
    @Autowired
    private AlipayClientFactory alipayClientFactory;
    @Override
    public AlipayProcessMessage orderRefundByAlipay(String out_trade_no, String trade_no,
            BigDecimal refund_amount, String out_request_no) {
      Order order = orderManagerService.getOrderByOrderNo(out_trade_no);

      if(order!=null){
          //转化支付的金额为两位小数点的浮点数
          Float floatValue = refund_amount.floatValue();
          Float total_num=(float)(Math.round(floatValue*100)/100);
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
          maps.put("refund_amount ", total_num.toString());  
          //退款请求单号
          maps.put("out_request_no", out_request_no);
          AlipayTradeRefundResponse  alipayTradeRefundResponse=null;
          try {  
              alipayTradeRefundResponse = alipayClientFactory.TradeRefund(maps); 
          } catch (Exception e) {  
              e.printStackTrace();  
          }
          AlipayProcessMessage alipayProcessMessage = new AlipayProcessMessage();
          alipayProcessMessage.setCode(alipayTradeRefundResponse.getCode());
          alipayProcessMessage.setMsg(alipayTradeRefundResponse.getMsg());
          alipayProcessMessage.setSubCode(alipayTradeRefundResponse.getSubCode());
          alipayProcessMessage.setSubMsg(alipayTradeRefundResponse.getSubMsg());
          if(alipayTradeRefundResponse.isSuccess()){
              alipayProcessMessage.setIsSuccess(true);
          } else {
              alipayProcessMessage.setIsSuccess(false);
          }
          return alipayProcessMessage;
      }       
      return null;
    }

}
