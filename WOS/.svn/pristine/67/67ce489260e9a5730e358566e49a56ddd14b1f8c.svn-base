package com.spfood.wos.workOrder.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spfood.wos.workOrder.constants.SendOrderAmount;
import com.spfood.wos.workOrder.intf.domain.HouseOrder;
import com.spfood.wos.workOrder.intf.domain.HouseOrderCommodity;
import com.spfood.wos.workOrder.intf.domain.HouseOrderTemp;
import com.spfood.wos.workOrder.manager.HouseOrderManager;
import com.spfood.wos.workOrder.manager.WorkOrderExtractManager;
import com.spfood.wos.workOrder.utils.SiteCodeAreaCodeLinkerUtils;
@Service
public class WorkOrderExtractManagerImpl implements WorkOrderExtractManager{
    @Autowired
    private HouseOrderManager houseOrderManager;   
    @Autowired
    private SendOrderAmount sendOrderAmount;
    Logger logger = Logger.getLogger(WorkOrderExtractManagerImpl.class);
    @Override
    public List<String> getOrderTempCodeStrings() {
        List<HouseOrderTemp> orderTemps = houseOrderManager.getTempOrder();
        List<String> orderCodesList = new ArrayList<String>();
        if (orderTemps != null && orderTemps.size() > 0) {
            for (HouseOrderTemp orderTemp : orderTemps) {
                orderCodesList.add(orderTemp.getOrderNo());
            }
        }
        return orderCodesList;
    }

    @Override
    public void traverseOrderCommodities(Map<String, List<HouseOrderCommodity>> map,
            List<HouseOrder> orders) {
        try {
            //遍历订单
        	for (HouseOrder houseOrder : orders) {
            	logger.info("订单商品"+houseOrder.getOrderCommList());
			}
            for (HouseOrder order : orders) {
                List<HouseOrderCommodity> orderCommList = order.getOrderCommList();
                if(orderCommList!=null&&orderCommList.size()>0){
                    for (HouseOrderCommodity orderCommodity : orderCommList) {
                        String temperature = orderCommodity.getAreaCode();
                        if(temperature!=null){
                            Logger.getLogger(this.getClass()).info("**************该订单"+order.getOrderNo()+"商品的"+orderCommodity.getCode()+"温区************"+temperature);
                            // 到温区拿到对应的商品列表对象，没有就创建一个该温区对应的商品列表对象
                            List<HouseOrderCommodity> list = map.get(temperature);
                            //如果该温区不存在
                            if (list == null) {
                                list = new ArrayList<HouseOrderCommodity>();
                                // 添加商品到列表中
                                list.add(orderCommodity);
                                // 添加该温区商品列表
                                map.put(temperature, list);
                            // 如果该温区存在
                            } else {
                                traverseOrderCommoditiesExistCurrentTemp(map,orderCommodity, temperature, list);
                            }
                        }else {
                            Logger.getLogger(this.getClass()).info("******************orderCommodity code "+orderCommodity.getCode() +"areaCode is null***********");
                        }                    
                    }
                }            
            }
        } catch (Exception e) {
            e.printStackTrace();
        }    
        
    }
    //温区商品Map中存在该温区
    private void traverseOrderCommoditiesExistCurrentTemp(
            Map<String, List<HouseOrderCommodity>> map,
            HouseOrderCommodity orderCommodity, String temperature,
            List<HouseOrderCommodity> list) {
        // 该温区是否存在该商品标识
        Boolean hasExistCommodityBean = false;
        // 遍历当前温区商品列表中是否包含了该商品，如果该温区商品列表已经包含了此商品就改变该商品的数量
        for (HouseOrderCommodity orderCommodityInlist : list) {
            String code = orderCommodityInlist.getCode();
            // 如果当前温区的商品列表中包括了该商品就改变该商品在列表中的数量
            if (orderCommodity.getCode().equals(code)) {

                // 设置存在该商品
                hasExistCommodityBean = true;
                int currentCommodityCount = orderCommodityInlist
                        .getCount() + orderCommodity.getCount();
                orderCommodityInlist.setCount(currentCommodityCount);
            }
        }
        // 如果该温区列表不存在改商品就添加到该温区商品列表中
        if (!hasExistCommodityBean) {
            list.add(orderCommodity);
        }
        //更新该温区商品列表
        map.put(temperature,list);
    }

