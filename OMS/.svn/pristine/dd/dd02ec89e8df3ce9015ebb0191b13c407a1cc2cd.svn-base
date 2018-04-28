package com.spfood.oms.orderinfosyn.manager;

import com.spfood.oms.order.intf.domain.Order;

public interface VerificationCodeManager {
    /**
     * 得到验证码
     * @param codeCount 验证码的位数，默认为6位
     * @param haslitters 验证码是否包含括字母 ，默认包含字母   ；             true: 包含字母，false：不包含字母
     * @return 验证码
     */
    public String createVerificationCode(Integer codeCount,Boolean haslitters);
    
    
    /**
     *  发送指定订单的验证码到顾客
     * @param orderCode
     * @return 是否发送成功的boolean
     */
    public Boolean sendVerificationCodeToCustomer(Order order);
    
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
