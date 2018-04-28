package com.spfood.oms.orderinfosyn.QuartzJob;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.spfood.oms.orderinfosyn.intf.OrderFinanceSynService;
/**
 * 定时发送财务同步信息
 * @author Administrator
 *
 */
public class TimeToSendOrderPay extends QuartzJobBean {
	private static final Logger logger = Logger
			.getLogger(TimeToSendOrderPay.class);

	@Autowired
	private OrderFinanceSynService orderFinanceSynService;

	protected void executeInternal(JobExecutionContext jobExecutionContext)throws JobExecutionException {

		logger.info("开始启动执行财务定时同步:::::" + System.currentTimeMillis());

		orderFinanceSynService.sendOrderPayToFms();

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
		logger.info("定时任务执行结束::::" + sdf.format(date));

	}
}
