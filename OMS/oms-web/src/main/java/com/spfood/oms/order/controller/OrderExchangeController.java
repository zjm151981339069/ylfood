package com.spfood.oms.order.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;










import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.oms.order.intf.OrderCreateService;
import com.spfood.oms.order.intf.OrderExchangeService;
import com.spfood.oms.order.intf.OrderManagerService;
import com.spfood.oms.order.intf.domain.Order;
import com.spfood.oms.order.intf.domain.OrderExchange;
import com.spfood.oms.order.intf.searchCriteria.OrderExchangeCriteria;
import com.spfood.oms.order.util.ExchangeConstant;

/**
 * 换货单管理
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "exchange")
public class OrderExchangeController {

	@Autowired(required = true)
	private OrderExchangeService orderExchangeService;
	@Autowired(required = true)
	private OrderManagerService orderManagerService;
	@Autowired
	private OrderCreateService orderCreateService;

	private static final Logger logger = Logger.getLogger(OrderExchangeController.class);
	
	@RequestMapping(value="/exchangeOrderList")
	@ResponseBody
	public Map<String,Object> getOrderExchangeList(OrderExchangeCriteria exchangeCriteria){
		exchangeCriteria.setPageNum(exchangeCriteria.getPage());
		PageInfo<OrderExchange> pageInfo = orderExchangeService.getOrderExchangeByParas(exchangeCriteria);
		//得到当前页的条数
		int currentNum = (int)pageInfo.getTotal()-pageInfo.getPageSize()*(pageInfo.getPageNum()-1);
		if(currentNum<pageInfo.getPageSize()){
			pageInfo.setPageSize(currentNum);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("obj", pageInfo);
		return map;
	}
	
	//换货列表展示
	@RequestMapping(value = "list")
	@RequiresPermissions("oms:exchange:show")
	public ModelAndView showMain(ModelAndView modelAndView, OrderExchangeCriteria orderExchangeCriteria) {
		modelAndView.setViewName("exchange/list");
		modelAndView.addObject("orderExchangeCriteria",orderExchangeCriteria);
		return modelAndView;

	}
	
	// 去换货单操作页面
	@RequestMapping(value = "toEdit")
	public ModelAndView toEdit(ModelAndView modelAndView,OrderExchangeCriteria exchangeCriteria) {
		
		modelAndView.setViewName("exchange/orderExchangeDetail");
		OrderExchange orderExchange = orderExchangeService.selectOrderExchangeByExid(exchangeCriteria.getExId());
			modelAndView.addObject("orderExchange",orderExchange);
			modelAndView.addObject("exchangeCriteria",exchangeCriteria);
		return modelAndView;
	}

	// 审核通过后的订单查询
	@RequestMapping(value = "qeueryOrderEchange")
	public ModelAndView qeueryOrderEchange(ModelAndView modelAndView,OrderExchangeCriteria exchangeCriteria) {
		modelAndView.setViewName("exchange/afterSalesOrder");
		// 进行判断，只有状态为1已审核才能查看
		String orderCode = exchangeCriteria.getExCode();
		if (orderCode != null && !"".equals(orderCode)) {
			Order order = orderManagerService.getOrderDetail(orderCode);
			order.setSite(ExchangeConstant.EXCHANGE_SITE);
			modelAndView.addObject("orderDetail",order);
			modelAndView.addObject("exchangeCriteria",exchangeCriteria);
			return modelAndView;
		}
		return modelAndView; 
	}

	// 审核通过操作
	@RequestMapping(value = "passOrderExchange")
	@ResponseBody
	public String passOrderExchange(OrderExchangeCriteria orderExchangeCriteria){
		if(orderExchangeCriteria.getAuditview() != null){
			orderExchangeCriteria.setAuditview(HtmlUtils.htmlEscape(orderExchangeCriteria.getAuditview(),"UTF-8"));
			if(orderExchangeService.passOrderExchangeByExid(orderExchangeCriteria)){
				return "SUCCESS";
			}else{
				return "ERROR";
			}
		}
		return "ERROR";
	}

	// 审核不通过操作
	@RequestMapping(value = "cancleOrderExchange")
	@ResponseBody
	public String cancelOrderExchange(OrderExchangeCriteria orderExchangeCriteria)  {
		if (orderExchangeCriteria.getAuditview() != null ) {
			orderExchangeCriteria.setAuditview(HtmlUtils.htmlEscape(orderExchangeCriteria.getAuditview(),"UTF-8"));
			if(orderExchangeService.cancelOrderExchangeByExid(orderExchangeCriteria)){
				return "SUCCESS";
			}else{
				return "ERROR";
			}
		}
		return "ERROR";
		
	}

}