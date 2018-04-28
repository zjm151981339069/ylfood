package com.spfood.oms.quartz.job;



import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.spfood.oms.order.intf.OrderManagerService;
import com.spfood.oms.quartz.common.QuartzJob;

/**
 * 
 * 订单取消定时JOB,执行频率：待定
 *
 */

@QuartzJob(name = "orderCancelJob", cronExp = "0 0/1 * * * ?")
public class OrderCancelJob extends QuartzJobBean
{
	
	private static int count = 0;
	@Autowired
	private OrderManagerService orderManagerService;
	
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		
		count++;
		System.out.println("订单取消定时任务执行"+count+"次");
		orderManagerService.cancelOrderQuartz();
	}
	
}
