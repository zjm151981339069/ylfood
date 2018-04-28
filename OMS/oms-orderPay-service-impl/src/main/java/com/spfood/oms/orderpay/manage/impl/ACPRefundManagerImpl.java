package com.spfood.oms.orderpay.manage.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spfood.oms.orderpay.intf.domain.ACPPayBackgroundResult;
import com.spfood.oms.orderpay.manage.ACPRefundManager;
import com.spfood.oms.orderpay.pay.acppay.AcpConfig;
import com.spfood.oms.orderpay.pay.acppay.sdk.AcpService;
import com.spfood.oms.orderpay.pay.acppay.sdk.LogUtil;
import com.spfood.oms.orderpay.pay.acppay.sdk.SDKConfig;
@Component
public class ACPRefundManagerImpl implements ACPRefundManager{
    @Autowired
    public AcpConfig acpConfig;
    @Override
    public void orderPayByACPRefund(
            ACPPayBackgroundResult aCPPayBackgroundResult) {
        /*** 银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改 ***/
        // 版本号
        Map<String, String> data = new HashMap<String, String>();
        data.put("version", acpConfig.getVersion());
        // 字符集编码 可以使用UTF-8,GBK两种方式
        data.put("encoding", acpConfig.getEncodingUtf8());
        // 签名方法
        data.put("signMethod", "01");
        // 交易类型 04-退货
        data.put("txnType", "04");

        // 交易子类型 默认00

        data.put("txnSubType", "00");

        // 业务类型

        data.put("bizType", "000301");

        // 渠道类型，07-PC，08-手机

        data.put("channelType", "07");

        /*** 商户接入参数 ***/

        // 商户号码，请改成自己申请的商户号或者open上注册得来的777商户号测试

        data.put("merId", acpConfig.getMerId());

        // 接入类型，商户接入固定填0，不需修改

        data.put("accessType", "0");

        // 商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则，重新产生，不同于原消费

        data.put("orderId", aCPPayBackgroundResult.getOrderId());

        // 订单发送时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效

        data.put("txnTime",aCPPayBackgroundResult.getTxnTime());

        // 交易币种（境内商户一般是156 人民币）

        data.put("currencyCode", "156");

        // ****退货金额，单位分，不要带小数点。退货金额小于等于原消费金额，当小于的时候可以多次退货至退货累计金额等于原消费金额

        data.put("txnAmt", aCPPayBackgroundResult.getTxnAmt());

        // 后台通知地址，后台通知参数详见open.unionpay.com帮助中心 下载 产品接口规范 网关支付产品接口规范 退货交易
        // 商户通知,其他说明同消费交易的后台通知

        data.put("backUrl",acpConfig.getBackUrl());

        /*** 要调通交易以下字段必须修改 ***/

        // ****原消费交易返回的的queryId，可以从消费交易后台通知接口中或者交易状态查询接口中获取

        data.put("origQryId", aCPPayBackgroundResult.getQueryId());

        /** 请求参数设置完毕，以下对请求参数进行签名并发送http post请求，接收同步应答报文-------------> **/

        // 报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。

        Map<String, String> reqData = AcpService.sign(data,acpConfig.getEncodingUtf8());

        // 交易请求url从配置文件读取对应属性文件acp_sdk.properties中的 acpsdk.backTransUrl

        String url = SDKConfig.getConfig().getBackRequestUrl();

        // 这里调用signData之后，调用submitUrl之前不能对submitFromData中的键值对做任何修改，如果修改会导致验签不通过

        Map<String, String> rspData = AcpService.post(reqData, url,acpConfig.getEncodingUtf8());

        // 应答码规范参考open.unionpay.com帮助中心 下载 产品接口规范 《平台接入接口规范-第5部分-附录》

        if (!rspData.isEmpty()) {

            if (AcpService.validate(rspData,acpConfig.getEncodingUtf8())) {

                LogUtil.writeLog("验证签名成功");
                String respCode = rspData.get("respCode");
                if (("00").equals(respCode)) {
                    // 交易已受理(不代表交易已成功），等待接收后台通知更新订单状态,也可以主动发起 查询交易确定交易状态。

                    /**
                     * 
                     * 
                     * 
                     * 继续发起交易查询，获取订单的支付状态，并更新系统的数据库
                     * 
                     * 
                     * 
                     * 
                     * 
                     */
                               
                    
                    // TODO
                } else if (("03").equals(respCode) ||
                ("04").equals(respCode) ||

                ("05").equals(respCode)) {
                    // 后续需发起交易状态查询交易确定交易状态

                    // TODO
                } else {
                    // 其他应答码为失败请排查原因

                    // TODO
                }
            } else {
                LogUtil.writeErrorLog("验证签名失败");
                // TODO 检查验证签名失败的原因
            }

        } else {
            // 未返回正确的http状态
            LogUtil.writeErrorLog("未获取到返回报文或返回http状态码非200");
        }
    }

}
