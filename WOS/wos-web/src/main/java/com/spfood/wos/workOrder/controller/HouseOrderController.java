package com.spfood.wos.workOrder.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;






import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spfood.kernel.dao.PageInfo;
import com.spfood.wos.workOrder.intf.HouseOrderService;
import com.spfood.wos.workOrder.intf.WorkOrderService;
import com.spfood.wos.workOrder.intf.domain.HouseOrder;
import com.spfood.wos.workOrder.intf.searchCriteria.HouseOrderSearchCriteria;

@Controller
@RequestMapping(value="houseOrder")
public class HouseOrderController {
	
	private static final Logger logger = Logger.getLogger(HouseOrderController.class);
	
	@Autowired
	private HouseOrderService houseOrderService;
	@Autowired
	private WorkOrderService workOrderService;
	
	@RequestMapping(value="/houseOrderList")
	@ResponseBody
	public Map<String,Object> getOrderExchangeList(HouseOrderSearchCriteria searchCriteria){
		searchCriteria.setPageNum(searchCriteria.getPage());
		searchCriteria.setOrderNo(searchCriteria.getSearchOrderNo());
		PageInfo<HouseOrder> pageInfo = houseOrderService.getHouseOrderListByCriteria(searchCriteria);
		//得到当前页的条数
		int currentNum = (int)pageInfo.getTotal()-pageInfo.getPageSize()*(pageInfo.getPageNum()-1);
		if(currentNum<pageInfo.getPageSize()){
			pageInfo.setPageSize(currentNum);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("obj", pageInfo);
		return map;
	}
	
	/**
	 * 列表展示
	 * @param modelAndView
	 * @param searchCriteria
	 * @return
	 */
	@RequestMapping(value="/list")
	@RequiresPermissions("wos:houseOrder:show")
	public ModelAndView showMain(ModelAndView modelAndView,HouseOrderSearchCriteria searchCriteria){
		modelAndView.setViewName("houseOrder/list");
		modelAndView.addObject("searchCriteria", searchCriteria);
		return modelAndView;
	}
	
	/**
	 * 订单详情查询
	 * @param modelAndView
	 * @param orderNo
	 * @return
	 */
	@RequestMapping(value = "houseOrderDetail")
	public ModelAndView showHouseOrder(ModelAndView modelAndView,HouseOrderSearchCriteria searchCriteria){
		
		modelAndView.setViewName("houseOrder/houseOrderDetail");
		HouseOrder houseOrder = houseOrderService.getOrder(searchCriteria.getOrderNo());
		modelAndView.addObject("houseOrder",houseOrder);
		modelAndView.addObject("searchCriteria", searchCriteria);
		return modelAndView;
		
	}
	
	/**
	 * 生成分拣任务
	 * @param modelAndView
	 * @param houseOrderNos
	 * @return
	 */
	@RequestMapping(value = "createTask")
	public @ResponseBody String createTask(String orderNos,Model model){
		
		String[] orderCodes = orderNos.split(",");
		List<String> houseOrderNos = Arrays.asList(orderCodes);
		try {
			workOrderService.specificHouseOrdersOpearation(houseOrderNos);
			return "SUCCESS";
		} catch (Exception e) {
			e.printStackTrace();
			return "FALSE";
		}
	}
}
