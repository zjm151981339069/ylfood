package com.spfood.oms.orderinfosyn.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 直接通过Spring 上下文获取SpringBean,用于多线程环境
 * 
 * @author Administrator
 *
 */
@Component
public class SpringBeanUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        SpringBeanUtils.applicationContext = applicationContext;
    }

    /**
     * 通过名字得到bean对象
     * 
     * @param beanName bean的名字
     * @return
     */
    public static Object getBeanByName(String beanName) {
        if (applicationContext == null) {
            return null;
        }
        return applicationContext.getBean(beanName);
    }

    /**
     * 通过类型得到bean对象
     * 
     * @param type  bean的类型
     * @return
     */
    public static <T> T getBean(Class<T> type) {
        return applicationContext.getBean(type);
    }

}
