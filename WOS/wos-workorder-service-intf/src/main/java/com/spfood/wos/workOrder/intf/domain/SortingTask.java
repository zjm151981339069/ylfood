package com.spfood.wos.workOrder.intf.domain;

import java.util.Date;
import java.util.List;

import com.spfood.kernel.domain.DomainObject;

public class SortingTask implements DomainObject {
	//分拣编号
    private String sortCode;
    //分拣ID
    private Long sortId;
    //任务时间
    private Date taskTime;
    //领料编号
    private String recCode;
    //自提点名称
    private String site;
    //自提点编码
    private String siteCode;
    //温区
    private String area;
    //温区名称
    private String areaName;
    //任务数量
    private Integer taskNum;
    //订单编号
    private List<SortingTaskCommodity> sortingTaskCommList;

    private static final long serialVersionUID = 1L;

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode == null ? null : sortCode.trim();
    }

    public Long getSortId() {
        return sortId;
    }

    public void setSortId(Long sortId) {
        this.sortId = sortId;
    }

    public Date getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Date taskTime) {
        this.taskTime = taskTime;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site == null ? null : site.trim();
    }

    public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
        this.siteCode = siteCode == null ? null : siteCode.trim();
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

    public String getRecCode() {
		return recCode;
	}

	public void setRecCode(String recCode) {
		this.recCode = recCode;
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
        sb.append(", sortCode=").append(sortCode);
        sb.append(", sortId=").append(sortId);
        sb.append(", taskTime=").append(taskTime);
        sb.append(", site=").append(site);
        sb.append(", siteCode=").append(siteCode);
        sb.append(", area=").append(area);
        sb.append(", taskNum=").append(taskNum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

	public List<SortingTaskCommodity> getSortingTaskCommList() {
		return sortingTaskCommList;
	}

	public void setSortingTaskCommList(
			List<SortingTaskCommodity> sortingTaskCommList) {
		this.sortingTaskCommList = sortingTaskCommList;
	}

}