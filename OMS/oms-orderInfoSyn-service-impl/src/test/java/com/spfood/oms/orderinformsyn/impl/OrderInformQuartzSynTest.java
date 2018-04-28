//package com.spfood.oms.orderinformsyn.impl;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.spfood.oms.orderStatusSyn.intf.OrderStatusService;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath*:local-spring-environment.xml", "classpath*:spring-test-context.xml","classpath*:spring-quartz.xml" })
//public class OrderInformQuartzSynTest {
//    
//    @Autowired
//    private OrderStatusService orderStatusService;
//    
//    /**
//     * 测试定时调度，1分钟执行一次，线程休眠2分钟。
//     */
//    @Test 
//    public void StartOrderInformServer(){
//       System.out.println("启动定时信息同步任务");
//       try {
//        Thread.currentThread();
//        //系统运行2分钟
//        Thread.sleep(1000*60*2);
//    } catch (InterruptedException e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//    }
//    }
//
//    
//    
//    
//
//}
