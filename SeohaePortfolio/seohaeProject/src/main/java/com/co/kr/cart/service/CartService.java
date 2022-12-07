package com.co.kr.cart.service;

import java.util.List;

import com.co.kr.cart.vo.CartVo;

public interface CartService {

	List<CartVo> cartList(String user_id);

	void cartDelete(int product_no);
	
	void cartInsert(CartVo cartVo);
	
	void cartUpdate(CartVo cartVo);
	
	int countCart(CartVo cartVo);
	
	void UpdateCount(CartVo cartVo);

	void cartListDelete(List<CartVo> data);

	int sumMoney(String userId);

	CartVo cartBookDetail(CartVo cartVo);

}
