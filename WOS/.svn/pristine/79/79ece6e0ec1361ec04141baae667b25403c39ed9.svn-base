package com.spfood.wos.workOrder.utils;

import java.util.Random;

/**
 * 随机字符串工具类
 * 
 * @author Administrator
 *
 */
public class RandomCodeUitls {
    /**
     * 
     * 
     * @param codeCount 字符串位数长度
     * @param haslitters是否包含字母
     * @return 字符串
     */
    public static String getVerificationCode(Integer codeCount,
            Boolean haslitters) {
        Integer length;
        Boolean includeLitter;
        // 如果验证码数量没有或数量错误指定使用6位数验证码
        if (codeCount == null || codeCount <= 0) {
            length = 6;
        } else {
            length = codeCount;
        }
        // 如果使用字母参数为null，默认使用包含字母
        if (haslitters == null) {
            includeLitter = true;
        } else {
            includeLitter = haslitters;
        }

        StringBuilder verificationCode = new StringBuilder();
        Random random = new Random();
        // 包含字母和数字
        if (includeLitter) {
            for (int i = 0; i < length; i++) {
                String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
                // 输出字母
                if ("char".equalsIgnoreCase(charOrNum)) {
                    // 输出大写字母
                    int temp = 65;
                    char c = (char) (random.nextInt(26) + temp);
                    verificationCode.append(c);
                // 输出数字
                } else if ("num".equalsIgnoreCase(charOrNum)) {
                    verificationCode.append(random.nextInt(10));
                }
            }
        // 只包括数字
        } else {
            for (int i = 0; i < length; i++) {
                verificationCode.append(random.nextInt(10));
            }
        }
        return verificationCode.toString();
    }
}
