package com.spfood.wos.workOrder.utils;

import java.util.UUID;


/**
 * 
* @author huangcj
* @date 2017年1月6日
* Des:主键生成
*
 */
public class UUIDGenerator { 
    public UUIDGenerator() { 
    } 
    /** 
     * 获得一个UUID 
     * @return String UUID 
     */ 
    public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
    }
    
    public static Long getUUIDByRandom(){
    	Long num = Math.round(Math.random()*90000+1);
    	return num;
    }
    /** 
     * 获得指定数目的UUID 
     * @param number int 需要获得的UUID数量 
     * @return String[] UUID数组 
     */ 
    public static String[] getUUID(int number){ 
        if(number < 1){ 
            return null; 
        } 
        String[] ss = new String[number]; 
        for(int i=0;i<number;i++){ 
            ss[i] = getUUID(); 
        } 
        return ss; 
    } 
    public static void main(String[] args){ 
        long i = getUUIDByRandom();
        System.out.println(i); 
    } 
}   
