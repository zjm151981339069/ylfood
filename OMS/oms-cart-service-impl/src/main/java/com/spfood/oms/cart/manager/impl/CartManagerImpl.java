package com.spfood.oms.cart.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spfood.kernel.exception.BizException;
import com.spfood.oms.cart.dao.CartDao;
import com.spfood.oms.cart.dao.CartCommodityDao;
import com.spfood.oms.cart.manager.CartManager;
import com.spfood.oms.cart.utils.Constant;
import com.spfood.oms.cart.utils.UUIDGenerator;
import com.spfood.oms.cart.intf.domain.CartCommodity;
import com.spfood.oms.cart.intf.domain.Cart;
import com.spfood.oms.cart.intf.searchCriteria.CartCommoditySearchCriteria;
import com.spfood.wms.goods.domain.vo.GoodsQuantityVo;
import com.spfood.wms.goods.intf.GoodsQuantityIntf;

@Service
public class CartManagerImpl implements CartManager {

	@Autowired
	public CartDao cartDao;

	@Autowired
	public CartCommodityDao cartCommodityDao;
	
	@Autowired
	private GoodsQuantityIntf goodsQuantityIntf;

	private static final Logger logger = Logger.getLogger(CartManagerImpl.class);
	

	/**
	 * 客户已登陆,删除购物车中的商品 
	 * 商品ID
	 * 
	 */
	@Override
	public void deleteCommodityByCode(String customerCode,String[] comCodes) throws BizException{
		CartCommoditySearchCriteria ccSearchCriteria = new CartCommoditySearchCriteria();
		ccSearchCriteria.setCustomerCode(customerCode);
		ccSearchCriteria.setComCodes(comCodes);
		cartCommodityDao.deleteCommodityByCodeInBatch(ccSearchCriteria);
	}

	/**
	 * 客户已登陆,修改购物车中的商品的数量 
	 * 购物车编码，商品编码,商品数量 
	 */
	@Override
	public void updateCommodityAmount(CartCommodity cartCommodity) throws BizException{

		// 商品编码、商品数量为空
		String comCode = cartCommodity.getComCode();
		if (cartCommodity == null || comCode == null || cartCommodity.getAmount() == null) {
			logger.error("oms.cart.manager.updateCommodityAmount is error,cartCommodity is null");
			throw new BizException("oms.cart.manager.updateCommodityAmount", "cartCommodity is null");
		}
		
		//检查库存
		this.setCommodityQuantity(cartCommodity);
		cartCommodity.setLastTime(new Date());
		cartCommodityDao.updateById(cartCommodity);
	}


	// 添加购物车和商品
	private void saveCartCommodityList(Cart cartCommodity) throws BizException{
		cartCommodity.setCartCode(UUIDGenerator.getUUID());
		cartCommodity.setLastTime(new Date());
		List<CartCommodity> commodityList = cartCommodity.getCommodityList();
		if(commodityList.size() > Constant.TOTAL){
			logger.error("oms.cart.manager.saveCartCommodityList is error,commodityList.size > "+Constant.TOTAL);
			throw new BizException("oms.cart.manager.saveCartCommodity","commodityList.size > "+Constant.TOTAL);
		}
		for (CartCommodity commodity : commodityList) {
			commodity.setCartCode(cartCommodity.getCartCode());
			commodity.setAddTime(new Date());
			commodity.setLastTime(new Date());
		}
		cartDao.insert(cartCommodity);
	}
	
	

	/**
	 * 批量修改商品数量
	 * @param cartCommodity
	 * @param commodityList
	 */
	private void updateCommodityList(Cart cartCommodity,List<CartCommodity> commodityList){
		// 需要保存的商品（新增商品、重复商品）
		List<CartCommodity> addCommList = new ArrayList<CartCommodity>();
		// 需要删除的商品（重复商品）
		List<CartCommodity> delCommList = new ArrayList<CartCommodity>();
		String cartCode = cartCommodity.getCartCode();
		List<CartCommodity> oldCommList = cartCommodity.getCommodityList();
		Map<String, CartCommodity> ccMap = new HashMap<String, CartCommodity>();
		for (CartCommodity commodity : commodityList) {
			commodity.setCartCode(cartCode);
			// 给新加商品赋值
			commodity.setAddTime(new Date());
			commodity.setLastTime(new Date());
			ccMap.put(commodity.getComCode(), commodity);
		}
		for (CartCommodity oldCommodity : oldCommList) {
			if (ccMap.containsKey(oldCommodity.getComCode())) {
				CartCommodity newCommodity = ccMap.get(oldCommodity.getComCode());
				int newAmount = newCommodity.getAmount() + oldCommodity.getAmount();
				newCommodity.setAmount(newAmount);
				delCommList.add(oldCommodity);
			}
		}

		Iterator<Entry<String, CartCommodity>> entryIter = ccMap.entrySet().iterator();
		while (entryIter.hasNext()) {
			Entry<String, CartCommodity> entry = entryIter.next();
			addCommList.add(entry.getValue());
		}
		
		//查库存
		this.setCommodityQuantityList(addCommList);
		
		this.saveCommodityList(addCommList);
		if (delCommList.size() > 0) {
			
			this.deleteCommodityListByIds(delCommList);
		}
	}
	
