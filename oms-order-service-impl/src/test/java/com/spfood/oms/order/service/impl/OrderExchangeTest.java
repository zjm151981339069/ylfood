package com.spfood.oms.order.service.impl;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spfood.oms.order.intf.OrderCreateService;
import com.spfood.oms.order.intf.OrderExchangeService;
import com.spfood.oms.order.intf.OrderManagerService;
import com.spfood.oms.order.intf.domain.ExchangePicture;
import com.spfood.oms.order.intf.domain.OrderExchange;
import com.spfood.oms.order.intf.domain.OrderExchangeCommodity;
import com.spfood.oms.order.manager.OrderExchangeCreateManager;
import com.spfood.oms.order.manager.OrderExchangeManage;

/**
 * 生成换货单测试
 */
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath*:local-spring-environment.xml", "classpath*:spring-test-context.xml" })   
public class OrderExchangeTest extends TestCase {

	@Autowired
	private OrderExchangeManage orderExchangeManage;
	@Autowired
	private OrderManagerService orderManagerService;
	@Autowired
	private OrderCreateService orderCreateService;
	@Autowired
	private OrderExchangeService orderExchangeService;
	@Autowired
	private OrderExchangeCreateManager orderExchangeCreateManager;

	//生成换货单
	@Test
	public void testInsert(){
		OrderExchange orderExchange = new OrderExchange();
		orderExchange.setDealStyle(1);
		List<OrderExchangeCommodity> exchangeList = new ArrayList<OrderExchangeCommodity>();
		OrderExchangeCommodity product = new OrderExchangeCommodity();
		
		//换货单商品
		product.setCode("mm100");
		product.setName("xxxooo");
		product.setCount(3);
		OrderExchangeCommodity p = new OrderExchangeCommodity();
		
		//换货单商品
		p.setCode("gg200");
		p.setName("NB");
		p.setCount(66);
		//换货单商品对应图片
		List<ExchangePicture> pictures = new ArrayList<ExchangePicture>();
		ExchangePicture pic = new ExchangePicture();
		pic.setUrl("http://imgsrc.baidu.com/forum/pic/item/a8ec8a13632762d006deaa12a0ec08fa503dc6bf.jpg");
		pictures.add(pic );
		product.setPictureUrl(pictures );
		exchangeList.add(product );
		exchangeList.add(p);
		orderExchange.setExchangeCommodityLists(exchangeList);
		orderExchange.setReceiver("44444");
		System.out.println(orderExchange);
		//orderExchangeCreateManager.createOrderExchange("99999999", orderExchange);
		orderExchangeService.OrderExchangeCreate("99999999", orderExchange);
	}
	
	//测试审核通过
	@Test
	public void passOrderExchange() throws Exception{
		OrderExchange orderExchange = orderExchangeService.selectOrderExchangeByExid(59L);
		orderExchange.setAuditview("tongyi");
		List<OrderExchangeCommodity> exchangeList = orderExchange.getExchangeCommodityLists();
		System.out.println(exchangeList);
		boolean b = orderExchangeService.passOrderExchangeByExid(orderExchange );
		System.out.println(b);
	}
	
}
