package com.co.kr.buy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.kr.bino.vo.BinoVo;
import com.co.kr.buy.dao.BuyDao;
import com.co.kr.buy.service.BuyService;
import com.co.kr.buy.vo.BuyVo;
import com.co.kr.cart.vo.CartVo;


@Service
public class BuyServiceImpl implements BuyService{
	
	@Autowired
	private BuyDao buyDao;

	@Override
	public List<CartVo> getCartList(String[] cartNo) throws Exception{
		
		List<CartVo> list = new ArrayList<CartVo>();
		
		for(int i = 0; i<cartNo.length; i++) {
			list.addAll(buyDao.getCartList(cartNo[i]));
		}
		return list;
	}

	@Override
	public void buyInsert(BuyVo buyVo) {
		buyDao.buyInsert(buyVo);
	}

	@Override
	public int getBuy_no(String userId) {
		return buyDao.getBuy_no(userId);
	}

	@Override
	public void cartList(String[] cartNo, int getBuy_no)  throws Exception{
		List<CartVo> cartVo = null;
		Map<String, Integer> map = new HashMap<>(); 
		for(int i=0; i<cartNo.length; i++) {
			cartVo = buyDao.cartList(cartNo[i]);				// 해당 cart_no 로 정보 cart table의 정보를 가져온다.
			map.put("pdAmount", cartVo.get(0).getPdAmount());
			map.put("pdNo", cartVo.get(0).getPdNo());
			map.put("buyNo", getBuy_no);
			
			buyDao.buyInfo_insert(map);						// 가지고온 cartVo, getBuy_no를 가지고 buyInfo 에 insert 해준다.
			buyDao.delete_product(cartNo[i]);
		}
	}

	@Override
	public List<CartVo> purchaseHistory(String userId) {
		return buyDao.purchaseHistory(userId);
	}

	@Override
	public BuyVo buyDetail(BuyVo buyVo) {
		return buyDao.buyDetail(buyVo);
	}

	@Override
	public void buyUseBino(BinoVo binoVo) {
		buyDao.buyUseBino(binoVo);
	}
	
}
