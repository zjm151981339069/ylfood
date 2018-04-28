package com.spfood.oms.order.intf.domain;

import java.util.List;

import com.spfood.kernel.domain.DomainObject;

/**
 * 换货商品实体类
 * 
 * @author Administrator
 *
 */
public class OrderExchangeCommodity implements DomainObject{

	private static final long serialVersionUID = 1L;

	private Long exComId;

	private String exCode;

	/**
	 * 商品编号
	 */
	private String code;

	/**
	 * 商品名称
	 */
	private String name;
	
	/**
	 * 商品数量
	 */
	private Integer count;

	/**
	 * 商品描述
	 */
	private String issuedes;
	
	/**
	 * 商品默认原图片
	 */
	private String purl;
	
	/**
	 * 换货商品审核图片
	 */
	private List<ExchangePicture> pictureUrl;

	public Long getExComId() {
		return exComId;
	}

	public void setExComId(Long exComId) {
		this.exComId = exComId;
	}

	public String getExCode() {
		return exCode;
	}

	public void setExCode(String exCode) {
		this.exCode = exCode == null ? null : exCode.trim();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getIssuedes() {
		return issuedes;
	}

	public void setIssuedes(String issuedes) {
		this.issuedes = issuedes == null ? null : issuedes.trim();
	}

	@Override
	public String toString() {
		 StringBuilder sb = new StringBuilder();
	        sb.append(getClass().getSimpleName());
	        sb.append(" [");
	        sb.append("Hash = ").append(hashCode());
	        sb.append(", exComId=").append(exComId);
	        sb.append(", exCode=").append(exCode);
	        sb.append(", code=").append(code);
	        sb.append(", name=").append(name);
	        sb.append(", count=").append(count);
	        sb.append(", issuedes=").append(issuedes);
	        sb.append(", purl=").append(purl);
	        sb.append(", serialVersionUID=").append(serialVersionUID);
	        sb.append("]");
	        return sb.toString();
	}

	public OrderExchangeCommodity() {
		super();
	}

	

	public List<ExchangePicture> getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(List<ExchangePicture> pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	public String getPurl() {
		return purl;
	}

	public void setPurl(String purl) {
		this.purl = purl;
	}

}
