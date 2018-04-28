package com.spfood.oms.orderRetrieve.service.impl;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceLauncher {
    public static void main(String[] args) throws IOException{
    	System.out.println("Start service.");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"spring-test-context.xml","spring-dubbo.xml","local-spring-environment.xml"});
        context.start();
 
        System.in.read();
        context.close();
    }
}
