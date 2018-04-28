package com.spfood.oms.cart.manager;


import java.util.List;

import com.spfood.kernel.exception.BizException;
import com.spfood.oms.cart.intf.domain.Cart;

public interface CartQueryManager {
	
	
	
	public Long getCartCommodityTotal(String customerCode) throws BizException;
	
	public List<Cart> getCartCommodityList(String customerCode) throws BizException;
	
}
