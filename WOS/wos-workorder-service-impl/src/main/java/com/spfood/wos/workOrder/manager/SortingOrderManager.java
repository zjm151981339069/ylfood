package com.spfood.wos.workOrder.manager;


public interface SortingOrderManager {

	/**
	 * 获取Ocs营业时间并修改分拣任务的调度时间
	 */
	public void getOcsWorkTimeAndModifyQuartzFq() throws Exception;

	/**
	 * 根据ocs的集单数量设置决定是否生成工单
	 * @param listCount 要生成工单的数量
	 * @throws Exception
	 */
	public void createWorkOrderByCollectCount() throws Exception;
	
	
}
