//package com.spfood.oms.orderinfosyn.manager.impl;
//
//import java.util.List;
//
//import javax.jms.DeliveryMode;
//import javax.jms.Destination;
//import javax.jms.MessageProducer;
//import javax.jms.Session;
//import javax.jms.TextMessage;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.alibaba.fastjson.JSONObject;
//import com.spfood.oms.order.intf.domain.OrderPay;
//import com.spfood.oms.orderinfosyn.manager.SendOrderFms;
//import com.spfood.oms.orderinfosyn.utils.MqSessionUtils;
//@Service
//public class SendOrderFmsImpl implements SendOrderFms{
//    
//    @Autowired
//    private MqSessionUtils mqsession;
//    @Override
//    public void sendOrderPaysToFMS(List<OrderPay> orderPays, String destination) {
//      //list参数判断不为空&&长度不为0
//        if (orderPays!=null&&orderPays.size()!=0) {
//            Session session = MqSessionUtils.getSession();
//            Destination mqdestination;
//            // MessageProducer：消息发送者
//            MessageProducer producer;
//            try {
//                mqdestination = session.createQueue(destination);
//                // 得到消息生成者 -------发送者
//                producer = session.createProducer(mqdestination);
//                // 设置持久化
//                producer.setDeliveryMode(DeliveryMode.PERSISTENT);
//                for (OrderPay orderPay : orderPays) {
//                    String jsonString = JSONObject.toJSONString(orderPay);
//                    TextMessage message = session
//                            .createTextMessage(jsonString);
//                    producer.send(message);
//                    System.out.println("消息目的地： "+destination+"   订单信息: "+orderPay.getPayId());
//                }
//                session.commit();      
//            } catch (Exception e) {
//                e.printStackTrace();
//            }finally{
//                MqSessionUtils.closeSession(session);
//            }
//        } 
//    }
//
//    public void sendWhatever(Object object, String destination) {
//        //list参数判断不为空
//          if (object!=null) {
//              Session session = MqSessionUtils.getSession();
//              Destination mqdestination;
//              // MessageProducer：消息发送者
//              MessageProducer producer;
//              try {
//                  mqdestination = session.createQueue(destination);
//                  // 得到消息生成者 -------发送者
//                  producer = session.createProducer(mqdestination);
//                  // 设置持久化
//                  producer.setDeliveryMode(DeliveryMode.PERSISTENT);
//                      String jsonString = JSONObject.toJSONString(object);
//                      TextMessage message = session
//                              .createTextMessage(jsonString);
//                      producer.send(message);
//                      System.out.println("消息目的地： "+destination+"   发票信息: "+object.toString());
//                  session.commit();      
//              } catch (Exception e) {
//                  e.printStackTrace();
//              }finally{
//                  MqSessionUtils.closeSession(session);
//              }
//          } 
//      }
//    
//}
