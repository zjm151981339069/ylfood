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
import com.spfood.wos.workOrder.intf.domain.ReceiveTask;
import com.spfood.wos.workOrder.intf.domain.SortingTask;
import com.spfood.wos.workOrder.manager.SendInfoToSCSManager;
@Component
public class SendInfoToSCSManagerImpl implements SendInfoToSCSManager{

    @Resource(name="jmsTemplate")
    JmsTemplate jmsTemplate;
    @Resource(name="scs_sort")
    Destination scs_sort;
    @Resource(name="scs_requisition")
    Destination scs_requisition;
    Logger logger = Logger.getLogger(SendInfoToSCSManagerImpl.class); 
    @Override
    public void sendSortWorkOrderToSCS(List<SortingTask> sortingTasks) throws Exception {
        for (final SortingTask sortingTask : sortingTasks) {
            jmsTemplate.send(scs_sort,new MessageCreator() {  
                @Override  
                public Message createMessage(Session session) throws JMSException {  
                    return session.createTextMessage(JSONObject.toJSONString(sortingTask));  
                }  
            });  
            logger.info("发送分拣任务单到:scs_sort   分拣任务单："+JSONObject.toJSONString(sortingTask));
        }      
    }

    @Override
    public void sendRequisitionWorkOrderToSCS(List<ReceiveTask> receiveTask) throws Exception {
        for (final ReceiveTask receiveTask2 : receiveTask) {
            jmsTemplate.send(scs_requisition,new MessageCreator() {  
                @Override  
                public Message createMessage(Session session) throws JMSException {  
                    return session.createTextMessage(JSONObject.toJSONString(receiveTask2));  
                }  
            }); 
            logger.info("发送领料任务单到：scs_requisition  领料任务单："+JSONObject.toJSONString(receiveTask2));
        }
    }

}
