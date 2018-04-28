package com.spfood.oms.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.oms.order.dao.OrderCommodityDao;
import com.spfood.oms.order.dao.OrderDao;
import com.spfood.oms.order.dao.OrderLogDao;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderBill;
import com.spfood.oms.order.intf.domain.OrderCommodity;
import com.spfood.oms.order.intf.domain.OrderLog;
import com.spfood.oms.order.intf.domain.OrderPay;
import com.spfood.oms.order.intf.domain.OrderTemp;
import com.spfood.oms.order.intf.searchCriteria.OrderSearchCriteria;
import com.spfood.oms.orderpay.intf.OrderPayByACPService;
import com.spfood.oms.orderpay.intf.OrderPayByAliPayService;
import com.spfood.oms.orderpay.intf.OrderPayByWeiChatService;
import com.spfood.oms.orderpay.intf.domain.AlipayQueryResult;

public class OrderManagerServiceTest extends BaseTest{

	@Autowired
	private OrderPayByACPService orderPayByACPService;
	@Autowired
	private OrderPayByWeiChatService orderPayByWeiChatService;

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testGetOrderByParas() {
		OrderSearchCriteria orderSearchCriteria = new OrderSearchCriteria();
		//orderSearchCriteria.setStatus(1);
		orderSearchCriteria.setStartDate(DateUtils.stringToDate("2017-03-24"));
		orderSearchCriteria.setEndDate(DateUtils.stringToDate("2017-03-25"));
		PageInfo<Order> orderByParas = orderManagerService.getOrderByParas(orderSearchCriteria);
		List<Order> result = orderByParas.getResult();
		for (Order order : result) {
			System.out.println(order);
		}
	}

	@Test
	public void testGetOrderDetail() {
		Order orderDetail = orderManagerService.getOrderDetail("8577475444736ren");
		System.out.println(orderDetail);
		List<OrderCommodity> orderCommList = orderDetail.getOrderCommList();
		for (OrderCommodity orderCommodity : orderCommList) {
			System.out.println(orderCommodity);
		}
		List<OrderLog> orderLogList = orderDetail.getOrderLogList();
		for (OrderLog orderLog : orderLogList) {
			System.out.println(orderLog);
		}
		OrderPay orderPay = orderDetail.getOrderPay();
		System.out.println(orderPay);
		
	}
	
	@Test
	public void testGetOrderDetails() {
		List<String> list = new ArrayList<String>();
		list.add("7298518908928576");
		list.add("7307051171840575");
		list.add("7311308390400557");
		list.add("7311470919680557");
		list.add("7311835824128574");
		//List<Order> orderDetails = orderDao.selectOrderDetailListByOrderNo(list);
		List<Order> orderDetails = orderManagerService.getOrderDetailList(list);
		for (Order order : orderDetails) {
			List<OrderCommodity> orderCommList = order.getOrderCommList();
			System.out.println("订单号:" + order.getOrderNo());
			System.out.println(order.getOrderPay());
			for (OrderCommodity orderCommodity : orderCommList) {
				System.out.println("\t商品: " + orderCommodity);
			}
		}
	}
	@Autowired
	private OrderDao orderDao;
	@Test
	public void test22(){
		Order order = new Order();
		order.setOrderNo("ooo");
		Order selectOne = orderDao.selectOne(order);
		System.out.println(selectOne);
	}
	@Test
	public void testGetOrderByOrderNo() {
		Order order = orderManagerService.getOrderByOrderNo("ooo");
		System.out.println(order);
	}
	
	@Test
	public void testGetOrderListByOrderNoList(){
		List<String> orderNos = new ArrayList<String>();
		orderNos.add("8a9131ecc8d14a95bac8a300f7a2d6cb");
		orderNos.add("7a8a5fe88c7b435f91dc696b7982dbc2");
		List<Order> orderListByOrderNoList = orderManagerService.getOrderListByOrderNoList(orderNos);
		for (Order order : orderListByOrderNoList) {
			System.out.println(order);
		}
	}

	@Test
	public void testCancelOrder() {
		boolean flag = orderManagerService.cancelOrder("7c405e7ab4da4c8088a917a4c875c4a0", "dsgdg", "sdgsdg");
		System.out.println(flag);
	}

	@Test
	public void testAddOrUpdateSign() {
		String addOrUpdateSign = orderManagerService.addOrUpdateSign("7a8a5fe88c7b435f91dc696b7982dbc2", "aegawe", "aegawe", "aegawe");
		System.out.println(addOrUpdateSign);
	}

