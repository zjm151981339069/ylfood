package com.spfood.oms.orderinfosyn.intf;

import java.util.List;

import com.spfood.oms.order.intf.domain.Order;

/**
 * 
 * @author Administrator
 *
 */
public interface OrderInfoSynService {
    /**
     * 发送订单订单信息
     * @param order 订单
     * @return 是否发送成功
     */
    public Boolean orderSynToNextSystems(Order order);
    /**
     * 发送订单订单信息
     * @param orders 订单列表
     * @return 是否发送成功
     */
    public Boolean orderSynToNextSystems(List<Order> orders);
    /**
     * 定时发送已支付订单信息到下级系统
     */
    public void quartzOrderSynToNextSystems();
    /**
     * 创建得到验证码
     * @return 验证码
     */
    public String createVerificationCode();
    
    /**
     * 根据订单编号拿到验证码
     * @param orderCode  订单编号
     * @return  验证码
     */
    public String getVerificationCodeByOrder(String orderCode);
    
    /**
     *  发送指定订单的验证码到顾客
     * @param orderCode
     * @return 是否发送成功的boolean
     */
    public Boolean sendVerificationCodeToCustomer(String orderCode);
    
    /**
     * 重复发送验证码到指定的手机
     * @param orderCode ：  订单编号
     * @param phone ： 指定的手机号码
     * @return
     */
    public Boolean repeatSendVerificationCode(String orderCode,String phone);
    
    /**
     * 重复发送验证码
     * @param orderCode ：  订单编号
     * @return
     */
    public Boolean repeatSendVerificationCode(String orderCode);
}
