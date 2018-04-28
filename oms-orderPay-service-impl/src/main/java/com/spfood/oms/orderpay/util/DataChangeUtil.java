package com.spfood.oms.orderpay.util;

import java.math.BigDecimal;

/**
 * 数据转换工具类
 * @author Administrator
 *
 */
public class DataChangeUtil {
    /**
     * 将BigDecimal金额转换为 分为单位的金额字符串
     * @param bigDecimal
     * @return
     */
    public static String bigDecimalMoneyToPennyMoney(BigDecimal bigDecimal){
        if(bigDecimal!=null){
            Float floatValue = bigDecimal.floatValue();
            // 金额转化为分为单位
            float sessionmoney = Float.parseFloat(floatValue.toString());
            String finalmoney = String.format("%.2f", sessionmoney);
            finalmoney = finalmoney.replace(".", "");
            byte[] bytes = finalmoney.getBytes();
            int index=0;
            for (int i = 0; i < bytes.length; i++) {
                if('0'!=bytes[i]){
                    index=i;
                    break;
                }
            }
            String strLast=finalmoney.substring(index, finalmoney.length());
            return strLast;  
        }
        return null;
            
    }
    /**
     * 以分为单位的金额转换为BigDecimal
     * @param money
     * @return BigDecimal金额
     */
    public static BigDecimal pennyMoneyStringToBigDecimal(String moe){
        if(moe!=null&&!"".equals(moe)){
            String money=moe;
            if(money.length()==1){
                money="00"+money;
            }
            if(money.length()==2){
                money="0"+money;
            }
            String endString = money.substring(money.length()-2);
            String headString = money.substring(0, money.length()-2);
            endString="."+endString;
            headString+=endString;
            return (new BigDecimal(headString));
        }
        return null;
       
    }
    
    public static Float bigDecimalToTwoPonintFloat(BigDecimal moe){
        if(moe!=null){
            String string = moe.toString();
            int index=string.length();
            byte[] bytes = string.getBytes();
            for (int i = 0; i < bytes.length; i++) {
                if('.'==bytes[i]){
                    index=i;
                    break;
                }
            }
            String finalSring="0";
            if(string.length()>=index+3){
                finalSring = string.substring(0, index+3);
            }else{
                finalSring=string;
            }
            return new Float(finalSring);
        }
        return null;
      
    }
    public static void main(String[] args) {
        Float bigDecimalToTwoPonintFloat = bigDecimalToTwoPonintFloat(new BigDecimal("458454545.122222"));
        System.out.println(bigDecimalToTwoPonintFloat);
    }

}
