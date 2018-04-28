package com.spfood.wos.workOrder.thread;


import org.apache.log4j.Logger;

import com.spfood.kernel.exception.BizException;



/**
 * 拆分订单处理线程
 * @author Administrator
 *
 */
public class ResolveOrderThread implements Runnable{
    Logger logger = Logger.getLogger(ResolveOrderThread.class);
    //任务对象
    private  ResolveOrderTask task; 
        
    public ResolveOrderThread() {
        super();
    }
    public ResolveOrderThread(ResolveOrderTask tasks) {
        super();
        this.task = tasks;
    }
    /**
     * 订单线程执行的方法
     */
    @Override
    public void run() {
        //执行线程方法
        try {
            task.execute();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException("wos.service.quartz.createWorkOrder", e.getCause(),"mutlthread create worksOrder exception");
        }
    }
    
    
   
    
}

