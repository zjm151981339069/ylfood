package com.spfood.wos.workOrder.intf.domain;

import java.util.Date;

import com.spfood.kernel.domain.DomainObject;

public class HouseOrderTemp implements DomainObject {
	
	private static final long serialVersionUID = 1L;
	 
    private Long orderId;

    //订单编号
    private String orderNo;

    //订单状态
    private Integer status;

    //下单时间
    private Date createTime;
    
   //配送时间
    private Date deliverTime;
    

    public HouseOrderTemp() {
		super();
	}

	public HouseOrderTemp(String orderNo) {
		super();
		this.orderNo = orderNo;
	}

    public HouseOrderTemp(String orderNo, Integer status,
			Date createTime,Date deliverTime ) {
		super();
		this.orderNo = orderNo;
		this.status = status;
		this.createTime = createTime;
		this.deliverTime = deliverTime;
	}

	public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeliverTime() {
		return deliverTime;
	}

	public void setDeliverTime(Date deliverTime) {
		this.deliverTime = deliverTime;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", deliverTime=").append(deliverTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}