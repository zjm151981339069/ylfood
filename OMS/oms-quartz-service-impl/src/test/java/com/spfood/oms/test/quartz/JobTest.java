package com.spfood.oms.test.quartz;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author admin
 * @date 2017年1月15日
 * 
 */

public class JobTest {
	
public static void main(String[] args) throws IOException {
		
		 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring-context.xml","spring-dubbo.xml"});
		 
		 context.start(); 
		 System.out.println("ok");
	     System.in.read();  
	     context.close();
	}
	
}
