package com.spfood.oms.order.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import com.spfood.common.security.EmployeeDTO;
import com.spfood.kernel.dao.PageInfo;
import com.spfood.oms.order.intf.OrderManagerService;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.searchCriteria.OrderSearchCriteria;

/*
 * @Author:Isidore Han
 * @Date:2016/12/12
 * @Des:The controller that use to create and modify the order
 */
@Controller
@RequestMapping(value="order")
public class OrderController {

	@Autowired
	private OrderManagerService orderManagerService;
	
	private static final Logger logger = Logger.getLogger(OrderController.class);
	@RequestMapping(value="/orderList")
	@ResponseBody
	public Map<String,Object> geteOrderList(OrderSearchCriteria searchCriteria){
		searchCriteria.setPageNum(searchCriteria.getPage());
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
	
	
	//查看订单详情
	@RequestMapping(value="/orderDetail") 
	public ModelAndView getOrderDetail(OrderSearchCriteria searchCriteria){
		ModelAndView modelAndView = new ModelAndView("order/orderDetail");
		Order order = orderManagerService.getOrderDetail(searchCriteria.getOrderNo());
		modelAndView.addObject("order",order);
		modelAndView.addObject("searchCriteria",searchCriteria);
		return modelAndView;
	}
	
	
	//取消订单
		@RequestMapping(value="/cancelOrder")
		@ResponseBody
		public String cancelOrder(String orderNo){
			String username = ((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getAccount();//获取用户名
			Long employeeID = ((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getEmployeeID();//当前用户编号
			boolean flag = orderManagerService.cancelOrder(orderNo,username,String.valueOf(employeeID));
			if(flag){
				return "SUCCESS";
			}else{
				return "ERROR";
				
			}
		}
	
	/**
	 * oms's main page
	 * @return
	 */
	@RequestMapping(value="/list")
	@RequiresPermissions("oms:order:show")
	public ModelAndView showMain(OrderSearchCriteria orderSearchCriteria){
		ModelAndView modelAndView = new ModelAndView("order/orderManage");
		modelAndView.addObject("searchCriteria",orderSearchCriteria);
		return modelAndView;
	}
	

	
	
	//设置配送时间
	@RequestMapping(value="/setDeliverTime",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String setDeliverTime(String orderCode,Date deliverTime,Integer status) {
		String username = ((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getAccount();
		Long employeeID = ((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getEmployeeID();
		return orderManagerService.setDeliverTime(orderCode,deliverTime,username,String.valueOf(employeeID));
	}
	
	//标记信息
	@RequestMapping(value="/setSign",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String setSign(String orderCode,String sign) {
		String username = ((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getAccount();
		Long employeeID = ((EmployeeDTO)SecurityUtils.getSubject().getPrincipal()).getEmployeeID();
		String signCopy = HtmlUtils.htmlEscape(sign,"UTF-8");
		String result = orderManagerService.addOrUpdateSign(orderCode,signCopy,username,String.valueOf(employeeID));
		return result;
	}
}
