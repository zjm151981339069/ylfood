package com.spfood.oms.order.intf.domain;

import java.util.Date;

import com.spfood.kernel.domain.DomainObject;

/**
 * 订单日志实体
 * @author lizekun
 *
 */
public class OrderLog implements DomainObject {

    private static final long serialVersionUID = 1L;
    //日志id
    private Long oprId;
    //订单编号
    private String orderNo;
    //操作时间
    private Date oprTime;
    //操作内容
    private String oprContent;
    //操作人
    private String oprator;
    //操作人编码
    private String opratorCode;

    public OrderLog() {
		super();
	}

	public OrderLog(String orderNo) {
		super();
		this.orderNo = orderNo;
	}

	public Long getOprId() {
        return oprId;
    }

    public void setOprId(Long oprId) {
        this.oprId = oprId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Date getOprTime() {
        return oprTime;
    }

    public void setOprTime(Date oprTime) {
        this.oprTime = oprTime;
    }

    public String getOprContent() {
        return oprContent;
    }

    public void setOprContent(String oprContent) {
        this.oprContent = oprContent == null ? null : oprContent.trim();
    }

    public String getOprator() {
        return oprator;
    }

    public void setOprator(String oprator) {
        this.oprator = oprator == null ? null : oprator.trim();
    }

    public String getOpratorCode() {
        return opratorCode;
    }

    public void setOpratorCode(String opratorCode) {
        this.opratorCode = opratorCode == null ? null : opratorCode.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", oprId=").append(oprId);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", oprTime=").append(oprTime);
        sb.append(", oprContent=").append(oprContent);
        sb.append(", oprator=").append(oprator);
        sb.append(", opratorCode=").append(opratorCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}