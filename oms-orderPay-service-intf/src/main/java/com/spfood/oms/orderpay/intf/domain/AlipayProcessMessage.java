package com.spfood.oms.orderpay.intf.domain;

import com.spfood.kernel.domain.DomainObject;

/**
 * 支付宝业务结果通知对象
 * @author Administrator
 *
 */
public class AlipayProcessMessage implements DomainObject{
    private static final long serialVersionUID = 1L;
    private String code; //网关返回码
    private String msg; //网关返回码描述
    private String subCode; //业务返回码
    private String subMsg; //业务返回码描述
    private Boolean isSuccess; //是否处理成功
    
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getSubCode() {
        return subCode;
    }
    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }
    public String getSubMsg() {
        return subMsg;
    }
    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }
    public Boolean getIsSuccess() {
        return isSuccess;
    }
    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
    
    

}
