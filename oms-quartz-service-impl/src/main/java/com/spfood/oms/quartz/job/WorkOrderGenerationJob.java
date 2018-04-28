/**

 */
package com.spfood.oms.quartz.job;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.spfood.oms.quartz.common.QuartzJob;
import com.spfood.wos.workOrder.intf.WorkOrderService;

/**
 * 工单生成定时任务,执行频率待定
 *
 */
@QuartzJob(name = "workOrderGenerationJob", cronExp = "0 0/1 * * * ?")
public class WorkOrderGenerationJob extends QuartzJobBean{
	
	private static final Logger logger = Logger.getLogger(WorkOrderGenerationJob.class);

	private static int count = 0;
	@Autowired
	private WorkOrderService  workOrderService;
	
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		count++;
		System.out.println("订单取消定时任务执行"+count+"次");
		try {
			workOrderService.createWorkOrderByHouseOrder();
		} catch (Exception e) {
			logger.error("WorkOrderGenerationJob定时调度createWorkOrderByHouseOrder方法异常", e);
		}
	}

}
