package com.spfood.wos.workOrder.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.wos.workOrder.dao.HouseOrderCommodityDao;
import com.spfood.wos.workOrder.dao.OutTaskDao;
import com.spfood.wos.workOrder.dao.ReceiveTaskDao;
import com.spfood.wos.workOrder.dao.SortingTaskDao;
import com.spfood.wos.workOrder.intf.domain.HouseOrderCommodity;
import com.spfood.wos.workOrder.intf.domain.OutTask;
import com.spfood.wos.workOrder.intf.domain.ReceiveTask;
import com.spfood.wos.workOrder.intf.domain.SortingTask;
import com.spfood.wos.workOrder.manager.CreateTaskManager;

@Service
public class CreateTaskManagerImpl implements CreateTaskManager{
	
	@Autowired
	private OutTaskDao outTaskDao;
	@Autowired
	private ReceiveTaskDao receiveTaskDao;
	@Autowired
	private SortingTaskDao sortingTaskDao;
	@Autowired
	private HouseOrderCommodityDao houseOrderCommodityDao;

	@Override
	public boolean createTask(List<OutTask> outTaskList,
			List<ReceiveTask> receiveTaskList, 
			List<SortingTask> sortingTaskList,
			List<HouseOrderCommodity> houseOrderCommList) {
		if (outTaskList == null || receiveTaskList == null || sortingTaskList == null || houseOrderCommList == null) {
			return false;
		}
		if (outTaskList.isEmpty() || receiveTaskList.isEmpty() || sortingTaskList.isEmpty() || houseOrderCommList.isEmpty()) {
			return false;
		}
		outTaskDao.insertInBatch(outTaskList);//保存出库任务单、任务单关联商品
		receiveTaskDao.insertInBatch(receiveTaskList);//保存领料任务单、任务单关联商品
		sortingTaskDao.insertInBatch(sortingTaskList);//保存分拣任务单、任务单关联商品
		houseOrderCommodityDao.updateTaskCodeInBatch(houseOrderCommList);//更新订单商品关联任务单
		
		return true;
	}
}
