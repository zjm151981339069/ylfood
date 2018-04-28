package com.spfood.wos.workOrder.intf.domain;

import java.util.Date;
import java.util.List;

import com.spfood.kernel.domain.DomainObject;

public class OutTask implements DomainObject {
	//出库任务编号
    private String outCode;
    //领料任务编号
    private String recCode;
    //出库任务ID
    private Long outId;
    //任务生成时间
    private Date taskTime;
    //仓库编码
    private String repCode;
    //温区
    private String area;
    //温区名称
    private String areaName;
    //任务数量
    private Integer taskNum;
    //商品
    private List<OutTaskGoods> goodsList;

    private static final long serialVersionUID = 1L;

    public String getOutCode() {
        return outCode;
    }

    public void setOutCode(String outCode) {
        this.outCode = outCode == null ? null : outCode.trim();
    }

    public String getRecCode() {
		return recCode;
	}

	public void setRecCode(String recCode) {
        this.recCode = recCode == null ? null : recCode.trim();
	}

	public Long getOutId() {
        return outId;
    }

    public void setOutId(Long outId) {
        this.outId = outId;
    }

    public Date getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Date taskTime) {
        this.taskTime = taskTime;
    }

    public String getRepCode() {
		return repCode;
	}

	public void setRepCode(String repCode) {
		this.repCode = repCode == null ? null : repCode.trim();
	}

	public String getArea() {
        return area;
    }

    public void setArea(String area) {
		this.area = area == null ? null : area.trim();
    }

    public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName == null ? null : areaName.trim();
	}

	public Integer getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(Integer taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", outCode=").append(outCode);
        sb.append(", recCode=").append(recCode);
        sb.append(", outId=").append(outId);
        sb.append(", taskTime=").append(taskTime);
        sb.append(", repCode=").append(repCode);
        sb.append(", area=").append(area);
        sb.append(", areaName=").append(areaName);
        sb.append(", taskNum=").append(taskNum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

	public List<OutTaskGoods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<OutTaskGoods> goodsList) {
		this.goodsList = goodsList;
	}

}