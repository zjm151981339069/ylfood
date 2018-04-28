package com.spfood.oms.cart.dao.impl;



import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.spfood.kernel.dao.impl.BaseDaoImpl;
import com.spfood.kernel.exception.BizException;
import com.spfood.oms.cart.dao.CartCommodityDao;
import com.spfood.oms.cart.intf.domain.CartCommodity;
import com.spfood.oms.cart.intf.searchCriteria.CartCommoditySearchCriteria;
import com.spfood.oms.cart.utils.MybatisList;


@Repository
public class CartCommodityDaoImpl extends BaseDaoImpl<CartCommodity> implements CartCommodityDao {

	private final static Logger logger = Logger.getLogger(CartCommodityDaoImpl.class); 

	@Override
	public void deleteCommodityByCodeInBatch(CartCommoditySearchCriteria cartCommoditySearchCriteria) throws BizException{
		try {
			sqlSessionTemplate.delete(MybatisList.SQL_DELETE_BY_CODE_IN_BATCH, cartCommoditySearchCriteria);
		} catch (Exception e) {
			logger.error("oms.cart.CommodityDaoImpl.deleteCommodityByCodeInBatch is error", e);
			throw new BizException("oms.cart.CommodityDaoImpl.deleteCommodityByCodeInBatch", e,cartCommoditySearchCriteria);
		}
		
		
	}




}
