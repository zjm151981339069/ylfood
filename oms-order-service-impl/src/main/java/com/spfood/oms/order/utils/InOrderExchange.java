package com.spfood.oms.order.utils;

import java.math.BigDecimal;

import com.spfood.kernel.domain.DomainObject;

/**
 * 换货单常量
 * @author Administrator
 *
 */
public class InOrderExchange {

	
	//换货商品单价设置为0
	public static BigDecimal CHANGE_ORDER_PRICE = new BigDecimal(0);
	
	//换货单执行价格设置为0
	public static BigDecimal CHANGE_ORDER_ACTPRICE = new BigDecimal(0);
	
	//换货单商品金额设置为0
	public static BigDecimal CHANGE_ORDER_SUBTOTAL = new BigDecimal(0);
	
	//换货单商品折扣金额设置为0
	public static BigDecimal CHANGE_ORDER_DISCOUNT = new BigDecimal(0);
	
	//换货单订单总金额设置为0
	public static BigDecimal CHANGE_ORDER_AMOUNT = new BigDecimal(0);
	
	//换货单商品总金额设置为0
	public static BigDecimal CHANGE_ORDER_COMAMOUNT = new BigDecimal(0);
	
	//换货单运费设置为0
	public static BigDecimal CHANGE_ORDER_CARRIAGE =new BigDecimal(0);
	
	//换货单提货方式自提
	public static String CHANGE_ORDER_SITE = "自提";
	
	
	
}
