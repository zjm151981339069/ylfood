//package com.spfood.oms.orderinfosyn.manager.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.spfood.oms.order.intf.OrderManagerService;
//import com.spfood.oms.order.intf.domain.OrderPay;
//import com.spfood.oms.orderinfosyn.constant.MqDestinationList;
//import com.spfood.oms.orderinfosyn.manager.OrderPaySynFms;
//import com.spfood.oms.orderinfosyn.manager.SendOrderFms;
//import com.spfood.oms.orderinfosyn.utils.DefaultStatus;
//@Service
//@Transactional
//public class OrderPaySynFmsImpl implements OrderPaySynFms{
//    
//
//    @Autowired
//    private SendOrderFms sendOrderInform;
//    
//    @Autowired
//    private OrderManagerService orderManagerService;
//    
//    private static final Logger LOGGER = Logger.getLogger(OrderPaySynFmsImpl.class);
//    
//    public int orderPaySynFms(OrderPay orderPay) {
//        if (orderPay != null && orderPay.getOrderNo() != null && orderPay.getIsPay() == DefaultStatus.HAS_PAY) {
//            try {
//				// 将支付信息传入activeMQ中
//				sendOrderInform.sendWhatever(orderPay,MqDestinationList.FMS_DESTINATION);
//				// 将issendfms的状态改为已发送
//				orderManagerService.updateOrderPayIsSendFms(orderPay.getOrderNo());
//				// 删除临时表
//				orderManagerService.deleteOrderTempByOrderNo(orderPay.getOrderNo());
//                return 1;
//            } catch (Exception e) {
//            	LOGGER.error("支付信息同步失败", e);
//                return -1;
//            }
//        }
//        LOGGER.info("支付订单信息有误"+"oms.orderInfoSyn.orderPaySynFmsImpl"+orderPay.toString());
//        return -1;
//    }
//
//    //批量同步
//	@Override
//	public int orderPaySynFms(List<OrderPay> orderPayList) {
//		
//		List<String> orderNos = new ArrayList<String>();
//		try {
//			for (OrderPay orderPay : orderPayList) {
//				if (orderPay != null && orderPay.getOrderNo() != null && orderPay.getIsPay() == DefaultStatus.HAS_PAY) {
//						// 将支付信息传入activeMQ中
//						sendOrderInform.sendWhatever(orderPay,MqDestinationList.FMS_DESTINATION);
//				}
//				orderNos.add(orderPay.getOrderNo());
//			}
//			// 将issendfms的状态改为已发送
//			orderManagerService.updateOrderPayIsSendFms(orderNos);
//			// 删除临时表
//			orderManagerService.deleteOrderTempByOrderNo(orderNos);
//			return 1;
//		} catch (Exception e) {
//			LOGGER.error("支付信息同步失败", e);
//            return -1;
//		}
//	}
//}
