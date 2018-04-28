package com.spfood.oms.cart.dao;



import com.spfood.kernel.dao.BaseDao;
import com.spfood.kernel.exception.BizException;
import com.spfood.oms.cart.intf.domain.CartCommodity;
import com.spfood.oms.cart.intf.searchCriteria.CartCommoditySearchCriteria;


public interface CartCommodityDao extends BaseDao<CartCommodity> {
	
	
	
	public void deleteCommodityByCodeInBatch(CartCommoditySearchCriteria cartCommoditySearchCriteria) throws BizException;
	
		
		
}
