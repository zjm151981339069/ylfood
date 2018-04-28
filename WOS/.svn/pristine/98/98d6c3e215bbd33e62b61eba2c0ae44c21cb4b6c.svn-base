//package com.spfood.wos.workOrder.service.impl;
//
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//import junit.framework.TestCase;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.spfood.wos.workOrder.intf.HouseOrderService;
//import com.spfood.wos.workOrder.intf.domain.HouseOrder;
//import com.spfood.wos.workOrder.intf.domain.HouseOrderCommodity;
//import com.spfood.wos.workOrder.intf.domain.HouseOrderTemp;
//
//
//
//@RunWith(SpringJUnit4ClassRunner.class) 
//@ContextConfiguration(locations = { "classpath*:local-spring-environment.xml", "classpath*:spring-test-context.xml" })   
//public class HouseOrderTest extends TestCase {
//
//	@Autowired
//	private HouseOrderService houseOrderService;
//	
//	@Test
//	public void saveHouseOrder(){
//		
//		HouseOrder houseOrder = new HouseOrder();
//		//订单
//		houseOrder.setOrderId(null);
//		houseOrder.setOrderNo("MOD9999");
//		houseOrder.setReceiver("james");
//		houseOrder.setPhone("18812345678");
//		List<HouseOrderCommodity> orderCommList = new ArrayList<HouseOrderCommodity>();
//		HouseOrderCommodity commodity = new HouseOrderCommodity();
//		//商品
//		commodity.setComId(null);
//		commodity.setOrderNo(houseOrder.getOrderNo());
//		commodity.setCode("ss99");
//		commodity.setCount(100);
//		orderCommList.add(commodity);
//		houseOrder.setOrderCommList(orderCommList);
//		houseOrderService.saveOrder(houseOrder );
//	}
//	
//	@Test
//	public void query(){
//		List<HouseOrderTemp> tempOrder = houseOrderService.getTempOrder(2);
//		System.out.println(tempOrder.size());
//	}
//}
//
//	