package com.spfood.oms.order.utils;

/**
 * 验证码工具类
 * 
 * @author Administrator
 *
 */
public class VerificationCodeUitls {
    /**
     * 创建验证码
     * @param codeCount 验证码位数长度
     * @param haslitters是否包含字母
     * @return 验证码
     */
    public static String getVerificationCode(int codeCount,boolean haslitters) {
    	String litters = "0123456789";
    	if (haslitters) {
    		litters = litters+"QWERTYUIOPLKJHGFDSAZXCVBNM";
		}
    	StringBuffer verificationCode = new StringBuffer();
    	for (int i = 0; i < codeCount; i++) {
    		char charAt = litters.charAt((int)(Math.random()*litters.length()));
    		verificationCode.append(charAt);
		}
    	return verificationCode.toString();
    }
}