	@Test
	public void testSetDeliverTime() {
		String addOrUpdateSign = orderManagerService.setDeliverTime("ooo", new Date(new Date().getTime()+1000), "aegawe", "aegawe");
		System.out.println(addOrUpdateSign);
	}

	@Test
	public void testDeleteOrderTempByOrderNo() {
		orderManagerService.deleteOrderTempByOrderNo("eeeeeeeeeeeeeeeee");
	}
	
	@Test
	public void testDeleteOrderTempByOrderNoDeleteOrderTempByOrderNo() {
		List<String> orderNoList = new ArrayList<String>();
		orderNoList.add("sfdsfsdfsdfsdf");
		orderManagerService.deleteOrderTempByOrderNo(orderNoList);
	}

	@Test
	public void testGetOrderTempByStatus() {
		List<OrderTemp> list = orderManagerService.getOrderTempByStatus(1);
		System.out.println(list);
	}

	@Test
	public void testGetOrderTempOrderNosByStatus() {
		List<String> list = orderManagerService.getOrderTempOrderNosByStatus(1);
		System.out.println(list);
	}

	@Test
	public void testGetOrderTempCountByStatus() {
		Long l = orderManagerService.getOrderTempCountByStatus(1);
		System.out.println(l);
	}

	@Test
	public void testUpdateOrderTempStatusByOrderNo() {
		orderManagerService.updateOrderTempStatusByOrderNo("dsgdgd", 0);
	}

	@Test
	public void testUpdateOrderVerification() {
		orderManagerService.updateOrderVerification("ooo", "efdg");
	}

	@Test
	public void testGetOrderPayByOrderNo() {
		OrderPay orderPay = orderManagerService.getOrderPayByOrderNo("9b073edf40e947788daeda107b82ae3f");
		System.out.println(orderPay);
	}

	@Test
	public void testGetOrderPayByOrderNos() {
		List<String> list = new ArrayList<String>();
		list.add("3dfab8fd4f780ee047f98530910");
		list.add("3dfab8fd4f780ee047f9830910");
		list.add("3dfab8fd4f780ee047f983090");
		
		List<OrderPay> orderPayByOrderNos = orderManagerService.getOrderPayByOrderNos(list);
		System.out.println(orderPayByOrderNos.size());
		for (OrderPay orderPay : orderPayByOrderNos) {
			System.out.println(orderPay);
		}
	}

