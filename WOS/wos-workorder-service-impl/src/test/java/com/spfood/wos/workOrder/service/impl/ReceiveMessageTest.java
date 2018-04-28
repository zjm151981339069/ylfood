//package com.spfood.wos.workOrder.service.impl;
//
//
//import javax.jms.Queue;
//
//import junit.framework.TestCase;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.spfood.wos.workOrder.intf.ReceiveOrderService;
//
//
//
//@RunWith(SpringJUnit4ClassRunner.class) 
//@ContextConfiguration(locations = { "classpath*:local-spring-environment.xml", "classpath*:spring-test-context.xml","classpath*:spring-test-mq.xml" })   
//public class ReceiveMessageTest extends TestCase {
//
//
//	@Autowired(required=true)
//	private ReceiveOrderService receiveMessage;
//	@Test
//	public void testOrderStatusReceive() {
//		 
//		Queue wos = null;
//		receiveMessage.receiveOrder(wos);
//	}
//	
//}
//
//	