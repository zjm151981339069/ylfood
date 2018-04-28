package com.spfood.oms.orderinfosyn.quartz;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;


/**
 *支付信息同步到FMS定时调度类
 *
 */
public class OrderPayInfoToFmsJob extends QuartzJobBean{
	
	private static final Logger logger = Logger.getLogger(OrderPayInfoToFmsJob.class);
	
	//调度执行的目标对象
	private String targetObject;
	
	//调度执行的目标对象
	private String targetMethod;
	
	private ApplicationContext ctx;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info("进入OrderPayInfoToFmsJob的executeInternal方法,支付信息同步到FMS定时调度开始执行......................");
		
		Object tarObj = ctx.getBean(targetObject);
		
		Method tarMethod = null;
			
			try {
				tarMethod = tarObj.getClass().getMethod(targetMethod, new Class[] {});
				
				tarMethod.invoke(tarObj, new Object[] {});
				
			} catch (NoSuchMethodException | SecurityException e) {
				
				logger.error("支付信息同步到FMS出现异常", e);
				
			}catch(Exception e){
				
				logger.error("支付信息同步到FMS出现异常", e);
				
				throw new JobExecutionException(e);
			}
			
			logger.info("OrderPayInfoToFmsJob的executeInternal方法执行完成,支付信息同步到FMS定时调度执行将结束......................");
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
