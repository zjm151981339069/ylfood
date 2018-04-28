package com.spfood.oms.orderinfosyn.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * 验证码配置
 * @author Administrator
 *
 */
@Component
public class VerificationCodeConfig {
    //验证码长度
    @Value("#{configProperties['order.verification.code.num']}")
    public  Integer  codeNumber;   
    //是否包含字母
    @Value("#{configProperties['order.verification.code.haslitter']}")
    public Boolean hasLitters ;
    public Integer getCodeNumber() {
        return codeNumber;
    }
    public void setCodeNumber(Integer codeNumber) {
        this.codeNumber = codeNumber;
    }
    public Boolean getHasLitters() {
        return hasLitters;
    }
    public void setHasLitters(Boolean hasLitters) {
        this.hasLitters = hasLitters;
    }
    
    
    
}
