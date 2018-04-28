//package com.spfood.oms.orderinformsyn.impl;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.spfood.oms.orderinformsyn.manager.VerificationCodeManager;
//
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath*:local-spring-environment.xml", "classpath*:spring-test-context.xml" })   
//public class VerificationCodeTest {
//    @Autowired   
//    private VerificationCodeManager orderInformSynService;
//    /**
//     * 生成6位包含字母的验证码
//     */
//    @Test
//    public void  createVerificationCodeTest(){
//        String createVerificationCode = orderInformSynService.createVerificationCode(6, true);
//        System.out.println("验证码======： "+createVerificationCode);
//    }
//    /**
//     * 生成6位不包含字母的验证码
//     */
//    @Test
//    public void  createVerificationCodeTestNoCharTest(){
//        String createVerificationCode = orderInformSynService.createVerificationCode(6, false);
//        System.out.println("验证码====： "+createVerificationCode);
//    }
//    /**
//     * 通过订单编号拿到验证码
//     */
//    @Test
//    public void getVerificationCodeByOrderTest(){
//        String verificationCodeByOrder = orderInformSynService.getVerificationCodeByOrder("a8de57b8a3054c2f86316670bd71d1df");
//        System.out.println("通过订单编号拿到的验证码======================"+verificationCodeByOrder);
//    }
//    
//    /**
//     * 调用发送提货验证码到顾客
//     */
//    @Test
//    public void sendVerificationCodeToCustomerTest(){
//        orderInformSynService.sendVerificationCodeToCustomer("474b9e12e198457ebf4c65d5efaa30a0");
//    }
//    
//    /**
//     * 重复发送验证码到指定的手机号码
//     */
//    @Test
//    public void repeatSendVerificationCodeTest(){
//        orderInformSynService.repeatSendVerificationCode("474b9e12e198457ebf4c65d5efaa30a0", "123456");
//    }
//    
//    /**
//     * 重复发送验证码到订单的手机
//     */
//    @Test
//    public void repeatSendVerificationCodeUserOrderPhoneTest(){
//        orderInformSynService.repeatSendVerificationCode("474b9e12e198457ebf4c65d5efaa30a0");
//    }
//    
//    
//}
