/**

 */
package com.spfood.oms.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.spfood.oms.orderinfosyn.intf.OrderFinanceSynService;
import com.spfood.oms.quartz.common.QuartzJob;

/**
 * 订单支付信息同步定时JOB,执行频率：每晚12点执行。
 *
 */
@QuartzJob(name = "orderPayInfoJob", cronExp = "0 0 0 * * ?")
public class OrderPayInfoSynJob extends QuartzJobBean{
	private static int count = 0;
	@Autowired
	private  OrderFinanceSynService orderFinanceSynService;

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		count++;
		System.out.println("订单支付信息同步任务执行"+count+"次");
		orderFinanceSynService.sendOrderPayToFms();
	}

}
