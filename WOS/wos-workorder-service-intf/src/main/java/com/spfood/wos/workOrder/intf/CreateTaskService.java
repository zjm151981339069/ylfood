package com.spfood.wos.workOrder.intf;

import java.util.List;

import com.spfood.wos.workOrder.intf.domain.HouseOrderCommodity;
import com.spfood.wos.workOrder.intf.domain.OutTask;
import com.spfood.wos.workOrder.intf.domain.ReceiveTask;
import com.spfood.wos.workOrder.intf.domain.SortingTask;

public interface CreateTaskService {

	/**
	 * 生成出货、领料、分拣任务工单
	 * @param outTaskList 出货工单集合
	 * @param receiveTaskList 领料工单集合
	 * @param sortingTaskList 分拣工单集合
	 * @return true/false
	 */
	public boolean createTask(List<OutTask> outTaskList,
			List<ReceiveTask> receiveTaskList, 
			List<SortingTask> sortingTaskList,
			List<HouseOrderCommodity> houseOrderCommList);
	
	
	/**
	 * 根据ocs的营业时间和集单时间间隔修改分拣任务定时调度
	 */
	public void sortTaskScheduleTimeModify();
	
	
	/**
	 * 根据ocs的集单数量设置决定是否生成工单
	 */
	public void createWorkOrderByCollectCount();
}
