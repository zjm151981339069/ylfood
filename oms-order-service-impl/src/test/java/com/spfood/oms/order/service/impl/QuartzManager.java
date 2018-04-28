package com.spfood.oms.order.service.impl;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spfood.oms.order.utils.MyDetailQuartzJobBean;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations={"classpath:spring-test-quartz.xml"})  
public class QuartzManager extends BaseTest{


	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	
	@Autowired
	private Scheduler scheduler;
	@Test
	public void start() throws IOException, SchedulerException{
		String schedulerName = scheduler.getSchedulerName();
		System.out.println(schedulerName);
		System.in.read();
	}

	//添加
	@Test
	public void scheduleJob() throws IOException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {  
            JobDetail jobDetail = JobBuilder.newJob(MyDetailQuartzJobBean.class)
                .withIdentity("testTask3", "DEFAULT").build();  
            jobDetail.getJobDataMap().put("targetObject", "orderManagerService");
            jobDetail.getJobDataMap().put("targetMethod", "test3");
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");  
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("testTrigger3", "DEFAULT")  
                .withSchedule(scheduleBuilder).build();  
            trigger.getTimeZone().setID("GMT+08:00");
            scheduler.scheduleJob(jobDetail, trigger);  
        } catch (Exception e) {  
        }  
		System.in.read();
	}
	
	//更新
	@Test
	public void update() throws SchedulerException, IOException{
		System.out.println("==============================================");
		//Scheduler scheduler = schedulerFactoryBean.getScheduler();
		
		TriggerKey triggerKey = TriggerKey.triggerKey("orderCancelTrigger", "DEFAULT");
		
		//获取trigger，即在spring配置文件中定义的 
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		  
		//表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 20,25 * * * ?");
		//按新的cronExpression表达式重新构建trigger
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
		.withSchedule(scheduleBuilder).build();
		trigger.getTimeZone().setID("GMT+08:00");
		//按新的trigger重新设置job执行
		scheduler.rescheduleJob(triggerKey, trigger);
		System.out.println("*********************");
		System.in.read();
	}
	
	//更新
	@Test
	public void update1() throws SchedulerException, IOException{
		System.out.println("==============================================");
		//Scheduler scheduler = schedulerFactoryBean.getScheduler();
		
		TriggerKey triggerKey = TriggerKey.triggerKey("orderCancelTrigger", "DEFAULT");
		
		//获取trigger，即在spring配置文件中定义的 
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		  
		//表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 30,35 * * * ?");
		//按新的cronExpression表达式重新构建trigger
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
		.withSchedule(scheduleBuilder).build();
		trigger.getTimeZone().setID("GMT+08:00");
		//按新的trigger重新设置job执行
		scheduler.rescheduleJob(triggerKey, trigger);
		System.out.println("*********************");
	}
	//删除
	@Test
	public void delete() throws SchedulerException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey("testTask", "DEFAULT");
		scheduler.deleteJob(jobKey);
	}
	
	//立即运行
	@Test
	public void triggerJob() throws SchedulerException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey("orderCancelTask", "DEFAULT");
		scheduler.triggerJob(jobKey);
	}
	
	//暂停
	@Test
	public void pauseJob() throws SchedulerException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey("orderCancelTask", "DEFAULT");
		scheduler.pauseJob(jobKey);
	}
	
	//恢复
	@Test
	public void resumeJob() throws SchedulerException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey("orderCancelTask", "DEFAULT");
		scheduler.resumeJob(jobKey);
	}
	
}
