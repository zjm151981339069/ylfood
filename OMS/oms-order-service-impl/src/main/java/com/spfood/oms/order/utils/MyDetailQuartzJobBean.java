package com.spfood.oms.order.utils;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MyDetailQuartzJobBean extends QuartzJobBean {
	private static final Logger logger = Logger.getLogger(MyDetailQuartzJobBean.class);
	private String targetObject;
	private String targetMethod;
	private ApplicationContext ctx;
	
	@Override
	protected void executeInternal(JobExecutionContext context)throws JobExecutionException {
		logger.info("开始执行定时任务......");
		try {
			logger.info("当前执行IP: "+InetAddress.getLocalHost());
		} catch (UnknownHostException e) {
            logger.info("当前执行IP: unknown host!");
		}
		try {
			Object otargetObject = ctx.getBean(targetObject);
			Method m = null;
			m = otargetObject.getClass().getMethod(targetMethod, new Class[] {});
			m.invoke(otargetObject, new Object[] {}); 
		} catch (Exception e) {
			logger.error(e);  
			throw new JobExecutionException(e);
		}
		logger.info("定时任务执行结束......");
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.ctx = applicationContext;
	}

	public void setTargetObject(String targetObject) {
		this.targetObject = targetObject;
	}

	public void setTargetMethod(String targetMethod) {
		this.targetMethod = targetMethod;
	}
}