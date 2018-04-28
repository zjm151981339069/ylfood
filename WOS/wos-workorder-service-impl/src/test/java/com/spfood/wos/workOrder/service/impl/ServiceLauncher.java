package com.spfood.wos.workOrder.service.impl;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceLauncher {
    public static void main(String[] args) throws IOException{
    	System.out.println("Start service.");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"local-spring-environment.xml","spring-test-context.xml","local-spring-dubbo.xml","spring-test-quartz.xml","spring-test-mq.xml"});
        context.start();
        System.out.println("OK");
        System.in.read();
    }
}
