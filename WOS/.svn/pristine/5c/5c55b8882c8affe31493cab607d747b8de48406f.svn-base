package com.spfood.wos.workOrder.intf.domain;

import com.spfood.kernel.domain.DomainObject;

/**
 * @author lizekun
 *
 */
public class OutTaskGoods implements DomainObject {
	//物品编码
    private String code;
    //出库物品id
    private Long ogId;
    //出库编码
    private String outCode;
    //物品类别
    private String type;
    //物品类别名称
    private String typeName;
    //物品名称
    private String name;
    //物品规格
    private String standard;
    //计量单位
    private String unit;
    //出库任务数量
    private Integer outTotal;
    
    private static final long serialVersionUID = 1L;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Long getOgId() {
		return ogId;
	}

	public void setOgId(Long ogId) {
		this.ogId = ogId;
	}

	public String getOutCode() {
        return outCode;
    }

    public void setOutCode(String outCode) {
        this.outCode = outCode == null ? null : outCode.trim();
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

	public Integer getOutTotal() {
        return outTotal;
    }

    public void setOutTotal(Integer outTotal) {
        this.outTotal = outTotal;
    }

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", code=").append(code);
        sb.append(", ogId=").append(ogId);
        sb.append(", outCode=").append(outCode);
        sb.append(", type=").append(type);
        sb.append(", name=").append(name);
        sb.append(", standard=").append(standard);
        sb.append(", unit=").append(unit);
        sb.append(", outTotal=").append(outTotal);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}