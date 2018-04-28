package com.spfood.oms.cart.manager.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.spfood.kernel.exception.BizException;
import com.spfood.oms.cart.dao.CartDao;
import com.spfood.oms.cart.manager.CartQueryManager;
import com.spfood.oms.cart.intf.domain.Cart;


@Service
public class CartQueryManagerImpl implements CartQueryManager{
	
	@Autowired
	private CartDao cartDao;

	private static final Logger logger = Logger.getLogger(CartQueryManagerImpl.class);

	@Override
	public Long getCartCommodityTotal(String customerCode) throws BizException{
		if(StringUtils.isEmpty(customerCode)){
			logger.error("oms.cart.manager.getCartCommodityTotal is error,customerCode is null");
			throw new BizException("oms.cart.manager.getCartCommodityList", "customerCode is null");
		}
		Cart cartCommodity = new Cart();
		cartCommodity.setCustomerCode(customerCode);
		Long total = cartDao.selectCount(cartCommodity);
		return total;
	}

	@Override
	public List<Cart> getCartCommodityList(String customerCode) throws BizException{
		if(StringUtils.isEmpty(customerCode)){
			logger.error("oms.cart.manager.getCartCommodityList is error ,customerCode is null");
			throw new BizException("oms.cart.manager.getCartCommodityList", "customerCode is null");
		}
		Cart cartCommodity = new Cart();
		cartCommodity.setCustomerCode(customerCode);
		List<Cart> cartCommList = cartDao.selectList(cartCommodity);
		
		return cartCommList;
	}
	
	

	
	
}
