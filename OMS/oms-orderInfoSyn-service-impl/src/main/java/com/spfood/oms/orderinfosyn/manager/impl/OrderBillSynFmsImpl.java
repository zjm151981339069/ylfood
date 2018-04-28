//package com.spfood.oms.orderinfosyn.manager.impl;
//
//import java.math.BigDecimal;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.spfood.kernel.exception.BizException;
//import com.spfood.oms.order.intf.OrderManagerService;
//import com.spfood.oms.order.intf.domain.Order;
//import com.spfood.oms.order.intf.domain.OrderBill;
//import com.spfood.oms.orderinfosyn.constant.MqDestinationList;
//import com.spfood.oms.orderinfosyn.manager.OrderBillSynFms;
//import com.spfood.oms.orderinfosyn.manager.SendOrderFms;
//
//@Service
//@Transactional
//public class OrderBillSynFmsImpl implements OrderBillSynFms{
//    
//    @Autowired
//    private OrderManagerService orderManagerService;
//    @Autowired
//    private SendOrderFms sendOrderFms;
//    
//    private static final Logger logger = Logger.getLogger(OrderBillSynFmsImpl.class);
//    
//    public boolean orderBillSyn(String orderNo, Integer billType,
//            Integer billTitle, String billContent,BigDecimal orderAmount) {
//        //orderNo 不为空且(billType,billTitle,billContent)三者不都为空
//        if(orderNo!=null &&billType!=null && billTitle!=null ) {
//        	Order order = orderManagerService.getOrderByOrderNo(orderNo);
//        	if (order.getIsBill()!=null && order.getIsBill() == 0) {
//				return true;
//			}else{
//				try{
//					//修改订单发票信息
//					OrderBill orderBill = new OrderBill(orderNo,billType,billTitle,billContent,orderAmount);
//					orderManagerService.updateOrderBill(orderBill);
//					
//					//System.out.println(orderBill.toString());
//					//将发票信息传入activeMQ中
//					sendOrderFms.sendWhatever(orderBill, MqDestinationList.FMS_DESTINATION);
//					return true;
//				}catch(Exception e ) {
//					e.printStackTrace();
//					logger.error("Invoice information synchronization failed", e);
//					throw new BizException("oms.orderInfoSyn.sendOrderFms",orderBillSyn(orderNo,billType,billTitle,billContent,orderAmount));
//				}
//			}
//        }
//		return true;
//    }
//}
