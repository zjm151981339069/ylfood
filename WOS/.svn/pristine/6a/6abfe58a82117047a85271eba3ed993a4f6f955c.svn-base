package com.spfood.wos.workOrder.intf.searchCriteria;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.spfood.kernel.domain.DomainObject;
import com.spfood.wos.workOrder.intf.domain.HouseOrder;


/**
 * 成品出库工单查询实体
 * @author Administrator
 *
 */
public class HouseOrderSearchCriteria extends HouseOrder implements DomainObject{
	
	private static final long serialVersionUID = 1L;

	//默认显示第1页
	private Integer pageNum ;

	//默认显示15记录
	private Integer pageSize ;

	private String sort ;
	
	// 开始时间
	private Date startDate;
	
	// 结束时间
	private Date endDate;

    //期望到货开始时间
    private Date wishStartDate;
    
    //期望到货结束时间
    private Date wishEndDate;

    private String startDateView;
	
	private String endDateView;
	
	private String wishStartDateView;
	
	private String wishEndDateView;
	
	//查询条件中使用的orderNo
	private String searchOrderNo;
	
	
	private String flagSearch;
	
	private Integer page;
	
	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
			this.pageNum = pageNum ;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
			this.pageSize = pageSize;
	}

	
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
		if (startDate != null) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			this.startDateView = format.format(startDate);
		}
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
		if (endDate != null) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			this.endDateView = format.format(endDate);
		}
	}

	public Date getWishStartDate() {
		return wishStartDate;
	}

	public void setWishStartDate(Date wishStartDate) {
		this.wishStartDate = wishStartDate;
		if (wishStartDate != null) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			this.wishStartDateView = format.format(wishStartDate);
		}
	}

	public Date getWishEndDate() {
		return wishEndDate;
	}

	public void setWishEndDate(Date wishEndDate) {
		this.wishEndDate = wishEndDate;
		if (wishEndDate != null) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			this.wishEndDateView = format.format(wishEndDate);
		}
	}

	public String getStartDateView() {
		return startDateView;
	}

	public void setStartDateView(String startDateView) {
		this.startDateView = startDateView;
	}

	public String getEndDateView() {
		return endDateView;
	}

	public void setEndDateView(String endDateView) {
		this.endDateView = endDateView;
	}

	public String getWishStartDateView() {
		return wishStartDateView;
	}

	public void setWishStartDateView(String wishStartDateView) {
		this.wishStartDateView = wishStartDateView;
	}

	public String getWishEndDateView() {
		return wishEndDateView;
	}

	public void setWishEndDateView(String wishEndDateView) {
		this.wishEndDateView = wishEndDateView;
	}

	/**
	 * @return the searchOrderNo
	 */
	public String getSearchOrderNo() {
		return searchOrderNo;
	}

	/**
	 * @param searchOrderNo the searchOrderNo to set
	 */
	public void setSearchOrderNo(String searchOrderNo) {
		this.searchOrderNo = searchOrderNo;
	}

	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}

	/**
	 * @return the flagSearch
	 */
	public String getFlagSearch() {
		return flagSearch;
	}

	/**
	 * @param flagSearch the flagSearch to set
	 */
	public void setFlagSearch(String flagSearch) {
		this.flagSearch = flagSearch;
	}

	
	
}