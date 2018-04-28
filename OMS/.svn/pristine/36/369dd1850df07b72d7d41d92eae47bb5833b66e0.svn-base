package com.spfood.oms.order.service.impl;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceLauncher {
    public static void main(String[] args) throws IOException{
    	System.out.println("Start service.");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"local-spring-dubbo.xml","local-spring-environment.xml","spring-context.xml"});
        context.start();
 
        System.in.read();
        context.close();
    }
}
