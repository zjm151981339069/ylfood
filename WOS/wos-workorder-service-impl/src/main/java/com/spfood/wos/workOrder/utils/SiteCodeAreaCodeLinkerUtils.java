package com.spfood.wos.workOrder.utils;
/**
 * 温区编码自提点编码连接字符串工具类
 * @author Administrator
 *
 */
public class SiteCodeAreaCodeLinkerUtils {
    public static final String LINKER="<siteArea>";
    /**
     * 温区自提点连接
     * @param siteCode 自提点编码
     * @param areaCode 温区编码
     * @return 自提点温区连接编码
     */
    public static String siteAreaLink(String siteCode,String areaCode){
        return siteCode+LINKER+areaCode;
    }
    /**
     * 获取自提点
     * @param siteAreaLink  自提点温区
     * @return 自提点
     */
    public static String getSiteCode(String siteAreaLink){
        String[] split = siteAreaLink.split(LINKER);
        if(split!=null&&split.length>0){
            return split[0];
        }else {
            return null;
        }
    }
    /**
     * 获取温区
     * @param siteAreaLink 自提点温区
     * @return 温区
     */
    public static String getAreaCode(String siteAreaLink){
        String[] split = siteAreaLink.split(LINKER);
        if(split!=null&&split.length>1){
            return split[1];
        }else {
            return null;
        }
    }
}
