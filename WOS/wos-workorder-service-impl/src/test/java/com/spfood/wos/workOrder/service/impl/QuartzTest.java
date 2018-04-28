//package com.spfood.wos.workOrder.service.impl;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.spfood.wos.workOrder.utils.QuartzJob;
//import com.spfood.wos.workOrder.utils.QuartzManager;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:spring-quartz.xml" })   
//public class QuartzTest extends BaseTest{
//
//	@Autowired
//	private QuartzManager quartzManager;
//	@Test
//	public void test1(){
//		System.out.println("=========================");
//		QuartzJob quartzJob = new QuartzJob();
//		quartzJob.setTriggerName("createWorkOrderTrigger");
//		quartzJob.setCronExpression("0 */8 * * * ?");
//		quartzManager.updateTrigger(quartzJob);
//	}
//}
