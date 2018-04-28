package com.spfood.oms.cart.service.impl;



import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spfood.oms.cart.intf.CartService;
import com.spfood.oms.cart.intf.domain.CartCommodity;
import com.spfood.oms.cart.intf.domain.Cart;


@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath*:local-spring-environment.xml", "classpath*:spring-test-context.xml" })   
public class CartCommodityTest extends TestCase{

	@Autowired
	private CartService cartService;
	
	
	
	@Test
	public void addCartCommodityTest(){
		String customerCode = "e8f1ecb72dbe9c1b5ab646de96dc22f3";
		CartCommodity commodity = new CartCommodity();
		commodity.setComCode("YLC0000000097");
		commodity.setAmount(10);
		commodity.setPictureUrl("/ddf/fdfd.jpg");
		Long i = cartService.addCartCommodity(customerCode,commodity);
		System.out.println("|||"+i);
	}
	
	@Test
	public void deleteCommodityByCodeTest(){
		String[] comCodes = {"YLC0000000041","YLC00000000361"};
		String customerCode = "694341ac1673d77fc31c008fe804acb3";
		Long i = cartService.removeCommodityByCode(customerCode,comCodes);
		System.out.println("|||"+i);
	}
	
	
	@Test
	public void updateCommodityTest(){
		CartCommodity commodity = new CartCommodity();
		commodity.setComCode("YLC0000000039");
		commodity.setAmount(900);
		String customerCode = "694341ac1673d77fc31c008fe804acb3";
		Long i = cartService.updateCommodityQuantity(customerCode,commodity);
		System.out.println("13fe2323-"+i);
		
	}
	

	@Test
	public void getCartCommodityListTest(){
		String customerCode = "176282831101111";
		List<Cart> ls = cartService.getCartCommodityList(customerCode);
		for (Cart cartCommodity : ls) {
			System.out.println("!@"+cartCommodity.getCommodityList().get(0).getPictureUrl());
		}
	}
	
	
	@Test
	public void getCartCommodityTotalTest(){
		String customerCode = "63";
		Long l = cartService.getCartCommodityTotal(customerCode);
		System.out.println("!@"+l);
	}

	
	@Test
	public void mergeCartCommodityTest(){
		String customerCode = "3131c008fe804acb3";
		CartCommodity cartCommodity = new CartCommodity();
		CartCommodity cartCommodity2 = new CartCommodity();
		List<CartCommodity> commodity = new ArrayList<CartCommodity>();
		cartCommodity.setComCode("YLC0000000095");
		cartCommodity.setAmount(2);
		cartCommodity.setPictureUrl("/ddf/fdfd.jpg");
		cartCommodity2.setComCode("req10211");
		cartCommodity2.setAmount(2);
		cartCommodity2.setPictureUrl("/ddf/fdfd.jpg");
		commodity.add(cartCommodity);
		commodity.add(cartCommodity2);
		cartService.mergeCartCommodity(customerCode,commodity);
	}
	
	
}
