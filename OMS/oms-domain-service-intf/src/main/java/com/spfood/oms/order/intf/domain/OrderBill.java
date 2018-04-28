package com.spfood.oms.order.intf.domain;

import java.math.BigDecimal;

import com.spfood.kernel.domain.DomainObject;

public class OrderBill implements DomainObject {
    private static final long serialVersionUID = 1L;
   

    /**
     * 订单编号
     */
     private String orderNo;
     
     
     /**
      * 订单发票类型
      */
     private Integer billType;
     
     /**
      * 订单发票抬头
      */
     private Integer billTitle;

     /**
      * 订单发票内容
      */
     private String billContent;
     
     private BigDecimal orderAmount;

    @Override
	public String toString() {
		return "OrderBill [orderNo=" + orderNo + ", billType=" + billType
				+ ", billTitle=" + billTitle + ", billContent=" + billContent
				+ ", orderAmount=" + orderAmount + "]";
	}

	public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }


    public Integer getBillType() {
        return billType;
    }

    public void setBillType(Integer billType) {
        this.billType = billType;
    }

    public Integer getBillTitle() {
        return billTitle;
    }

    public void setBillTitle(Integer billTitle) {
        this.billTitle = billTitle;
    }

    public String getBillContent() {
        return billContent;
    }

    public void setBillContent(String billContent) {
        this.billContent = billContent;
    }
    

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}
    
}
