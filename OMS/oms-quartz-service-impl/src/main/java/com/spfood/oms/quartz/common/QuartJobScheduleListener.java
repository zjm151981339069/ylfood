package com.spfood.oms.quartz.common;

import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;

public class QuartJobScheduleListener implements ApplicationListener<ContextRefreshedEvent>
{
	private Logger logger = Logger.getLogger(QuartJobScheduleListener.class);

	@Autowired
	private Scheduler scheduler;

	public void onApplicationEvent(ContextRefreshedEvent event)
	{
		try
		{
			ApplicationContext applicationContext = event.getApplicationContext();
			this.loadCronTriggers(applicationContext, scheduler);
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
	}

	private void loadCronTriggers(ApplicationContext applicationContext, Scheduler scheduler)
	{
		Map<String, Object> quartzJobBeans = applicationContext.getBeansWithAnnotation(QuartzJob.class);
		Set<String> beanNames = quartzJobBeans.keySet();
		for (String beanName : beanNames)
		{
			Object object = quartzJobBeans.get(beanName);
			try
			{
				if (Job.class.isAssignableFrom(object.getClass()))
				{
					QuartzJob quartzJobAnnotation = AnnotationUtils.findAnnotation(object.getClass(), QuartzJob.class);
					JobKey jobKey = new JobKey(quartzJobAnnotation.name(), quartzJobAnnotation.group());
					@SuppressWarnings("unchecked")
					JobDetail job = JobBuilder
							.newJob((Class<? extends Job>) object.getClass())
							.withIdentity(jobKey)
							.build();
					CronTrigger cronTrigger = TriggerBuilder
							.newTrigger()
							.withIdentity(quartzJobAnnotation.name() + "_trigger", quartzJobAnnotation.group())
							.withSchedule(CronScheduleBuilder.cronSchedule(quartzJobAnnotation.cronExp()))
							.build();
					scheduler.scheduleJob(job, cronTrigger);
				}
				else
				{
					String errorMsg = object.getClass() + " doesn't implemented " + Job.class.getName();
					logger.error(errorMsg);
					throw new RuntimeException(errorMsg);
				}
			}
			catch (Exception e)
			{
				logger.error(e.getMessage(), e);
			}
		}
	}
}
