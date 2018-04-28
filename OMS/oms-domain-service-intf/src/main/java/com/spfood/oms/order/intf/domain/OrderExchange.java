package com.spfood.oms.order.intf.domain;

import com.spfood.kernel.domain.DomainObject;

import java.util.Date;
import java.util.List;
/**
 * 换货单实体类
 * @author Administrator
 *
 */

public class OrderExchange implements DomainObject {
	
	private static final long serialVersionUID = 1L;

	//换货单ID
	private Long exId;
	
	//换货单编码
	private String exCode;

	//换货单对应原订单编码
    private String orderNo;

    //换货单申请时间
    private Date appTime;

    //下单时间
    private Date createTime;

    //收货人
    private String receiver;
    
   //收货人编号
  	private String receiverCode;

    //电话
    private String phone;
    
    //用户名
    private String username;

    //用户名编号
  	private String usernameCode;
  	
    //自提点
    private String site;
    
    //自提点名称
    private String siteCode;

    //商品处理方式
    private Integer dealStyle;

    //地址
    private String addr;

    //审核状态
    private Integer auditstatus;
    
    //审核意见
    private String auditview;
    
   //对应商品、
  	private List<OrderExchangeCommodity> exchangeCommodityLists;
    
    public OrderExchange() {
  		super();
  	}
  	
    public OrderExchange(String exCode) {
 		super();
 		this.exCode = exCode;
 	}

    public String getExCode() {
        return exCode;
    }

    public void setExCode(String exCode) {
        this.exCode = exCode == null ? null : exCode.trim();
    }

    public Long getExId() {
        return exId;
    }

    public void setExId(Long exId) {
        this.exId = exId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Date getAppTime() {
        return appTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site == null ? null : site.trim();
    }

    public Integer getDealStyle() {
        return dealStyle;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public Integer getAuditstatus() {
        return auditstatus;
    }


    public String getAuditview() {
        return auditview;
    }

    
    public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public void setAuditview(String auditview) {
        this.auditview = auditview;
    }

	public String getReceiverCode() {
		return receiverCode;
	}

	public void setReceiverCode(String receiverCode) {
		this.receiverCode = receiverCode;
	}

	public String getUsernameCode() {
		return usernameCode;
	}

	public void setUsernameCode(String usernameCode) {
		this.usernameCode = usernameCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}
	
	private OrderExchangeCommodity orderExchangeCommodity;

	public List<OrderExchangeCommodity> getExchangeCommodityLists() {
		return exchangeCommodityLists;
	}

	public void setExchangeCommodityLists(
			List<OrderExchangeCommodity> exchangeCommodityLists) {
		this.exchangeCommodityLists = exchangeCommodityLists;
	}

	public OrderExchangeCommodity getOrderExchangeCommodity() {
		return orderExchangeCommodity;
	}

	public void setOrderExchangeCommodity(
			OrderExchangeCommodity orderExchangeCommodity) {
		this.orderExchangeCommodity = orderExchangeCommodity;
	}

	public void setAppTime(Date appTime) {
		this.appTime = appTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setDealStyle(Integer dealStyle) {
		this.dealStyle = dealStyle;
	}

	public void setAuditstatus(Integer auditstatus) {
		this.auditstatus = auditstatus;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", exId=").append(exId);
        sb.append(", exCode=").append(exCode);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", appTime=").append(appTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", receiver=").append(receiver);
        sb.append(", receiverCode=").append(receiverCode);
        sb.append(", phone=").append(phone);
        sb.append(", username=").append(username);
        sb.append(", usernameCode=").append(usernameCode);
        sb.append(", site=").append(site);
        sb.append(", siteCode=").append(siteCode);
        sb.append(", dealStyle=").append(dealStyle);
        sb.append(", addr=").append(addr);
        sb.append(", auditstatus=").append(auditstatus);
        sb.append(", auditview=").append(auditview);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
	}

	
	
}