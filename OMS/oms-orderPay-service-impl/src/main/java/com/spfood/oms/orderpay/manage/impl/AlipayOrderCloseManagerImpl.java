package com.spfood.oms.orderpay.manage.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipay.api.response.AlipayTradeCloseResponse;
import com.spfood.oms.orderpay.intf.domain.AlipayProcessMessage;
import com.spfood.oms.orderpay.manage.AlipayOrderCloseManager;
import com.spfood.oms.orderpay.pay.alipay.AlipayClientFactory;
@Component
public class AlipayOrderCloseManagerImpl implements AlipayOrderCloseManager{
    @Autowired
    private AlipayClientFactory alipayClientFactory;
    @Override
    public AlipayProcessMessage alipayOrderClose(String out_trade_no,
            String trade_no) {
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
        AlipayTradeCloseResponse   alipayTradeCloseResponse=null;
        try {   
            alipayTradeCloseResponse = alipayClientFactory.orderClose(maps); 
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        //获取处理结果
        if(alipayTradeCloseResponse!=null){
            AlipayProcessMessage alipayProcessMessage = new AlipayProcessMessage();
            alipayProcessMessage.setCode(alipayTradeCloseResponse.getCode());
            alipayProcessMessage.setMsg(alipayTradeCloseResponse.getMsg());
            alipayProcessMessage.setSubCode(alipayTradeCloseResponse.getSubCode());
            alipayProcessMessage.setSubMsg(alipayTradeCloseResponse.getSubMsg());
            if(alipayTradeCloseResponse.isSuccess()){
                alipayProcessMessage.setIsSuccess(true);
            } else {
                alipayProcessMessage.setIsSuccess(false);
            }
            return alipayProcessMessage;
        }
        return null;
     
    }

}
