package com.spfood.wos.workOrder.utils;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.spfood.wos.workOrder.intf.domain.HouseOrder;
import com.spfood.wos.workOrder.manager.HouseOrderManager;
@Component
@Transactional
public class OmsOrderMqListener implements SessionAwareMessageListener{
    @Autowired
    private HouseOrderManager houseOrderManager;
    Logger logger = Logger.getLogger(OmsOrderMqListener.class); 
    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        TextMessage tm = (TextMessage) message;  
        try {  
            //得到队列中的消息
           JSONObject object = JSON.parseObject(tm.getText());
           HouseOrder houseOrder = object.toJavaObject(HouseOrder.class);
           System.out.println(object);
           //得到object转为houseOrder
           logger.info("接收订单信息***********"+object);
//           HouseOrder houseOrder = object.getObject("order", HouseOrder.class);
           System.out.println("接收到的对象：："+houseOrder);
           houseOrderManager.saveOrder(houseOrder);
           logger.info("订单信息:"+houseOrder);
        } catch (JMSException e) {  
            e.printStackTrace();  
        }  
        
    }  

}
