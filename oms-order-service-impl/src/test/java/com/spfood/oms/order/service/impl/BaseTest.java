package com.spfood.oms.order.service.impl;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spfood.oms.order.intf.OrderCreateService;
import com.spfood.oms.order.intf.OrderManagerService;


@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration( {"classpath:local-spring-environment.xml","classpath:spring-test-context.xml"})   
public class BaseTest {

	@Autowired
	protected OrderManagerService orderManagerService;
	@Autowired
	protected OrderCreateService orderCreateService;
}
