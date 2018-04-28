package com.spfood.wos.workOrder.utils;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuartzManager{
	private static final Logger logger = Logger.getLogger(QuartzManager.class);

	@Autowired
	private Scheduler scheduler;
	
	/**
	 * 修改定时任务触发器表达式
	 * @param quartzJob 设置triggerName和cronExpression
	 * @throws SchedulerException
	 */
	public void updateTrigger(QuartzJob quartzJob) {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(quartzJob.getTriggerName(), quartzJob.getTriggerGroup());
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression());
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
			.withSchedule(scheduleBuilder).build();
			trigger.getTimeZone().setID(quartzJob.getTimeZoneID());
			//按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		} catch (SchedulerException e) {
			logger.error(e);
		}
	}
	
}
