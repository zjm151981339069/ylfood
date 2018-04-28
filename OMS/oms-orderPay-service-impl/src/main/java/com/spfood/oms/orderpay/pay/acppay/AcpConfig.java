package com.spfood.oms.orderpay.pay.acppay;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class AcpConfig {
    //默认配置的是UTF-8
    @Value("#{configProperties['unionpay.acp.encoding_utf8']}")
    public  String encodingUtf8;
    @Value("#{configProperties['unionpay.acp.encoding_gbk']}")
    public  String encodingGbk ;
    //全渠道固定值
    @Value("#{configProperties['unionpay.acp.version']}")
    public  String version;   
    //后台服务对应的写法参照 FrontRcvResponse.java
    @Value("#{configProperties['unionpay.acp.frontUrl']}")
    public  String frontUrl;
    //后台服务对应的写法参照 BackRcvResponse.java
    @Value("#{configProperties['unionpay.acp.backUrl']}")
    public  String backUrl;//受理方和发卡方自选填写的域[O]--后台通知地址
    @Value("#{configProperties['unionpay.acp.mer_id']}")
    public  String merId ;
    @Value("#{configProperties['unionpay.acp.error.page']}")
    public  String errorPage ;
    // 商户发送交易时间 格式:YYYYMMDDhhmmss
    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }
    public String getEncodingUtf8() {
        return encodingUtf8;
    }
    public void setEncodingUtf8(String encodingUtf8) {
        this.encodingUtf8 = encodingUtf8;
    }
    public String getEncodingGbk() {
        return encodingGbk;
    }
    public void setEncodingGbk(String encodingGbk) {
        this.encodingGbk = encodingGbk;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getFrontUrl() {
        return frontUrl;
    }
    public void setFrontUrl(String frontUrl) {
        this.frontUrl = frontUrl;
    }
    public String getBackUrl() {
        return backUrl;
    }
    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }
    public String getMerId() {
        return merId;
    }
    public void setMerId(String merId) {
        this.merId = merId;
    }
    public String getErrorPage() {
        return errorPage;
    }
    public void setErrorPage(String errorPage) {
        this.errorPage = errorPage;
    }
  
}
