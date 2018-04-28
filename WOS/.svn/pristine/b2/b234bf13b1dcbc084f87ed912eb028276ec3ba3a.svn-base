package com.spfood.wos.workOrder.manager.impl;


import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.spfood.ocs.access.intf.ParamsService;
import com.spfood.ocs.access.utils.BaseResult;
import com.spfood.wos.workOrder.intf.domain.HouseOrderTemp;
import com.spfood.wos.workOrder.manager.CreateWorkOrderManager;
import com.spfood.wos.workOrder.manager.HouseOrderManager;
import com.spfood.wos.workOrder.manager.SortingOrderManager;
import com.spfood.wos.workOrder.utils.PropertiesUtils;
import com.spfood.wos.workOrder.utils.QuartzJob;
import com.spfood.wos.workOrder.utils.QuartzManager;


@Service
public class SortingOrderManagerImpl implements SortingOrderManager{

	private static final Logger logger = Logger.getLogger(SortingOrderManagerImpl.class);

	@Autowired
	private ParamsService paramsService;
	
	@Autowired
	private QuartzManager quartzManage;
	
	@Autowired
	private HouseOrderManager houseOrderManager;
	
	@Autowired
	private CreateWorkOrderManager createWorkOrderManager;
	
	
	 @Value("#{configProperties['create.workOrder.quartz.trigger.name']}")
	 private String triggerNameModify;
	 
	 @Value("#{configProperties['create.workOrder.quartz.fileName']}")
	 private String fileName;
	 
	 @Value("#{configProperties['create.workOrder.quartz.propertyName']}")
	 private String propertyName;
	 
	 @Value("#{configProperties['mybatis.driverClassName']}")
	 private String driverClassName;
	 
	
	/**
	 * 获取Ocs营业时间并修改分拣任务的调度时间
	 */
	@Override
	public void getOcsWorkTimeAndModifyQuartzFq() throws Exception {
		
		String triggerName = triggerNameModify;
		//上班时间(时)
		String workTime = null;
		//下班时间(时)
		String offTime = null;
		//集单间隔时间(分钟)
		String collecTimeInterVal = null;
		//集单数量
		String nowCollectCount = null;
		
		//获取oc营业时间、集单时间间隔等信息
		BaseResult<Map<String,String>> baseResult = paramsService.queryList();
		if(baseResult != null){
			Map<String,String> map = baseResult.getResultObj();
			if(map != null){
				workTime = map.get("workTime");
				offTime = map.get("offTime");
				collecTimeInterVal = map.get("collecTimeInterVal");
				nowCollectCount = map.get("collectCount");
			}
		}
		
		logger.info("从ocs获取参数结果: workTime--"+workTime+" offTime--"+offTime+"collecTimeInterVal--"+collecTimeInterVal);
		String cronExpression = getcronExpression(workTime,offTime,collecTimeInterVal);
		logger.info("根据ocs获取参数拼接出来的表达式："+cronExpression);
		if(!cronExpression.equals("")){
			
			QuartzJob quartzJob = new QuartzJob();
			quartzJob.setTriggerName(triggerName);
			quartzJob.setCronExpression(cronExpression.toString());
			quartzManage.updateTrigger(quartzJob);
			
		}
		logger.info("修改表达式成功。。。");
		//将查询出的collectCount修改到配置文件的create.workOrder.quartz.collectCount
		//1、先获取配置文件中create.workOrder.quartz.collectCount
		//要读取的配置文件路径
		String path = "environments"+File.separator+fileName+".properties";
		String oldCollectCount =  PropertiesUtils.getProperties(path,propertyName);
		logger.info("系统中现在的collectCount值："+oldCollectCount);
		logger.info("从ocs获取的集单数量值："+nowCollectCount);
		//2、如果配置文件的值和从ocs中获取的值不等，则修改配置文件的值为从ocs查询到的值
		if(!oldCollectCount.equals(nowCollectCount)){
			PropertiesUtils.setProperties(path,propertyName, nowCollectCount);
		}
		logger.info("修改集单数量成功，修改后的值为："+nowCollectCount);
	}

	/**
	 * 根据ocs的集单数量设置决定是否生成工单
	 * @throws Exception 
	 */
	@Override
	public void createWorkOrderByCollectCount() throws Exception {
		//要读取的配置文件路径
		String path = "environments"+File.separator+fileName+".properties";
		String collectCount = PropertiesUtils.getProperties(path,propertyName);
		List<HouseOrderTemp> list = houseOrderManager.getTempOrder();
		if(list != null && list.size() > Integer.valueOf(collectCount)){
			logger.info("查询出要生成工单的数量为："+list.size());
			createWorkOrderManager.createWorkOrder();
			logger.info("根据ocs设置的集单数量生成工单成功，生成的工单数量为："+list.size());
		}
	}	
	
	public String getcronExpression(String workTime,String offTime,String collecTimeInterVal) {
		StringBuffer cronExpression = new StringBuffer();
		//根据从ocs获取的参数拼接定时任务执行频率表达式
		if(collecTimeInterVal != null && !collecTimeInterVal.equals("")){
			cronExpression.append("0 0/"+collecTimeInterVal+" ");
		}
		if(workTime != null && !workTime.equals("") && offTime != null && !offTime.equals("")){
			String workHour = workTime.substring(11,13);
			String offHour = offTime.substring(11,13);
			
			cronExpression.append(workHour+"-"+offHour+" ");
		}
		if(!cronExpression.toString().equals("")){
			cronExpression.append("* * ?");
		}
		return cronExpression.toString();
	}
}
