package com.spfood.wos.workOrder.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.spfood.kernel.exception.BizException;
import com.spfood.wos.workOrder.intf.CreateTaskService;
import com.spfood.wos.workOrder.intf.HouseOrderService;
import com.spfood.wos.workOrder.intf.domain.HouseOrderCommodity;
import com.spfood.wos.workOrder.intf.domain.HouseOrderTemp;
import com.spfood.wos.workOrder.intf.domain.OutTask;
import com.spfood.wos.workOrder.intf.domain.ReceiveTask;
import com.spfood.wos.workOrder.intf.domain.SortingTask;
import com.spfood.wos.workOrder.manager.CreateTaskManager;
import com.spfood.wos.workOrder.manager.SortingOrderManager;

public class CreateTaskServiceImpl implements CreateTaskService{
	private static final Logger logger = Logger.getLogger(CreateTaskServiceImpl.class);
	
	@Autowired
	private CreateTaskManager createTaskManager;
	
	@Autowired
	private SortingOrderManager sortingOrderManager;
	
	@Autowired
	private HouseOrderService houseOrderService;

	@Override
	public boolean createTask(List<OutTask> outTaskList,
			List<ReceiveTask> receiveTaskList, 
			List<SortingTask> sortingTaskList,
			List<HouseOrderCommodity> houseOrderCommList) {
		try {
			return createTaskManager.createTask(outTaskList,receiveTaskList,sortingTaskList,houseOrderCommList);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BizException("wos.service.createTask", e.getCause(),
					new Object[] { Arrays.toString(new Object[] { outTaskList,receiveTaskList, sortingTaskList,houseOrderCommList }) });
		}
	}

	/**
	 * 根据ocs的营业时间和集单时间间隔修改分拣任务定时调度
	 */
	@Override
	public void sortTaskScheduleTimeModify() {
		try {
			sortingOrderManager.getOcsWorkTimeAndModifyQuartzFq();
			
		} catch (Exception e) {
			
			logger.error(e.getMessage());
			
			throw new BizException("wos.service.createTask.sortTaskScheduleTimeModify", e.getCause(),"");
		}
		
	}

	/**
	 * 根据ocs的集单数量设置决定是否生成工单
	 */
	@Override
	public void createWorkOrderByCollectCount() {
		
		try {
			
			sortingOrderManager.createWorkOrderByCollectCount();
			
		} catch (Exception e) {
			
			logger.error(e.getMessage());
			throw new BizException("wos.service.createTask.createWorkOrderByCollectCount", e.getCause(),"");
		}
		
	}
	
	
}
