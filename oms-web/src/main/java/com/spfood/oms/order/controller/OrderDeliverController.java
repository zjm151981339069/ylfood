package com.spfood.oms.order.controller;



import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.oms.order.intf.OrderManagerService;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderExchange;
import com.spfood.oms.order.intf.searchCriteria.OrderExchangeCriteria;
import com.spfood.oms.order.intf.searchCriteria.OrderSearchCriteria;
import com.spfood.oms.order.util.ExchangeConstant;
import com.spfood.oms.order.util.Pagination;

@Controller
@RequestMapping(value="/deliver")
public class OrderDeliverController {
	
	@Autowired
	private OrderManagerService orderManagerService;
	
	private static final Logger logger = Logger.getLogger(OrderDeliverController.class);
	@RequestMapping(value="/orderTableList")
	@ResponseBody
	public Map<String,Object> geteOrderList(OrderSearchCriteria searchCriteria){
		searchCriteria.setPageNum(searchCriteria.getPage());
		searchCriteria.setDeliverSearch(ExchangeConstant.deliverStatus);
		PageInfo<Order> pageInfo = orderManagerService.getOrderByParas(searchCriteria);
		//得到当前页的条数
		int currentNum = (int)pageInfo.getTotal()-pageInfo.getPageSize()*(pageInfo.getPageNum()-1);
		if(currentNum<pageInfo.getPageSize()){
			pageInfo.setPageSize(currentNum);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("obj", pageInfo);
		return map;
	}
	
	@RequestMapping(value="/list")
	@RequiresPermissions("oms:deliver:show")
	public ModelAndView showMain(ModelAndView modelAndView,OrderSearchCriteria orderSearchCriteria){
		modelAndView.setViewName("deliver/list");
		modelAndView.addObject("searchCriteria", orderSearchCriteria);
		return modelAndView;
		
	}
}
