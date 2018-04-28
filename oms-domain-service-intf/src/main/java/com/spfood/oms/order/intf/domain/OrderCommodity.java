package com.spfood.oms.order.intf.domain;

import java.math.BigDecimal;

import com.spfood.kernel.domain.DomainObject;

/**
 * @author lizekun
 *
 */
public class OrderCommodity implements DomainObject {
    private static final long serialVersionUID = 1L;
    //订单商品ID
    private Long comId;
    //订单编号
    private String orderNo;
    //商品编号
    private String code;
    //商品名称
    private String name;
    //商品类别
    private String type;
    //商品类别名称
    private String typeName;
    //商品价格
    private BigDecimal price;
    //执行价格
    private BigDecimal actPrice;
    //商品数量
    private Integer count;
    //小计金额
    private BigDecimal subTotal;
    //是否组装
    private Integer isPackage;
    //商品图片
    private String pictureUrl;
    //温区编码
    private String areaCode;
    //温区名称
    private String areaName;


    public Long getComId() {
        return comId;
    }

    public void setComId(Long comId) {
        this.comId = comId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
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

    public String getType() {
		return type;
	}

	public void setType(String type) {
        this.type = type == null ? null : type.trim();
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
	}

	public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getActPrice() {
        return actPrice;
    }

    public void setActPrice(BigDecimal actPrice) {
        this.actPrice = actPrice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public Integer getIsPackage() {
        return isPackage;
    }

    public void setIsPackage(Integer isPackage) {
        this.isPackage = isPackage;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl == null ? null : pictureUrl.trim();
    }

    public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", comId=").append(comId);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", typeName=").append(typeName);
        sb.append(", price=").append(price);
        sb.append(", actPrice=").append(actPrice);
        sb.append(", count=").append(count);
        sb.append(", subTotal=").append(subTotal);
        sb.append(", isPackage=").append(isPackage);
        sb.append(", pictureUrl=").append(pictureUrl);
        sb.append(", areaCode=").append(areaCode);
        sb.append(", areaName=").append(areaName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}