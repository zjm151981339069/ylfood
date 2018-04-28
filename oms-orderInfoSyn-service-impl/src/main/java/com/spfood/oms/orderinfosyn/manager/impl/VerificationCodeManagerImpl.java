package com.spfood.oms.orderinfosyn.manager.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.oms.order.intf.OrderManagerService;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.orderinfosyn.manager.VerificationCodeManager;
import com.spfood.oms.orderinfosyn.utils.VerificationCodeUitls;

@Service
public class VerificationCodeManagerImpl implements VerificationCodeManager{
    @Autowired
    private OrderManagerService orderManagerService;
    Logger logger = Logger.getLogger(VerificationCodeManagerImpl.class); 
    /**
     * 创建订单的验证码
     */
    @Override
    public String createVerificationCode(Integer codeCount, Boolean haslitters) {
        
        return VerificationCodeUitls.getVerificationCode(codeCount, haslitters);
    }
    /**
     * 发送订单编号的验证码到顾客
     */
    @Override
    public Boolean sendVerificationCodeToCustomer(Order order) {
        String verificationCode=null;
        String phone=null ;
        //判断是查询到该订单
        if(order!=null){
            verificationCode = order.getVerification();
            phone = order.getPhone();  
        }else {
            return false;
        }
        //判断是该订单是否存在验证码和电话号码
        if(verificationCode!=null&&phone!=null){   
            try {
                /**
                 * 调用发送验证码到手机的接口
                 */            
                logger.info("调用验证码到手机的接口方法");
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }            
        }else {
            return false;
        }
        return true;
    }
    /**
     * 重发验证码到指定的手机
     */
    @Override
    public Boolean repeatSendVerificationCode(String orderCode, String phone) {
        //判断订单编号和手机
        if(orderCode!=null&&!"".equals(orderCode)&&phone!=null&&!"".equals(phone)){
            Order order = orderManagerService.getOrderByOrderNo(orderCode);
            if(order!=null){
                try {
                    /**
                     * 调用发送到顾客手机验证码的接口方法
                     */
                    logger.info("调用发送验证码到顾客");
                    return true;
                } catch (Exception e) {
                   e.printStackTrace();
                   return false;
                }
            }        
        }else {
            return false;
        }
        return false;
    }
    /**
     * 重发订单验证码
     */
    @Override
    public Boolean repeatSendVerificationCode(String orderCode) {
        Order order=null;
        String phone=null;
        //判断订单编号是否正确
        if(orderCode!=null&&!"".equals(orderCode)){
            try {
                order = orderManagerService.getOrderByOrderNo(orderCode);
                if(order!=null){
                    phone = order.getPhone();
                }else{
                    return false;
                }   
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }    
        }
        //判断是否包括订单和电话号码
        if(order==null||phone==null){
            return false;
        }        
        //更改订单的验证码，发送验证码到客户，发送更改后的订单验证码到TS消息队列
        try {
            /**
             * 调用发送到顾客手机验证码的接口方法
             */
            logger.info("调用发送验证码到顾客");
            } catch (Exception e) {
               e.printStackTrace();
               return false;
            }      
        return true;
    }

}
