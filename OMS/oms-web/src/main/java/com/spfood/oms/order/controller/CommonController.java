package com.spfood.oms.order.controller;

import java.text.ParseException;
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


@Controller
@RequestMapping(value="common")
public class CommonController {
	
	private static final Logger logger = Logger.getLogger(CommonController.class);
	
	@Autowired
	private BasicConfigurationService  basicConfigurationService;
	
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
}
