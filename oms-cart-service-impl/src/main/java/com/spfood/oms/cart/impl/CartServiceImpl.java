package com.spfood.oms.cart.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spfood.kernel.exception.BizException;
import com.spfood.oms.cart.manager.CartManager;
import com.spfood.oms.cart.manager.CartQueryManager;
import com.spfood.oms.cart.intf.CartService;
import com.spfood.oms.cart.intf.domain.Cart;
import com.spfood.oms.cart.intf.domain.CartCommodity;


/**
 * 
* @author huangcj
* @date 2017年1月5日
* Des:购物车管理实现类
*
 */
public class CartServiceImpl implements CartService{
	
	
	@Autowired
	private CartManager cartManager;
	
	@Autowired
	private CartQueryManager cartQueryManager;
	
	

	@Override
	public Long addCartCommodity(String customerCode,CartCommodity commodity) throws BizException{
		cartManager.addCartCommodity(customerCode,commodity);
		return cartQueryManager.getCartCommodityTotal(customerCode);
	}

	@Override
	public Long updateCommodityQuantity(String customerCode,CartCommodity commodity) throws BizException{
		cartManager.updateCommodityAmount(commodity);
		return cartQueryManager.getCartCommodityTotal(customerCode);
	}

	@Override
	public Long removeCommodityByCode(String customerCode,String[] comCodes) throws BizException{
		cartManager.deleteCommodityByCode(customerCode,comCodes);
		return cartQueryManager.getCartCommodityTotal(customerCode);
	}
	
	@Override
	public List<Cart> getCartCommodityList(String customerCode) throws BizException{
		return cartQueryManager.getCartCommodityList(customerCode);
	}

	@Override
	public Long getCartCommodityTotal(String customerCode) throws BizException{
		return cartQueryManager.getCartCommodityTotal(customerCode);
	}

	@Override
	public Long mergeCartCommodity(String customerCode,List<CartCommodity> commodity) throws BizException{
		cartManager.mergeCartCommodity(customerCode,commodity);
		return cartQueryManager.getCartCommodityTotal(customerCode);
	}

	 
	
	 
}