	@Test
	public void testUpdateOrderBillOrderBill() {
		OrderBill orderBill = new OrderBill();
		orderBill.setOrderNo("ooo");
		orderBill.setBillTitle(8);
		orderBill.setBillType(8);
		orderBill.setBillContent("qqqq");
		orderBill.setOrderAmount(new BigDecimal(477));
		try {
			boolean updateOrderBill = orderManagerService.updateOrderBill(orderBill);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateOrderBillStringIntIntString() {
		OrderBill orderBill = new OrderBill();
		orderBill.setOrderNo("ooo");
		orderBill.setBillTitle(3);
		orderBill.setBillType(4);
		orderBill.setBillContent("2ew");
		orderManagerService.updateOrderBill(orderBill);
	}

	@Test
	public void testUpdateBatch(){
		List<Order> orderList = new ArrayList<Order>();
		Order order1 = new Order();
		order1.setOrderNo("ooo");
		order1.setStatus(2);
		Order order2 = new Order();
		order2.setOrderNo("uuu");
		order2.setStatus(3);
		Order order3 = new Order();
		order3.setOrderNo("ppp");
		order3.setStatus(4);
		orderList.add(order1);
		orderList.add(order2);
		orderList.add(order3);
		orderDao.updateBatchCancelOrder(orderList);
	}
	
	@Autowired
	private OrderCommodityDao orderCommodityDao;
	
	@Test
	public void testOrderCommodity(){
		
		List<String> orderNoList = new ArrayList<String>();
		orderNoList.add("sdf");
		List<OrderCommodity> selectByOrderNoList = orderCommodityDao.selectByOrderNoList(orderNoList );
		for (OrderCommodity orderCommodity : selectByOrderNoList) {
			System.out.println(orderCommodity);
		}
		
	}
	
	@Autowired
	private OrderLogDao orderLogDao;
	@Test
	public void testOrderLog(){
		
		List<OrderLog> orderLogList = new ArrayList<OrderLog>();
		orderLogList.add(new OrderLog("35fdffa81de747eba244aab7febfb119"));
		orderLogList.add(new OrderLog("7a8a5fe88c7b435f91dc696b7982dbc2"));
		
		orderLogDao.insertInBatch(orderLogList);
	}
	
	@Test
	public void testUpdateBatchCancelOrder(){
		List<Order> orderList = new ArrayList<Order>();
		Order order1 = new Order("8a9131ecc8d14a95bac8a300f7a2d6cb");
		order1.setStatus(3);
		orderList.add(order1);
		Order order2 = new Order("7a8a5fe88c7b435f91dc696b7982dbc2");
		order2.setStatus(4);
		orderList.add(order2);

		orderDao.updateBatchCancelOrder(orderList);
	}
	
	@Test
	public void testUpdateOrderPay(){
		OrderPay orderPay = new OrderPay("26a023ad54e24222bd44b711a0d3c83c");
		orderPay.setPaySum(new BigDecimal(444));
		orderManagerService.updateOrderPay(orderPay);
	}
	
	@Test
	public void testUpdateOrderStatus(){
		List<Order> orderList = new ArrayList<Order>();
		Order order1 = new Order("ooo");
		order1.setStatus(3);
		Order order2 = new Order("uuu");
		order2.setStatus(4);
		orderList.add(order1);
		orderList.add(order2);
		try {
			long start = System.currentTimeMillis();
			orderManagerService.updateOrderStatus(orderList);
			long end = System.currentTimeMillis();
			System.out.println(end-start);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateOrderStatus1(){
		Order order2 = new Order("uuu");
		order2.setStatus(5);
		try {
			long start = System.currentTimeMillis();
			boolean updateOrderStatus = orderManagerService.updateOrderStatus(order2);
			long end = System.currentTimeMillis();
			System.out.println(end-start);
			System.out.println(updateOrderStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testOrderPayIsSendFMS(){
		boolean updateOrderPayIsSendFms = orderManagerService.updateOrderPayIsSendFms("3dfab8fd4fd24e4780ee047f98530910");
		System.out.println(updateOrderPayIsSendFms);
		orderManagerService.cancelOrderQuartz();
	}
	
	@Test
	public void testOrderPayIsSendFMSOrderPayIsSendFMS(){
		List<String> orderNoList = new ArrayList<String>();
		orderNoList.add("50e1bef16ad649428449f7692eee887f");
		orderNoList.add("f517351b71d9417ea7d3f40589adc49e");
		orderManagerService.updateOrderPayIsSendFms(orderNoList);
	}
	
	@Test
	public void testCancelOrderQuartz(){
		orderManagerService.cancelOrderQuartz();
	}
	

	@Autowired
	private OrderPayByAliPayService orderPayByAliPayService;
	@Test
	public void testOrderPayByAliPayService(){
		AlipayQueryResult orderPayQuery = orderPayByAliPayService.orderPayQuery("jsdkmjskuipafuaspfusoifuasjdikjlkf45kds1j1454", null);
		System.out.println(orderPayQuery);
	}
	
	@Test
	public void testGetOrderAndCommodity(){
		Order orderAndCommodity = orderManagerService.getOrderAndCommodity("ooo");
		System.out.println(orderAndCommodity);
		for (OrderCommodity commodity : orderAndCommodity.getOrderCommList()) {
			System.out.println(commodity);
		}
		System.out.println(orderAndCommodity.getOrderLogList());
		System.out.println(orderAndCommodity.getOrderPay());
	}
	
	@Test
	public void testGetOrderAndCommodityList(){
		List<String> orderNoList = new ArrayList<String>();
		orderNoList.add("ooo");
		orderNoList.add("uuu");
		List<Order> orderAndCommodity = orderManagerService.getOrderAndCommodity(orderNoList);
		for (Order order : orderAndCommodity) {
			System.out.println(order);
			List<OrderCommodity> orderCommList = order.getOrderCommList();
			System.out.println(orderCommList.size());
			for (OrderCommodity orderCommodity : orderCommList) {
				System.out.println(orderCommodity);
			}
		}
	}
	

	
	@Test
	public  void test111(){
		AlipayQueryResult orderPayQuery = orderPayByAliPayService.orderPayQuery("jsdkmjskuipafuaspfusoifuasjdikjlkf45kds1j1454", null);
		System.out.println(orderPayQuery);
	}
	

	
	
	
}
