package com.spfood.oms.order.service.impl;
/**
 * 换货商品测试
 */

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spfood.oms.order.dao.OrderExchangeDao;
import com.spfood.oms.order.intf.OrderExchangeService;


@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath*:local-spring-environment.xml", "classpath*:spring-test-context.xml" })   
public class NewExchangeTest extends TestCase {

	@Autowired
	private OrderExchangeService orderExchangeService;
	@Autowired
	private OrderExchangeDao orderExchangeDao;
	
	@Test
	public void selectExchange(){
		orderExchangeDao.selectById(79);
	}
}
