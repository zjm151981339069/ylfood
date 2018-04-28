package com.spfood.wos.workOrder.constants;
/**
 * 多线程任务统计类，实时统计此次调度正在执行任务的线程数量
 * @author Administrator
 *
 */
public class ResolveOrderTaskExecuteCount {
    private static Integer count=0;

    public static synchronized Integer getCount() {
        return count;
    }

    public static synchronized void setCount(Integer count) {
        ResolveOrderTaskExecuteCount.count = count;
    }
    
}
