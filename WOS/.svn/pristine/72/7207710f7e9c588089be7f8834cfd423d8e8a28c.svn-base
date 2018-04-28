
package com.spfood.wos.workOrder.intf.domain;

import com.spfood.kernel.domain.DomainObject;

public class ReceiveTaskGoods implements DomainObject {
	//物品编号
    private String code;
    //领料物品ID
    private Long rgId;
    //领料编号
    private String recCode;
    //物品类型
    private String type;
    //物品类型名称
    private String typeName;
    //物品名称
    private String name;
    //物品规格
    private String standard;
    //领取任务数量
    private Integer receiveTotal;
    //计量单位
    private String unit;
    //温区名称 
    private String areaName;
    
    private static final long serialVersionUID = 1L;

    public String getCode() {
        return code;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Long getRgId() {
		return rgId;
	}

	public void setRgId(Long rgId) {
		this.rgId = rgId;
	}

	public String getRecCode() {
        return recCode;
    }

    public void setRecCode(String recCode) {
        this.recCode = recCode == null ? null : recCode.trim();
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

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard == null ? null : standard.trim();
    }

    public Integer getReceiveTotal() {
        return receiveTotal;
    }

    public void setReceiveTotal(Integer receiveTotal) {
        this.receiveTotal = receiveTotal;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", code=").append(code);
        sb.append(", rgId=").append(rgId);
        sb.append(", recCode=").append(recCode);
        sb.append(", type=").append(type);
        sb.append(", typeName=").append(typeName);
        sb.append(", name=").append(name);
        sb.append(", standard=").append(standard);
        sb.append(", receiveTotal=").append(receiveTotal);
        sb.append(", unit=").append(unit);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}