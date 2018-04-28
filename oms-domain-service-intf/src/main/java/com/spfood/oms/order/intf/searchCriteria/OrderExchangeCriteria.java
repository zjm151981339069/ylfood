package com.spfood.oms.order.intf.searchCriteria;


import java.util.Date;

import com.spfood.kernel.domain.DomainObject;
import com.spfood.oms.order.intf.domain.OrderExchange;

/**
 * 换货单查询扩展类
 * @author Administrator
 *
 */
public class OrderExchangeCriteria extends OrderExchange implements DomainObject{

	private static final long serialVersionUID = 1L;

	private Integer pageNum ;

	private Integer pageSize;

	//默认下单时间排序
	private String sort;

	// 换货单开始时间
	private Date startDate;

	// 换货单结束时间
	private Date endDate;

	// 用户中心查询条件
	private String search;

	
	private Integer page;
	
	private String flagSearch;
	
	//查询条件中使用的审核状态
	private String searchAuditStatus;

	/**
	 * @return the pageNum
	 */
	public Integer getPageNum() {
		return pageNum;
	}

	/**
	 * @param pageNum the pageNum to set
	 */
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}


	/**
	 * @return the sort
	 */
	public String getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}


	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	/**
	 * @return the search
	 */
	public String getSearch() {
		return search;
	}
	

	public void setSearch(String search) {
		this.search = search == null ? null :search.trim();
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
	 * @return the searchAuditStatus
	 */
	public String getSearchAuditStatus() {
		return searchAuditStatus;
	}

	/**
	 * @param searchAuditStatus the searchAuditStatus to set
	 */
	public void setSearchAuditStatus(String searchAuditStatus) {
		this.searchAuditStatus = searchAuditStatus;
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
