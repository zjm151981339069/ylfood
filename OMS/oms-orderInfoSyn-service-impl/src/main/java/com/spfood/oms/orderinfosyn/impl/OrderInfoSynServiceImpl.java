package com.spfood.oms.orderinfosyn.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.oms.order.intf.OrderManagerService;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.orderinfosyn.constant.VerificationCodeConfig;
import com.spfood.oms.orderinfosyn.intf.OrderInfoSynService;
import com.spfood.oms.orderinfosyn.manager.OrderSynToNextSystemsManager;
import com.spfood.oms.orderinfosyn.manager.SendOrderMessageQueueManager;
import com.spfood.oms.orderinfosyn.manager.VerificationCodeManager;
/**
 * 订单信息同步服务
 * @author Administrator
 * 
 */
public class OrderInfoSynServiceImpl implements OrderInfoSynService{
    @Autowired
    private VerificationCodeManager verificationCodeManager;
    @Autowired
    private OrderManagerService orderManagerService;
    @Autowired
    private SendOrderMessageQueueManager sendOrderMessageQueueManager;
    @Autowired
    private VerificationCodeConfig verificationCodeConfig;
    @Autowired
    private  OrderSynToNextSystemsManager orderSynToNextSystemsManager;
    Logger logger = Logger.getLogger(OrderInfoSynServiceImpl.class); 
    
    @Override
    public Boolean orderSynToNextSystems(List<Order> orders) {
        return orderSynToNextSystemsManager.orderSynToNextSystems(orders);  
    }

    @Override
    public Boolean orderSynToNextSystems(Order order) {
        return orderSynToNextSystemsManager.orderSynToNextSystems(order);
    }
    @Override
    public void quartzOrderSynToNextSystems() {
        orderSynToNextSystemsManager.quartzOrderSynToNextSystems();        
    }
    @Override
    public String createVerificationCode() {
        return verificationCodeManager.createVerificationCode(verificationCodeConfig.getCodeNumber(), verificationCodeConfig.getHasLitters());
    }

    @Override
    public String getVerificationCodeByOrder(String orderCode) {
        Order order =null;
        if(orderCode!=null&&!"".equals(orderCode)){
            order = orderManagerService.getOrderByOrderNo(orderCode);
            return order.getVerification();
        }else {
            return null;
        }    
    }

    @Override
    public Boolean sendVerificationCodeToCustomer(String orderCode) {
        Order order=null;
        //判断订单编号
        if(orderCode!=null&&!"".equals(orderCode)){
            //通过订单得到验证码
            order = orderManagerService.getOrderByOrderNo(orderCode);
        }else {
            return false;
        }
        return verificationCodeManager.sendVerificationCodeToCustomer(order);
    }

    @Override
    public Boolean repeatSendVerificationCode(String orderCode, String phone) {
        return verificationCodeManager.repeatSendVerificationCode(orderCode, phone);
    }

    @Override
    public Boolean repeatSendVerificationCode(String orderCode) {
        return verificationCodeManager.repeatSendVerificationCode(orderCode);
    }





}
