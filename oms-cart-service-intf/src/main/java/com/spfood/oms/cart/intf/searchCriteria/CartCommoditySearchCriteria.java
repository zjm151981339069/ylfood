package com.spfood.oms.cart.intf.searchCriteria;

import com.spfood.oms.cart.intf.domain.CartCommodity;

public class CartCommoditySearchCriteria extends CartCommodity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1986803653845129114L;
	
	private String customerCode;
	
	public String[] comCodes;

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String[] getComCodes() {
		return comCodes;
	}

	public void setComCodes(String[] comCodes) {
		this.comCodes = comCodes;
	}
	
	
}
