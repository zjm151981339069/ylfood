package com.spfood.wos.workOrder.service.impl;



import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spfood.ocs.access.intf.ParamsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:local-spring-environment.xml", "classpath:spring-test-context.xml","classpath:spring-test-quartz.xml","classpath:spring-test-quartz.xml" ,"classpath:spring-test-mq.xml","classpath:local-spring-dubbo.xml"})   
public class CreateTaskServiceTest {
	
	@Autowired
	private ParamsService paramsService;
	
	
	@Value("#{configProperties['create.workOrder.quartz.propertyName']}")
	 private String propertyName;
	 
	 @Value("#{configProperties['org.quartz.dataSource.myDS.URL']}")
	 private String driverClassName;
	 
	 @Value("#{configProperties['create.workOrder.quartz.fileName']}")
	 private String fileName;
	 
	@Test
	public void test() throws IOException, URISyntaxException{
		try {
			System.out.println(driverClassName+"-------------------------------");
			System.out.println(propertyName+"-------------------------------");
			System.out.println(fileName+"-------------------------------");
			Thread.sleep(1000*60*5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
