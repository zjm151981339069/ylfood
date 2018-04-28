package com.spfood.wos.workOrder.intf.domain;

import java.util.Date;
import java.util.List;

import com.spfood.kernel.domain.DomainObject;

public class ReceiveTask implements DomainObject {
	//领料编号
    private String recCode;
    //领料
    private Long recId;
    //任务时间
    private Date taskTime;
    //温区
    private String area;
    //任务数量
    private Integer taskNum;
    //订单编号
    private List<ReceiveTaskGoods> goodsList;
    //温区名称
    private String areaName;

    private static final long serialVersionUID = 1L;

    public String getRecCode() {
        return recCode;
    }

    public void setRecCode(String recCode) {
        this.recCode = recCode == null ? null : recCode.trim();
    }

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public Date getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Date taskTime) {
        this.taskTime = taskTime;
    }

    public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(Integer taskNum) {
        this.taskNum = taskNum;
    }

    public List<ReceiveTaskGoods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<ReceiveTaskGoods> goodsList) {
		this.goodsList = goodsList;
	}

	public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", recCode=").append(recCode);
        sb.append(", recId=").append(recId);
        sb.append(", taskTime=").append(taskTime);
        sb.append(", area=").append(area);
        sb.append(", taskNum=").append(taskNum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
	
}