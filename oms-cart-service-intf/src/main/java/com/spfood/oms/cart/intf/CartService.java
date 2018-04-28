package com.spfood.oms.cart.intf;


import java.util.List;

import com.spfood.kernel.exception.BizException;
import com.spfood.oms.cart.intf.domain.Cart;
import com.spfood.oms.cart.intf.domain.CartCommodity;

public interface CartService {
	
	/**
	 * 添加商品到购物车
	 * 商品编码*,商品数量*,商品默认图片*, 客户编码*
	 * @param customerCode,commodity
	 * @return 购物车中商品的总数量
	 */
	public Long addCartCommodity(String customerCode,CartCommodity cartCommodity) throws BizException;
	
	/**
	 * 修改购物车中的商品数量
	 * 客户编码*,商品ID*,商品数量*
	 * @param customerCode,CartCommodity
	 * @return 购物车中商品的总数量
	 */
	public Long updateCommodityQuantity(String customerCode,CartCommodity cartCommodity) throws BizException;
	
	/**
	 * 移除购物车中的商品
	 * 用户编码,商品编码*
	 * @param customerCode
	 * @param comCodes
	 * @return 购物车中商品的总数量
	 */
	public Long removeCommodityByCode(String customerCode,String[] comCodes) throws BizException;
	
	
	/**
	 * 查询购物车中的商品
	 * 客户编码*
	 * @param customerCode
	 * @return 购物车中的商品
	 */
	public List<Cart> getCartCommodityList(String customerCode) throws BizException;
	
	/**
	 * 查询购物车中商品的总数量
	 * 客户编码*
	 * @param customerCode
	 * @return 购物车中商品的总数量
	 */
	public Long getCartCommodityTotal(String customerCode) throws BizException;
	
	
	/**
	 * 合并购物车商品
	 * @param customerCode,CartCommodity
	 * @return 购物车中商品的总数量
	 */
	public Long mergeCartCommodity(String customerCode,List<CartCommodity> cartCommodity) ;
	
}