	/**
	 * 批量删除商品
	 * @param delCartCommList
	 */
	private void deleteCommodityListByIds(List<CartCommodity> delCartCommList){
		List<Long> ccidList = new ArrayList<Long>();
		for (int i = 0; i < delCartCommList.size(); i++) {
			ccidList.add(delCartCommList.get(i).getCcId());
		}
		cartCommodityDao.deleteByIdInBatch(ccidList);
	}
	
	/**
	 * 保存商品信息
	 * @param commodity
	 */
	private void saveCommodity(CartCommodity commodity) {
		commodity.setAddTime(new Date());
		commodity.setLastTime(new Date());
		cartCommodityDao.insert(commodity);
	}
	
	/**
	 * 批量保存商品信息
	 * @param commodity
	 */
	private void saveCommodityList(List<CartCommodity> commodity){
		if(commodity == null || commodity.size() == 0){
			logger.error("oms.cart.manager.saveCommodityList is error,commodity is null");
			throw new BizException("oms.manager.saveCommodityList", "commodity is null");
		}
		
		cartCommodityDao.insertInBatch(commodity);
	}

	
	
	/**
	 * 添加购物车商品
	 * @param customerCode
	 * @param newCommodity
	 */
	@Override
	@Transactional
	public void addCartCommodity(String customerCode,CartCommodity newCommodity) throws BizException{
		// 查询已有的商品
		Cart cart = new Cart();
		cart.setCustomerCode(customerCode);
		Cart oldCart = cartDao.selectOne(cart);
		if (oldCart == null ) {
			//无购物车、商品信息，添加购物车、商品
			List<CartCommodity> commodityList = new ArrayList<CartCommodity>();
			commodityList.add(newCommodity);
			//检查库存
			this.setCommodityQuantity(newCommodity);
			cart.setCommodityList(commodityList);
			this.saveCartCommodityList(cart);
		}else  if (oldCart != null && oldCart.getCommodityList().size() > 0) {
			List<CartCommodity> oldCommList = oldCart.getCommodityList();
			if (newCommodity == null || oldCommList.size() == Constant.TOTAL) {
				logger.error("oms.cart.manager.addCartCommodity is error,oldCommList.size = "+Constant.TOTAL);
				throw new BizException("oms.cart.manager.addCartCommodity","oldCommList.size() = "+Constant.TOTAL);
			}
			// 已存在商品信息,更新数量
			CartCommodity commodity = this.getRepeatCommodity(oldCommList, newCommodity);
			
			if(commodity.getCcId() != null){
				this.updateCommodityAmount(commodity);
			}else {
				//检查库存
				this.setCommodityQuantity(commodity);
				this.saveCommodity(commodity);
			}
			
			
		}else if(oldCart != null && oldCart.getCommodityList().size() == 0){
			// 有购物车无商品信息,添加商品
			String cartCode = oldCart.getCartCode();
			newCommodity.setCartCode(cartCode);
			//检查库存
			this.setCommodityQuantity(newCommodity);
			newCommodity.setAddTime(new Date());
			newCommodity.setLastTime(new Date());
			this.saveCommodity(newCommodity);
		}
	}
	
	/**
	 * 获得重复的商品
	 * @param oldCommodity
	 * @param newCommodity
	 * @return CartCommodity
	 */
	private CartCommodity getRepeatCommodity(List<CartCommodity> oldCommodity,CartCommodity newCommodity){
		
		for (CartCommodity commodity : oldCommodity) {
			if(commodity.getComCode().equals(newCommodity.getComCode())){
				Integer amount = newCommodity.getAmount() + commodity.getAmount();
				commodity.setAmount(amount);
				return commodity;
			}
			newCommodity.setCartCode(commodity.getCartCode());
		}
		return newCommodity;
	}

