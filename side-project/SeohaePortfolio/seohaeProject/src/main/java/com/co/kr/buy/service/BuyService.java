package com.co.kr.buy.service;

import java.util.List;

import com.co.kr.bino.vo.BinoVo;
import com.co.kr.buy.vo.BuyVo;
import com.co.kr.cart.vo.CartVo;

public interface BuyService {

	List<CartVo> getCartList(String[] cartNo) throws Exception;

	void buyInsert(BuyVo buyVo);

	int getBuy_no(String userId);

	void cartList(String[] cartNo, int getBuy_no) throws Exception;

	List<CartVo> purchaseHistory(String userId);

	BuyVo buyDetail(BuyVo buyVo);

	void buyUseBino(BinoVo binoVo);
	
}
