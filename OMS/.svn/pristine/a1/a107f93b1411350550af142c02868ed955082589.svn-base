package com.spfood.oms.order.intf.domain;
/**
 * 换货商品对应的审核照片实体类
 */

import com.spfood.kernel.domain.DomainObject;

public class ExchangePicture implements DomainObject{

	private static final long serialVersionUID = 1L;
	
	//审核照片ID
	private Long exPid;

	//对应换货单编码
	private String exCode;
	
	//商品编码
	private String code;
	
	//图片地址
	private String url;

	public ExchangePicture() {
		super();
	}

	public Long getExPid() {
		return exPid;
	}

	public void setExPid(Long exPid) {
		this.exPid = exPid;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		 StringBuilder sb = new StringBuilder();
	        sb.append(getClass().getSimpleName());
	        sb.append(" [");
	        sb.append("Hash = ").append(hashCode());
	        sb.append(", exPid=").append(exPid);
	        sb.append(", exCode=").append(exCode);
	        sb.append(", code=").append(code);
	        sb.append(", url=").append(url);
	        sb.append(", serialVersionUID=").append(serialVersionUID);
	        sb.append("]");
	        return sb.toString();
	}

	public String getExCode() {
		return exCode;
	}

	public void setExCode(String exCode) {
		this.exCode = exCode;
	}
	
	
	
}
