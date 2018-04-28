package com.spfood.oms.orderinfosyn.manager.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderBill;
import com.spfood.oms.order.intf.domain.OrderPay;
import com.spfood.oms.orderinfosyn.manager.SendOrderMessageQueueManager;
@Service
public class SendOrderMessageQueueManagerImpl implements SendOrderMessageQueueManager{
    @Resource(name="jmsTemplate")
    JmsTemplate jmsTemplate;
    @Resource(name="wos")
    Destination wos;
    @Resource(name="fms")
    Destination fms;
    @Resource(name="ts")
    Destination ts;
    Logger logger = Logger.getLogger(SendOrderMessageQueueManagerImpl.class); 
   
    @Override
    public void sendOrderImformToTS(List<Order> orders) {
      //list参数判断不为空&&长度不为0
        if (orders!=null&&orders.size()!=0) {
            for (final Order order : orders) {
                jmsTemplate.send(ts,new MessageCreator() {  
                    @Override  
                    public Message createMessage(Session session) throws JMSException {  
                        return session.createTextMessage(JSONObject.toJSONString(order));  
                    }  
                });  
                logger.info("发送订单到：ts    订单信息"+JSONObject.toJSONString(order));
            }
        } 
        
    }
    @Override
    public void sendOrderImformToWOS(List<Order> orders) {
        if (orders!=null&&orders.size()!=0) {
            for (final Order order : orders) {
                jmsTemplate.send(wos,new MessageCreator() {  
                    @Override  
                    public Message createMessage(Session session) throws JMSException {  
                        return session.createTextMessage(JSONObject.toJSONString(order));  
                    }  
                });
                logger.info("发送订单到：wos    订单信息"+JSONObject.toJSONString(order));
            }
        }      
    }


    @Override
    public void sendOrderImformToFMS(List<Order> orders) {
        if (orders!=null&&orders.size()!=0) {
            for (final Order order : orders) {
                jmsTemplate.send(fms,new MessageCreator() {  
                    @Override  
                    public Message createMessage(Session session) throws JMSException {  
                        return session.createTextMessage(JSONObject.toJSONString(order));  
                    }  
                }); 
                logger.info("发送订单到：fms    订单信息"+JSONObject.toJSONString(order));
            }
        }      
        
    }
    @Override
    public boolean sendOrderPayInfoToFMS(List<OrderPay> orderPays) {
        if(orderPays!=null&&orderPays.size()>0){
            for (final OrderPay orderPay : orderPays) {
                jmsTemplate.send(fms,new MessageCreator() {  
                    @Override  
                    public Message createMessage(Session session) throws JMSException {  
                        return session.createTextMessage(JSONObject.toJSONString(orderPay));  
                    }  
                }); 
                logger.info("发送订单支付信息到：fms    订单支付信息"+JSONObject.toJSONString(orderPay));
                return true;
            }
        }
		return false;
        
    }
    @Override
    public boolean sendOrderBillToFMS(final OrderBill orderBill) {
        if(orderBill!=null){
            jmsTemplate.send(fms,new MessageCreator() {  
                @Override  
                public Message createMessage(Session session) throws JMSException {  
                    return session.createTextMessage(JSONObject.toJSONString(orderBill));  
                }  
            }); 
            logger.info("发送订单发票信息到：fms    订单发票信息"+JSONObject.toJSONString(orderBill));
            return true;
        }
		return false;
        
    }
}
