package com.spfood.oms.orderpay.intf.domain;

import com.spfood.kernel.domain.DomainObject;

/**
 * 微信处理业务通知结果
 * @author Administrator
 *
 */
public class WeiChatProcessMessage implements DomainObject{
    private static final long serialVersionUID = 1L;
    private Boolean isSuccess; //是否成功处理
    private String returnCode; //返回状态码
    private String returnMsg; //返回信息
    public Boolean getIsSuccess() {
        return isSuccess;
    }
    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
    public String getReturnCode() {
        return returnCode;
    }
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
    public String getReturnMsg() {
        return returnMsg;
    }
    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
    
    
    
}
