//package com.spfood.oms.orderinformsyn.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.spfood.oms.order.intf.OrderManagerService;
//import com.spfood.oms.order.intf.domain.Order;
//import com.spfood.oms.order.intf.domain.OrderCommodity;
//import com.spfood.oms.orderinfosyn.intf.OrderInfoSynService;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath*:local-spring-environment.xml", "classpath*:spring-test-context.xml" })   
//public class OrderInformSynTest {
//    @Autowired
//    private OrderInfoSynService orderInformSynService;
//    @Autowired
//    private OrderManagerService orderManagerService;
//    
//    /**
//     * 测试立即发货
//     */
////    @Test 
////    public void SendToWmsTest(){
////        String list= "0f6f6d5826974ed18460754ecd2abbe0";
////        orderInformSynService.setOrderToDeliverInfoSyn(list);
////    }
//    
//    @Test 
//    public void SendToWosTest2(){
//        List<String> orderNos= new ArrayList<String>();
//        orderNos.add("d2182w942bw1jc9fjcdd741Tbb6k9q4f");
//        System.out.println("ok");
//        List<Order> orderListByOrderNoList = new ArrayList<Order>();
//        Order orderByOrderNo = orderManagerService.getOrderByOrderNo("d2182w942bw1jc9fjcdd741Tbb6k9q4f");
//        List<OrderCommodity> orderCommList=new ArrayList<OrderCommodity>();
//        OrderCommodity orderCommodity = new OrderCommodity();
//        orderCommodity.setCode("code45");
//        orderCommodity.setName("name45");
//        orderCommodity.setAreaCode("2");
//        orderCommodity.setAreaName("area2");
//        orderCommodity.setOrderNo("d2182w942bw1jc9fjcdd741Tbb6k9q4f");
//        orderCommodity.setCount(5);
//        orderCommList.add(orderCommodity);
//        OrderCommodity orderCommodity1 = new OrderCommodity();
//        orderCommodity1.setCode("code10");
//        orderCommodity1.setName("name10");
//        orderCommodity1.setAreaCode("0");
//        orderCommodity1.setAreaName("are0");
//        orderCommodity1.setOrderNo("d2182w942bw1jc9fjcdd741Tbb6k9q4f");
//        orderCommodity1.setCount(6);
//        orderCommList.add(orderCommodity1);
//        OrderCommodity orderCommodity2 = new OrderCommodity();
//        orderCommodity2.setCode("code12");
//        orderCommodity2.setName("name12");
//        orderCommodity2.setAreaCode("6");
//        orderCommodity2.setAreaName("area6");
//        orderCommodity2.setOrderNo("d2182w942bw1jc9fjcdd741Tbb6k9q4f");
//        orderCommodity2.setCount(2);
//        orderCommList.add(orderCommodity2);
//        orderByOrderNo.setOrderCommList(orderCommList);
//        orderListByOrderNoList.add(orderByOrderNo);
//        System.out.println(orderListByOrderNoList.get(0).getOrderCommList().size());
//        long start = System.currentTimeMillis();     
////        orderInformSynService.setOrderToDeliverInfoSyn(orderListByOrderNoList);
//        long end = System.currentTimeMillis();
//    }
//}
//
