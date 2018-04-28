package com.spfood.oms.cart.manager;



import java.util.List;

import com.spfood.kernel.exception.BizException;
import com.spfood.oms.cart.intf.domain.CartCommodity;


public interface CartManager {

	
	public void deleteCommodityByCode(String customerCode,String[] comCodes) throws BizException;
	
	public void updateCommodityAmount(CartCommodity commodity) throws BizException;
	
	public void addCartCommodity(String customerCode,CartCommodity commodity) throws BizException;
	
	public void mergeCartCommodity(String customerCode,List<CartCommodity> commodity) throws BizException;
}
