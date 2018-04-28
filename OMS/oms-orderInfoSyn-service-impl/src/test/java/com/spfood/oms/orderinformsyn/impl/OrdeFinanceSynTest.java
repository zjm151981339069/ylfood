package com.spfood.oms.orderinformsyn.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderCommodity;
import com.spfood.oms.order.intf.domain.OrderPay;
import com.spfood.oms.orderinfosyn.intf.OrderFinanceSynService;
//import com.spfood.oms.orderinfosyn.quartz.ordersyn.impl.OrderPaySynImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:local-spring-environment.xml", "classpath:spring-test-context.xml","classpath:spring-test-mq.xml","classpath:spring-test-quartz.xml" })   
public class OrdeFinanceSynTest {
    @Autowired
    private OrderFinanceSynService orderFinanceSynService;
    
    private OrderPay orderPay = new OrderPay();
    
    /**
     * 订单支付信息同步测试，睡眠2分钟，测试调度频率为1分钟一次
     */
    @Test
    public void orderPayInfoToFmsTest(){
    	try {
			Thread.currentThread().sleep(1000*60*2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    
    
    /**
     * 测试支付信息同步
     */
    @Test 
    public void orderPaySyn(){
    	
    	List<OrderPay> list = new ArrayList<OrderPay>();
    	orderPay.setPayId(22L);
    	orderPay.setOrderNo("MMMDDD100");
    	orderPay.setIsPay(0);
    	System.out.println("支付信息：：："+orderPay);
    	list.add(orderPay);
		boolean i = orderFinanceSynService.orderPaySyn(list );
		System.out.println("同步结果：：："+i);
    }
    
    /**
     * 测试发票同步
     */
    @Test
    public void orderBillSyn(){
    	BigDecimal bigDecimal = new BigDecimal(6666);
    	boolean orderBillSyn = orderFinanceSynService.orderBillSyn("kkk", 1, 1, "xxx公司",bigDecimal);
    	System.out.println("发票同步结果"+orderBillSyn);
    }
    
    @Test 
    public void SendToWosTest2(){
        List<String> orderNos= new ArrayList<String>();
        orderNos.add("XXXDDDD12345");
        System.out.println("ok");
        List<Order> orderListByOrderNoList = new ArrayList<Order>();
        Order orderByOrderNo = new Order();
        orderByOrderNo.setOrderNo("XXXDDDD12345");
        //Order orderByOrderNo = orderManagerService.getOrderByOrderNo("d2182w942bw1jc9fjcdd741Tbb6k9q4f");
        List<OrderCommodity> orderCommList=new ArrayList<OrderCommodity>();
        OrderCommodity orderCommodity = new OrderCommodity();
        orderCommodity.setCode("code45");
        orderCommodity.setName("name45");
        orderCommodity.setAreaCode("2");
        orderCommodity.setAreaName("area2");
        orderCommodity.setOrderNo("XXXDDDD12345");
        orderCommodity.setCount(5);
        orderCommList.add(orderCommodity);
        OrderCommodity orderCommodity1 = new OrderCommodity();
        orderCommodity1.setCode("code10");
        orderCommodity1.setName("name10");
        orderCommodity1.setAreaCode("11");
        orderCommodity1.setAreaName("are0");
        orderCommodity1.setOrderNo("XXXDDDD12345");
        orderCommodity1.setCount(6);
        orderCommList.add(orderCommodity1);
        OrderCommodity orderCommodity2 = new OrderCommodity();
        orderCommodity2.setCode("code12");
        orderCommodity2.setName("name12");
        orderCommodity2.setAreaCode("6");
        orderCommodity2.setAreaName("area6");
        orderCommodity2.setOrderNo("XXXDDDD12345");
        orderCommodity2.setCount(2);
        orderCommList.add(orderCommodity2);
        orderByOrderNo.setOrderCommList(orderCommList);
        orderListByOrderNoList.add(orderByOrderNo);
        System.out.println(orderListByOrderNoList.get(0).getOrderCommList().size());
        long start = System.currentTimeMillis();     
//        orderInfoSynService.setOrderToDeliverInfoSyn(orderListByOrderNoList);
        long end = System.currentTimeMillis();
    }
}
