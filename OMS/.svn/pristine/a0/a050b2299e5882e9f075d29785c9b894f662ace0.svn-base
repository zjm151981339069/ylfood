package com.spfood.oms.orderinformsyn.impl;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceLauncher {
//    //启动项目服务
//    public static void main(String[] args) throws IOException{
//        System.out.println("Start orderInfoSyn  service.");
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
//                new String[] {"spring-context.xml","spring-quartz.xml","spring-environment.xml","spring-dubbo.xml"});
//        context.start();
//        System.in.read();
//    }
	
	 //启动项目服务
    public static void main(String[] args) throws IOException{
        System.out.println("Start 财务同步服务.");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"local-spring-environment.xml","spring-test-context.xml","spring-quartz.xml"});
        context.start();
        System.in.read();
        context.close();
    }
}
