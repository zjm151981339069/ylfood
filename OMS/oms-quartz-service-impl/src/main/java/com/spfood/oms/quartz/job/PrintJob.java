package com.spfood.oms.quartz.job;


import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.spfood.oms.quartz.common.QuartzJob;



//@QuartzJob(name = "PrintJob", cronExp = "0/10 * * * * ?")
public class PrintJob extends QuartzJobBean
{
	
	private static int count = 0;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException
	{
		System.err.println(count+1);
		count++;
		if(count>10){
			System.out.println();
		}
		System.err.println("Hello Job is running " + new Date() + this.hashCode());
		
	}
}
