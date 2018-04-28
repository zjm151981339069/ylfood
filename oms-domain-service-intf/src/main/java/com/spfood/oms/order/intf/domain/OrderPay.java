package com.spfood.oms.order.intf.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.spfood.kernel.domain.DomainObject;

/**
 * 订单支付实体
 * @author lizekun
 *
 */
public class OrderPay implements DomainObject {
	
    private static final long serialVersionUID = 1L;
    //支付id
    private Long payId;
    //订单编号
    private String orderNo;
    //是否支付
    private Integer isPay;
    //支付金额
    private BigDecimal paySum;
    //支付时间
    private Date payTime;
    //支付方式
    private Integer payType;
    //交易号
    private String transcation;
    //付款人编号
    private String payeeCode;
    //付款人
    private String payee;
    //付款账号
    private String account;
    //是否到账
    private Integer isArrived;
    //是否已发送到FMS
    private Integer isSendFms;

    public OrderPay() {
		super();
	}

	public OrderPay(String orderNo) {
		super();
		this.orderNo = orderNo;
	}

	public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    public BigDecimal getPaySum() {
        return paySum;
    }

    public void setPaySum(BigDecimal paySum) {
        this.paySum = paySum;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getTranscation() {
        return transcation;
    }

    public void setTranscation(String transcation) {
        this.transcation = transcation == null ? null : transcation.trim();
    }

    public String getPayeeCode() {
        return payeeCode;
    }

    public void setPayeeCode(String payeeCode) {
        this.payeeCode = payeeCode == null ? null : payeeCode.trim();
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee == null ? null : payee.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Integer getIsArrived() {
        return isArrived;
    }

    public void setIsArrived(Integer isArrived) {
        this.isArrived = isArrived;
    }

    public Integer getIsSendFms() {
        return isSendFms;
    }

    public void setIsSendFms(Integer isSendFms) {
        this.isSendFms = isSendFms;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", payId=").append(payId);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", isPay=").append(isPay);
        sb.append(", paySum=").append(paySum);
        sb.append(", payTime=").append(payTime);
        sb.append(", payType=").append(payType);
        sb.append(", transcation=").append(transcation);
        sb.append(", payeeCode=").append(payeeCode);
        sb.append(", payee=").append(payee);
        sb.append(", account=").append(account);
        sb.append(", isArrived=").append(isArrived);
        sb.append(", isSendFms=").append(isSendFms);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderNo == null) ? 0 : orderNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderPay other = (OrderPay) obj;
		if (orderNo == null) {
			if (other.orderNo != null)
				return false;
		} else if (!orderNo.equals(other.orderNo))
			return false;
		return true;
	}
    
}