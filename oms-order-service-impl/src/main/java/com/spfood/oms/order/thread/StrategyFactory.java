package com.spfood.oms.order.thread;

import java.util.HashMap;
import java.util.Map;

import com.spfood.oms.order.utils.OrderStatus;

public class StrategyFactory {
	 
    private static StrategyFactory factory = new StrategyFactory();
    private static Map<Integer,Strategy> strategyMap = new HashMap<>();
    static{
       strategyMap.put(OrderStatus.CANCEL.getValue(), new CancelOrderStrategy());
       strategyMap.put(OrderStatus.UNPAIED.getValue(), new UnpaiedOrderStrategy());
       strategyMap.put(OrderStatus.HASPAIED.getValue(), new HaspaiedOrderStrategy());
    }
    
    public Strategy creator(Integer type){
       return strategyMap.get(type);
    }
    
    public static StrategyFactory getInstance(){
       return factory;
    }
} 