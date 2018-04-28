package com.spfood.oms.order.intf.domain;

import java.util.Date;

import com.spfood.kernel.domain.DomainObject;

/**
 * 临时表订单实体
 * @author lizekun
 *
 */
public class OrderTemp implements DomainObject {
    private static final long serialVersionUID = 1L;
    //临时表订单id
    private Long orderId;
    //临时表订单编号
    private String orderNo;
    //临时表订单状态
    private Integer status;
    //临时表订单创建时间
    private Date createTime;

    public OrderTemp() {
		super();
	}

	public OrderTemp(String orderNo) {
		super();
		this.orderNo = orderNo;
	}

	public OrderTemp(String orderNo, Integer status, Date createTime) {
		super();
		this.orderNo = orderNo;
		this.status = status;
		this.createTime = createTime;
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
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}