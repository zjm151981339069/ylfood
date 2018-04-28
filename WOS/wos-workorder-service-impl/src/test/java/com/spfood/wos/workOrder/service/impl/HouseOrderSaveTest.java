//package com.spfood.wos.workOrder.service.impl;
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
//import com.spfood.wos.workOrder.manager.HouseOrderManager;
//
//
//@RunWith(SpringJUnit4ClassRunner.class) 
//@ContextConfiguration(locations = { "classpath*:local-spring-environment.xml", "classpath*:spring-test-context.xml" })   
//public class HouseOrderSaveTest extends TestCase {
//
//
//	@Autowired(required=true)
//	private HouseOrderService houseOrderService;
//	private HouseOrder houseOrder = new HouseOrder();
//	@Autowired
//	private HouseOrderManager houseOrderManager;
//	
//	/**
//	 * 测试入库订单
//	 */
//	@Test
//	public void testOrderSave() {
//		 
//		houseOrder.setOrderNo("YYY2222");
//		houseOrder.setReceiver("James");
//		List<HouseOrderCommodity> orderCommList = new ArrayList<HouseOrderCommodity>();
//		HouseOrderCommodity e = new HouseOrderCommodity();
//		e.setOrderNo("YYY2222");
//		e.setCount(200);
//		e.setName("ppppp");
//		orderCommList.add(e);
//		houseOrder.setOrderCommList(orderCommList );
//		houseOrderService.saveOrder(houseOrder);
//	}
//	@Test
//	public void testOrderTemp() {
//	    Integer status=2;
//        List<HouseOrderTemp> tempOrder = houseOrderService.getTempOrder(status);
//        int size = tempOrder.size();
//        System.out.println("临时订单的数量为:"+size);
//	}
//	@Test
//	public void testOrderQuery() {
//	    HouseOrder order = houseOrderService.getOrder("cccc2702");
//	    List<HouseOrderCommodity> orderCommList = order.getOrderCommList();
//	    System.out.println("得到的订单单==="+order);
//	    System.out.println("得到的订单shangp ==="+orderCommList);
//	    
//	}
//	
//	@Test
//	public void updateHouseOrder(){
//		List<String> orderNos= new ArrayList<String>();
//		orderNos.add("cccc2714");
//		orderNos.add("cccc2715");
//		orderNos.add("cccc2716");
//		orderNos.add("cccc2717");
//		orderNos.add("cccc2718");
//		//houseOrderService.updateToHasSorting(orderNos);
//		houseOrderService.updateToAbnormalStatus(orderNos);
//		List<HouseOrder> list = houseOrderManager.queryHouseOrderListByOrderNos(orderNos);
//		for (HouseOrder houseOrder : list) {
//			
//			System.out.println("循环状态:::"+houseOrder.getStatus());
//		}
//		
//	}
//	
//	
//
//}