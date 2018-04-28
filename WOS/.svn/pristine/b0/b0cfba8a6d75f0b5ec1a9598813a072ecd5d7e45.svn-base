package com.spfood.wos.workOrder.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;













import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spfood.basicservice.intf.BasicConfigurationService;
import com.spfood.basicservice.intf.ConfigureOptionDTO;
import com.spfood.basicservice.intf.DivisionNode;
import com.spfood.basicservice.intf.DivisionReaderService;
import com.spfood.ocs.access.domain.ServiceSite;
import com.spfood.ocs.access.intf.ServiceSiteService;



@Controller
@RequestMapping(value="common")
public class CommonController {
	
	private static final Logger logger = Logger.getLogger(CommonController.class);
	
	@Autowired
	private BasicConfigurationService  basicConfigurationService;
	
	@Autowired
	private DivisionReaderService  divisionReaderService;
	
	
	@Autowired
	private ServiceSiteService  serviceSiteService;
	
	/**
	 * 表basic_configure_options中根据configureItemName查询所有的optionValue和optionName
	 * @param getConfigureOptions
	 * @return
	 */
	@RequestMapping(value="/configOptions")
	@ResponseBody
	public List<ConfigureOptionDTO> getConfigureOptions(@RequestParam(name="code",required=false)String getConfigureOptions){
			List<ConfigureOptionDTO> list;
			try {
				list = basicConfigurationService.getConfigureOptions(getConfigureOptions);
				return list;
			} catch (ParseException e) {
				e.printStackTrace();
				logger.error("调用basicConfigurationService服务从表basic_configure_options中根据configureItemName查询optionValue和optionName出现错误",e);
				return null;
			}
	}
	
	/**
	 * 获取所有城市
	 * @return
	 */
	@RequestMapping(value="/getCityHasBusiness")
	@ResponseBody
	public List<DivisionNode> getCityHasBusiness(){
		return divisionReaderService.getDivisionHasBusiness();
	}
	/**
	 * 获取自提点,当areaCode不为空时，根据areaCode查询这个areaCode下对应的自提点
	 * @return
	 */
	@RequestMapping(value="/getServiceSite")
	@ResponseBody
	public List<ServiceSite > getServiceSite(@RequestParam(name="code",required=false)String areaCode){
		List<ServiceSite > list = new ArrayList<ServiceSite>();
		if(areaCode != null && areaCode !=""){
			list= serviceSiteService.queryListByCitycode(areaCode);
			return list;
		}else{
			return list;
		}
	}
}