	/**
	 * 合并商品
	 * @param customerCode
	 * @param commodityList
	 */
	@Override
	public void mergeCartCommodity(String customerCode,List<CartCommodity> commodityList) throws BizException{
		// 查询已有的商品
		Cart cart = new Cart();
		cart.setCustomerCode(customerCode);
		Cart oldCart = cartDao.selectOne(cart);
		if (oldCart == null ) {
			//无购物车、商品信息，添加购物车、商品
			//查库存
			this.setCommodityQuantityList(commodityList);
			cart.setCommodityList(commodityList);
			this.saveCartCommodityList(cart);
			 //有购物车、商品信息，更新商品数量
		}else if (oldCart != null && oldCart.getCommodityList().size() > 0) {
			List<CartCommodity> oldCommList = oldCart.getCommodityList();
			if (commodityList.size() == 0 || commodityList.size() + oldCommList.size() > Constant.TOTAL) {
				logger.error("oms.cart.manager.mergeCartCommodity is error,commodityList.size + oldCommList.size > "+Constant.TOTAL);
				throw new BizException("oms.cart.manager.addCartCommodity","commodityList.size + oldCommList.size > "+Constant.TOTAL);
			}
			this.updateCommodityList(oldCart, commodityList);
		//有购物车、无商品信息，保存商品信息	
		}else if(oldCart != null && oldCart.getCommodityList().size() == 0){
			String cartCode = oldCart.getCartCode();
			for (CartCommodity commodity : commodityList) {
				commodity.setCartCode(cartCode);
				commodity.setAddTime(new Date());
				commodity.setLastTime(new Date());
			}
			//查库存
			this.setCommodityQuantityList(commodityList);
			this.saveCommodityList(commodityList);
			
		}
		
	}
	
	/**
	 * 当添加到购物车的商品的数量大于库存数量时，取库存数量
	 * 单个商品
	 * @param cartCommodity
	 */
	private void setCommodityQuantity(CartCommodity cartCommodity){
		//查库存
		GoodsQuantityVo goodsQuantityVo = goodsQuantityIntf.getGoodsCanSaleNum(cartCommodity.getComCode());
		
		if(goodsQuantityVo ==null){
			//库存中不存在该商品,数量设置为1
			cartCommodity.setAmount(Constant.MIN);
			return;
		}
		
		if(cartCommodity.getAmount() > goodsQuantityVo.getGoodsSum()){
			if(goodsQuantityVo.getGoodsSum() == 0){
				cartCommodity.setAmount(Constant.MIN);
			}else {
				//商品数量单位为份  直接转int
				cartCommodity.setAmount((int)goodsQuantityVo.getGoodsSum());
			}
			
		}
		//单个商品的购买数量上限为200
		if(cartCommodity.getAmount() > Constant.AMOUNT){
			cartCommodity.setAmount(Constant.AMOUNT);
		}
	}
	
	/**
	 * 当添加到购物车的商品的数量大于库存数量时，取库存数量
	 * 多个商品
	 * @param commodityList
	 */
	private void setCommodityQuantityList(List<CartCommodity> commodityList){
		List<String> comCodes = new ArrayList<String>();
		Map<String, Integer> goodsMap = new HashMap<String, Integer>();
		for (CartCommodity commodity : commodityList) {
			comCodes.add(commodity.getComCode());
		}
		
		List<GoodsQuantityVo> goodsList = goodsQuantityIntf.getGoodsCanSaleNum(comCodes);
		
		
		for (GoodsQuantityVo goodsQuantityVo : goodsList) {
			goodsMap.put(goodsQuantityVo.getGoodsCode(), (int)goodsQuantityVo.getGoodsSum());
		}
		
		for (CartCommodity cartCommodity : commodityList) {
			if(goodsMap.containsKey(cartCommodity.getComCode())){
				int goodsSum = goodsMap.get(cartCommodity.getComCode());
				if(cartCommodity.getAmount() > goodsSum){
					if(goodsSum == 0){
						cartCommodity.setAmount(Constant.MIN);
					}else {
						//商品数量单位为份  直接转int
						cartCommodity.setAmount(goodsSum);
					}
					
				}
				//单个商品的购买数量上限为200
				if(cartCommodity.getAmount() > Constant.AMOUNT){
					cartCommodity.setAmount(Constant.AMOUNT);
				}
			}else {
				cartCommodity.setAmount(Constant.MIN);
			}
			
		}
		
		
	}
	
}
