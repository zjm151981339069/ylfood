package com.spfood.oms.orderRetrieve.service.impl;



import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderCommodity;
import com.spfood.oms.order.intf.searchCriteria.OrderSearchCriteria;
import com.spfood.oms.orderRetrieve.intf.OrderRetrieveService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:local-spring-environment.xml", "classpath*:spring-test-context.xml" })   
public class OrderRetrieveTest extends TestCase{

	@Autowired
	private OrderRetrieveService orderRetrieveService;
	
	@Test
	public void getOrderByPropertyTest(){
		
		OrderSearchCriteria orderPar = new OrderSearchCriteria();
		orderPar.setPageNum(1);
		orderPar.setPageSize(10);
		orderPar.setCustomerCode("574");
		PageInfo<Order> page = orderRetrieveService.getOrderPageByParam(orderPar);
		System.out.println("!!!==="+page);
		List<Order> dd = page.getResult();
		for (Order order : dd) {
			System.out.println("===!"+order.getType());
			
		}

	}
	
	
	@Test
	public void getOrderDetailByOrderNoTest(){
		String orderno = "bb993d0a90f44b499565f43e228f86da";
		Order order = orderRetrieveService.getOrderDetailByOrderNo(orderno);
		if(order!=null){
			System.out.println("1223|"+order.getOrderCommList().size());
			List<OrderCommodity> p= order.getOrderCommList();
			for (OrderCommodity orderCommodity : p) {
				System.out.println("1223|"+orderCommodity.getPictureUrl());
			}
			
		}
		
	}
	
	
	@Test
	public void getOrderCountByDateTest(){
		OrderSearchCriteria order = new OrderSearchCriteria();
//		Date date = new Date();
//		order.setStartDate(date);
//		order.setEndDate(date);
		order.setStatus(1);
		orderRetrieveService.getOrderCountByParam(order);
	}
	
	
	
}
