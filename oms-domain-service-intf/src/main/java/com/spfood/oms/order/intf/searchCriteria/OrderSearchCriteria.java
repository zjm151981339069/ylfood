package com.spfood.oms.order.intf.searchCriteria;

import java.util.Date;

import com.spfood.kernel.domain.DomainObject;
import com.spfood.oms.order.intf.domain.Order;

public class OrderSearchCriteria extends Order implements DomainObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//开始时间
	private Date startDate;
	//结束时间
	private Date endDate;
	//当前页码
	private Integer pageNum;
	//每页显示数量
	private Integer pageSize;
	//排序
	private String sort;
	//发货单查询条件
	private Integer[] deliverSearch;
	
	//是从哪个页面进入到订单详情页
	//1表示从订单列表页面 2表示从发货单列表页面
	private String isDorF;
	
	private Integer page;
	
	//是否点击过查询，来决定返回到此页面时列表结果是否根据选中的条件查询
	private String flagSearch;

	public OrderSearchCriteria() {
		super();
	}
	
	public OrderSearchCriteria(String orderNo) {
		super(orderNo);
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
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

	

	public Integer[] getDeliverSearch() {
		return deliverSearch;
	}

	public void setDeliverSearch(Integer[] deliverStatus) {
		this.deliverSearch = deliverStatus;
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
	 * @return the isDorF
	 */
	public String getIsDorF() {
		return isDorF;
	}

	/**
	 * @param isDorF the isDorF to set
	 */
	public void setIsDorF(String isDorF) {
		this.isDorF = isDorF;
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