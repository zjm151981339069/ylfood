package com.spfood.oms.order.service.impl;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.oms.order.intf.domain.CreateOrderMessage;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderCommodity;
import com.spfood.oms.order.intf.domain.OrderLog;
import com.spfood.oms.order.intf.domain.OrderPay;
import com.spfood.oms.order.intf.searchCriteria.OrderSearchCriteria;
import com.spfood.oms.orderinfosyn.intf.OrderInfoSynService;

public class OrderCreateServiceTest extends BaseTest{
	@Autowired
	private ReloadableResourceBundleMessageSource  messageSource;
	@Autowired
	private OrderInfoSynService orderInfoSynService;
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testAddOrder() {
		Order order = orderManagerService.getOrderDetail("a1b2e93ebc2f4a949f756a1bbf832cf3");
		order.setType(null);
		
		List<OrderCommodity> products = new ArrayList<OrderCommodity>();
		OrderCommodity commodity1 = new OrderCommodity();
		commodity1.setCode("YLC0000000297");
		commodity1.setCount(2);
		products.add(commodity1);
		OrderCommodity commodity2 = new OrderCommodity();
		commodity2.setCode("YLC0000000298");
		products.add(commodity2);
		commodity2.setCount(2);
		
		order.setOrderCommList(products);
		order.setRepCustomer("repUser");
		order.setCustomer("购货人");
		order.setCustomerCode("gouhuoren");
		order.setRepCustomer("代购人");
		order.setRepCustomerCode("daigouren");
		order.setReceiver("收货人");
		order.setReceiverCode("shouhuoren");
		order.setModifierCode("222");
		order.setComAmount(new BigDecimal(456.00));
		order.setDiscount(new BigDecimal(4.44));
		order.setCarriage(new BigDecimal(10.00));
		order.setSiteCode("adsf");
		order.setCityCode("cityCode");
		order.setCityName("cityName");
		OrderPay orderPay = new OrderPay();
		orderPay.setTranscation("1111222");
		order.setOrderPay(orderPay);
		
		//另外保存
		try {
			CreateOrderMessage message = orderCreateService.addOrder(order);
			System.out.println(message);
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("=="+e.getMessage());
		}
	}

	@Test
	public void testAddExchangeOrder() {
		Order order = orderManagerService.getOrderDetail("1a7ad3f2f2f04126b4270511a07cdc32");
		order.setType(null);

		List<OrderCommodity> products = new ArrayList<OrderCommodity>();
		OrderCommodity commodity1 = new OrderCommodity();
		commodity1.setCode("YLC0000000297");
		commodity1.setCount(2);
		products.add(commodity1);
		OrderCommodity commodity2 = new OrderCommodity();
		commodity2.setCode("YLC0000000298");
		products.add(commodity2);
		commodity2.setCount(2);
		
		order.setRepCustomer("repUser");
		order.setCustomer("购货人");
		order.setCustomerCode("gouhuoren");
		order.setRepCustomer("代购人");
		order.setRepCustomerCode("daigouren");
		order.setReceiver("收货人");
		order.setReceiverCode("shouhuoren");
		//另外保存
		try {
			order.setOrderCommList(products);
			order.setOrderNo("eeeeeeeeeeeeeeeee");
			order.setStatus(2);
			long start = System.currentTimeMillis();
			CreateOrderMessage message = orderCreateService.addExchangeOrder(order);
			long end = System.currentTimeMillis();
			System.out.println(end-start);
			System.out.println(message);
		} catch (Exception e) {
			String message = messageSource.getMessage(e.getMessage(), null,null);
			System.out.println(e.getMessage()+"："+message );
		}
	}

	@Test
	public void testAddOrderLogString() {
		OrderLog orderLog = new OrderLog();
		orderLog.setOrderNo("dsfdsf");
		orderLog.setOprator("operator");
		orderLog.setOpratorCode("operatorCode");
		orderLog.setOprContent("oprContent");
		try {
			orderCreateService.addOrderLog(orderLog);
		} catch (Exception e) {
			System.out.println("=="+e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	public void testAddOrderLogOrderLog() {
		fail("Not yet implemented");
	}

	@Test
	public void testOrderCri(){

		int pageNum = 2;
		int pageSize = 6;
		String sort = "delivertime desc";
		OrderSearchCriteria osc = new OrderSearchCriteria();
		osc.setStatus(1);
		//osc.setStartDate(new Date());
		osc.setEndDate(new Date());
		//osc.setReceiver("nnn");
		//osc.setPageNum(pageNum);
		//osc.setPageSize(pageSize);
		System.out.println(osc);
		if (sort != null && !"".equals(sort)) {
			//osc.setSort(sort);		//按期望到货时间排序，默认下单时间倒序
		}
		
		
		PageInfo<Order> pageInfo = orderManagerService.getOrderByParas(osc);
		System.out.println(pageInfo);
		for (Order order : pageInfo.getResult()) {
			System.out.println(order);
		}
	}
	
	@Test
	public void testAddOrderLogList(){
		List<OrderLog> orderLogList = new ArrayList<OrderLog>();
		/*OrderLog orderLog1 = new OrderLog("QQQQQ1", new Date(), "oprContent", "oprator", "opratorCode");
		OrderLog orderLog2 = new OrderLog("QQQQQ2", new Date(), "oprContent", "oprator", "opratorCode");
		OrderLog orderLog3 = new OrderLog("QQQQQ3", new Date(), "oprContent", "oprator", "opratorCode");
		orderLogList.add(orderLog1);
		orderLogList.add(orderLog2);
		orderLogList.add(orderLog3);*/
		orderCreateService.addOrderLog(orderLogList);
	}

}
