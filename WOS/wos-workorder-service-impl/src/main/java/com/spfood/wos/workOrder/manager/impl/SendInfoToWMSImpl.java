package com.spfood.wos.workOrder.manager.impl;

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

import com.alibaba.fastjson.JSONObject;
import com.spfood.wos.workOrder.intf.domain.OutTask;
import com.spfood.wos.workOrder.manager.SendInfoToWMSManager;
@Component
public class SendInfoToWMSImpl implements SendInfoToWMSManager{
    @Resource(name="jmsTemplate")
    JmsTemplate jmsTemplate;
    @Resource(name="wms")
    Destination wms;
    Logger logger = Logger.getLogger(SendInfoToWMSImpl.class); 
    @Override
    public void sendDeliveWorkOrderToWMS(List<OutTask> outTasks) throws Exception { 
            for (final OutTask outTask : outTasks) {
                jmsTemplate.send(wms,new MessageCreator() {  
                    @Override  
                    public Message createMessage(Session session) throws JMSException {  
                        return session.createTextMessage(JSONObject.toJSONString(outTask));  
                    }  
                });  
                logger.info("发送出库任务单到:wms   出库任务单："+JSONObject.toJSONString(outTask));
            }
    }

}
