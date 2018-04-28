package com.spfood.oms.order.service.impl;
/**
 * 换货商品测试
 */
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.oms.order.dao.OrderExchangeDao;
import com.spfood.oms.order.intf.OrderExchangeService;
import com.spfood.oms.order.intf.domain.OrderExchange;
import com.spfood.oms.order.intf.domain.OrderExchangeCommodity;
import com.spfood.oms.order.intf.searchCriteria.OrderExchangeCriteria;
import com.spfood.oms.order.manager.OrderExchangeQueryManager;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath*:local-spring-environment.xml", "classpath*:spring-test-context.xml" })   
public class QueryExchangeTest extends TestCase {

	@Autowired
	private OrderExchangeQueryManager orderExchangeQueryManager; 
	@Autowired
	private OrderExchangeService orderExchangeService;
	/**
	 * 查询换货单
	 */
	@Test
	public void testQuery(){
		PageInfo<OrderExchange> os = new PageInfo<OrderExchange>(1, 20);
		OrderExchangeCriteria orderExchangeCriteria = new OrderExchangeCriteria();
		os = orderExchangeQueryManager.queryOrderExchangeByParas(orderExchangeCriteria );
		List<OrderExchange> list = os.getResult();
		for (OrderExchange orderExchange : list) {
			System.out.println(orderExchange);
		}
		System.out.println("分页。。。。。"+os);
		
		System.out.println("总条数。。。。"+os.getTotal());
	}
	
	@Test
	public void cusTest(){
		PageInfo<OrderExchange> info = new PageInfo<OrderExchange>(1, 20);
		OrderExchangeCriteria orderExchangeCriteria = new OrderExchangeCriteria();
		orderExchangeCriteria .setUsernameCode("12");
		orderExchangeCriteria.setSearch("香蕉");
		orderExchangeCriteria.setPageNum(1);
		orderExchangeCriteria.setPageSize(20);
		info = orderExchangeService.getOrderExchangeListByCriteria(orderExchangeCriteria);
		System.out.println("条数+++++++" +info.getTotal());
		List<OrderExchange> result = info.getResult();
		System.out.println(info);
		for (OrderExchange orderExchange : result) {
			System.out.println("换货单"+orderExchange);
			List<OrderExchangeCommodity> exchangeList = orderExchange.getExchangeCommodityLists();
			System.out.println("换货单对应几款商品"+exchangeList.size());
			System.out.println("换货单对应商品"+exchangeList);
//			if (exchangeList != null) {
//				int i = 0;
//				for (OrderExchangeCommodity orderExchangeCommodity : exchangeList) {
//					if(orderExchangeCommodity.getCount()!= null){
//						
//						i += orderExchangeCommodity.getCount();
//						System.out.println("count数量："+ orderExchangeCommodity.getCount());
//					}
//					System.out.println("订单商品数量"+i);
//					System.out.println(info);
//				}
//			}
		}
	}
	
	@Test
	public void getByid(){
		Long exid =40l ;
		orderExchangeService.selectOrderExchangeByExid(exid);
	}
	
}
