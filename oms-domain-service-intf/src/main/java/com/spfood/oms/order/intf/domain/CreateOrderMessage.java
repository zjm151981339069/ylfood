package com.spfood.oms.order.intf.domain;

import java.util.List;

import com.spfood.kernel.domain.DomainObject;

/**
 * @author lizekun
 *
 */
public class CreateOrderMessage implements DomainObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String flag;
	private String message;
	private List<String> goodsCodeList; 
	public String getFlag() {
		return flag;
	}
	
	public CreateOrderMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreateOrderMessage(String flag, String message) {
		super();
		this.flag = flag;
		this.message = message;
	}

	public CreateOrderMessage(String flag, List<String> goodsCodeList) {
		super();
		this.flag = flag;
		this.goodsCodeList = goodsCodeList;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getGoodsCodeList() {
		return goodsCodeList;
	}

	public void setGoodsCodeList(List<String> goodsCodeList) {
		this.goodsCodeList = goodsCodeList;
	}

	@Override
	public String toString() {
		return "CreateOrderMessage [flag=" + flag + ", message=" + message
				+ ", goodsCodeList=" + goodsCodeList + "]";
	}
	
}
