package com.spfood.oms.orderpay.impl;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ServiceLauncher {
    //启动项目服务
    public static void main(String[] args) throws IOException{
        System.err.println("Start orderPay service.");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"spring-context.xml","spring-environment.xml","spring-dubbo.xml"});
        context.start();
        System.in.read();
    }
}
