package com.spfood.oms.orderinfosyn.QuartzJob;

import java.text.ParseException;

import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

public class PersistableCronTriggerFactoryBean extends CronTriggerFactoryBean {
	@Override
	public void afterPropertiesSet() {
		try {
			super.afterPropertiesSet();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("持久化到数据库-------------------");
		// Remove the JobDetail element
	}
}