    @Override
    public void getOrdersByCodes(List<String> orderTempCodes, int count,
            List<HouseOrder> orders) {
        Integer orderAmount = sendOrderAmount.getOrderAmount();
        List<String> codeList = new ArrayList<String>(); 
        for (int i = 0; i < orderAmount; i++) {
            try {
                String orderCode = orderTempCodes.get(count*orderAmount + i);
                codeList.add(orderCode);
            } catch (Exception e) {
                // 溢出就结束循环
                break;
            }
        }
        List<HouseOrder> ordersQuery = houseOrderManager.getOrderListByOrderNos(codeList);
        for (HouseOrder houseOrder : ordersQuery) {
        	orders.add(houseOrder);
		}
        
    }

    @Override
    public void getSortOrderDataByOrderNo(
            Map<HouseOrder, Map<String, List<HouseOrderCommodity>>> SortMapByOrder,
            List<HouseOrder> orders) {
        for (HouseOrder order : orders) {
            Map<String, List<HouseOrderCommodity>> orderMap = new HashMap<String, List<HouseOrderCommodity>>();
            List<HouseOrderCommodity> orderCommList = order.getOrderCommList();
            if(orderCommList!=null&&orderCommList.size()>0){
                for (HouseOrderCommodity houseOrderCommodity : orderCommList) {
                    List<HouseOrderCommodity> list = orderMap.get(houseOrderCommodity.getAreaCode());
                    //如果该温区不存在
                    if(list==null){
                        List<HouseOrderCommodity> comList = new ArrayList<HouseOrderCommodity>(); 
                        comList.add(houseOrderCommodity);
                        orderMap.put(houseOrderCommodity.getAreaCode(),comList);
                    //如果该温区存在
                    }else {
                        list.add(houseOrderCommodity);
                        orderMap.put(houseOrderCommodity.getAreaCode(),list);
                    }
                }
            }
            SortMapByOrder.put(order,orderMap);
        }       
    }

    @Override
    public void getOrdersByCodesInMultiThread(List<String> orderTempCodes,
            List<HouseOrder> orders) {
        if(orderTempCodes!=null&&orderTempCodes.size()!=0){
        	List<HouseOrder> ordersQuery=houseOrderManager.queryHouseOrderListByOrderNos(orderTempCodes);
        	for (HouseOrder houseOrder : ordersQuery) {
        		orders.add(houseOrder);
			}
        	
        }
        
    }
    /**
     * 自提点温区对应商品列表
     * @param map
     * @param orders
     */
    @Override
    public void getSiteTempAreaCommMap(Map<String, List<HouseOrderCommodity>> map,List<HouseOrder> orders){
        for (HouseOrder houseOrder : orders) {
            //订单自提点
            String siteCode = houseOrder.getSiteCode();
            List<HouseOrderCommodity> orderCommList = houseOrder.getOrderCommList();
            for (HouseOrderCommodity houseOrderCommodity : orderCommList) {
                //商品对应的温区
                String areaCode = houseOrderCommodity.getAreaCode();
                //生成自提点和温区的唯一标识
                String siteArea = SiteCodeAreaCodeLinkerUtils.siteAreaLink(siteCode, areaCode);
                List<HouseOrderCommodity> list = map.get(siteArea);
                //该标识不存在商品列表
                if(list==null){
                    List<HouseOrderCommodity> comList= new ArrayList<HouseOrderCommodity>();
                    comList.add(houseOrderCommodity);
                    map.put(siteArea, comList);     
                //该标识下存在商品列表
                }else{
                    logger.info("商品list====="+list);
                    // 该温区是否存在该商品标识
                    Boolean hasExistCommodityBean = false;
                    for (HouseOrderCommodity houseOrderCommodity2 : list) {
                        //该列表中有该物品编号
                        if(houseOrderCommodity2.getCode().equals(houseOrderCommodity.getCode())){
                            Integer count = houseOrderCommodity.getCount()+houseOrderCommodity2.getCount();
                            houseOrderCommodity2.setCount(count);
                            hasExistCommodityBean=true;
                        }              
                    }
                    if(!hasExistCommodityBean){
                        list.add(houseOrderCommodity);
                    }
                    map.put(siteArea, list);
                }
            }
        }        
    }
}
