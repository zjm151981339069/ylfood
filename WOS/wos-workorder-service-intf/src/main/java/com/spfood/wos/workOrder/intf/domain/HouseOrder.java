package com.spfood.wos.workOrder.intf.domain;

import com.spfood.kernel.domain.DomainObject;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Administrator
 *订单详情
 */
public class HouseOrder implements DomainObject  {
	private static final long serialVersionUID = 1L;
	
	//订单id
	private Long orderId;

	//订单编号
	private String orderNo;

    //订单类型
    private Integer type;

    //购货人
    private String customer;

    //购货人编号
    private String customerCode;
    
    //收货人
    private String receiver;
    
    //收货人编号
    private String receiverCode;

    //代付人
    private String repCustomer;
    
    //代付人编号
    private String repCustomerCode;
    
    //电话
    private String phone;
    
    //城市
    private String cityName;
    
    //城市编码
    private String cityCode;

    //地区
    private String zone;

    //详细地址
    private String addr;

    //运费
    private BigDecimal carriage;

    //配送时间
    private Date deliverTime;

    //配送时间更改人
    private String modifier;
    
    //更改人编号
    private String modifierCode;

    //配送时间更改时
    private Date modifyTime;

    //自提点
    private String site;
    
    //自提点编码
    private String siteCode;

   //是否已开发票
    private Integer isBill;
    
    //发票类型
    private Integer billType;

    //发票抬头
    private Integer billTitle;

    //发票内容
    private String billContent;

    //用户留言
    private String userComments;

    //订单总金额
    private BigDecimal orderAmount;
    
    //商品总金额
    private BigDecimal comAmount;

    //折扣金额
    private BigDecimal discount;

    //订单状态
    private Integer status;

    //创建时间
    private Date createTime;
	
    //标记
    private String sign;
	
    //验证码
	private String verification;
	
	//任务时间
    private Date taskTime;
	
    //对应商品表
	private List<HouseOrderCommodity> orderCommList;
	
	public HouseOrder() {
		super();
	}

	public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getType() {
        return type;
    }


    public String getCustomer() {
        return customer;
    }

    public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getReceiverCode() {
		return receiverCode;
	}

	public void setReceiverCode(String receiverCode) {
		this.receiverCode = receiverCode;
	}

	public String getRepCustomer() {
		return repCustomer;
	}

	public void setRepCustomer(String repCustomer) {
		this.repCustomer = repCustomer;
	}

	public String getRepCustomerCode() {
		return repCustomerCode;
	}

	public void setRepCustomerCode(String repCustomerCode) {
		this.repCustomerCode = repCustomerCode;
	}

	public String getModifierCode() {
		return modifierCode;
	}

	public void setModifierCode(String modifierCode) {
		this.modifierCode = modifierCode;
	}

	public void setCustomer(String customer) {
        this.customer = customer == null ? null : customer.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone == null ? null : zone.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public BigDecimal getCarriage() {
        return carriage;
    }

    public void setCarriage(BigDecimal carriage) {
        this.carriage = carriage;
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site == null ? null : site.trim();
    }

    public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode == null ? null : siteCode.trim();;
	}

	public Integer getBillType() {
        return billType;
    }

    public Integer getBillTitle() {
        return billTitle;
    }

    public String getBillContent() {
        return billContent;
    }

    public void setBillContent(String billContent) {
        this.billContent = billContent == null ? null : billContent.trim();
    }

    public String getUserComments() {
        return userComments;
    }

    public void setUserComments(String userComments) {
        this.userComments = userComments == null ? null : userComments.trim();
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getStatus() {
        return status;
    }

    public Date getCreateTime() {
        return createTime;
    }

	public List<HouseOrderCommodity> getOrderCommList() {
		return orderCommList;
	}

	public void setOrderCommList(List<HouseOrderCommodity> orderCommList) {
		this.orderCommList = orderCommList;
	}

	public Integer getIsBill() {
		return isBill;
	}

	public void setIsBill(Integer isBill) {
		this.isBill = isBill;
	}

	public BigDecimal getComAmount() {
		return comAmount;
	}

	public void setComAmount(BigDecimal comAmount) {
		this.comAmount = comAmount;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setDeliverTime(Date deliverTime) {
		this.deliverTime = deliverTime;
	}

	public void setBillType(Integer billType) {
		this.billType = billType;
	}

	public void setBillTitle(Integer billTitle) {
		this.billTitle = billTitle;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}

	public Date getTaskTime() {
		return taskTime;
	}

	public void setTaskTime(Date taskTime) {
		this.taskTime = taskTime;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", type=").append(type);
        sb.append(", customer=").append(customer);
        sb.append(", customerCode=").append(customerCode);
        sb.append(", repCustomer=").append(repCustomer);
        sb.append(", repCustomerCode=").append(repCustomerCode);
        sb.append(", receiver=").append(receiver);
        sb.append(", receiverCode=").append(receiverCode);
        sb.append(", phone=").append(phone);
        sb.append(", cityName=").append(cityName);
        sb.append(", cityCode=").append(cityCode);
        sb.append(", zone=").append(zone);
        sb.append(", addr=").append(addr);
        sb.append(", carriage=").append(carriage);
        sb.append(", deliverTime=").append(deliverTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", site=").append(site);
        sb.append(", siteCode=").append(siteCode);
        sb.append(", isBill=").append(isBill);
        sb.append(", billType=").append(billType);
        sb.append(", billTitle=").append(billTitle);
        sb.append(", billContent=").append(billContent);
        sb.append(", userComments=").append(userComments);
        sb.append(", orderAmount=").append(orderAmount);
        sb.append(", comAmount=").append(comAmount);
        sb.append(", discount=").append(discount);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", sign=").append(sign);
        sb.append(", verification=").append(verification);
        sb.append(", taskTime=").append(taskTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
	}
    
}