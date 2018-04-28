package com.spfood.wos.workOrder.intf.domain;

import java.util.List;

/**
 * 生成分拣工单所需数据
 * @author Administrator
 *
 */
public class SortWorkOrderData {
    //分拣工单编码
    private String code;
    //订单
    private HouseOrder houseOrder;    
    //温区
    private String temperatureArea;
    //商品列表
    private List<HouseOrderCommodity> commodities ;    
    //领料工单编码
    private String requisitionCode;
    
    public SortWorkOrderData() {
        super();
    }

    public SortWorkOrderData(String code, HouseOrder houseOrder,
            String temperatureArea, List<HouseOrderCommodity> commodities,
            String requisitionCode) {
        super();
        this.code = code;
        this.houseOrder = houseOrder;
        this.temperatureArea = temperatureArea;
        this.commodities = commodities;
        this.requisitionCode = requisitionCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public HouseOrder getHouseOrder() {
        return houseOrder;
    }

    public void setHouseOrder(HouseOrder houseOrder) {
        this.houseOrder = houseOrder;
    }

    public String getTemperatureArea() {
        return temperatureArea;
    }

    public void setTemperatureArea(String temperatureArea) {
        this.temperatureArea = temperatureArea;
    }

    public List<HouseOrderCommodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(List<HouseOrderCommodity> commodities) {
        this.commodities = commodities;
    }

    public String getRequisitionCode() {
        return requisitionCode;
    }

    public void setRequisitionCode(String requisitionCode) {
        this.requisitionCode = requisitionCode;
    }

    
}
