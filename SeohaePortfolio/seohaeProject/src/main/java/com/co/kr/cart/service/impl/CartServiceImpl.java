package com.co.kr.cart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.kr.cart.dao.CartDao;
import com.co.kr.cart.service.CartService;
import com.co.kr.cart.vo.CartVo;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartDao cartDao;

    @Override
    public void cartInsert(CartVo cartVo) {
        cartDao.cartInsert(cartVo);
    }
    
    @Override
    public List<CartVo> cartList(String user_id) {
        return cartDao.cartList(user_id);
    }
    
    @Override
    public void cartDelete(int product_no) {
        cartDao.cartDelete(product_no);
    }
    
    @Override
    public void cartUpdate(CartVo cartVo) {
        cartDao.cartUpdate(cartVo);
    }
    
    @Override
    public int countCart(CartVo cartVo) {
        return cartDao.countCart(cartVo);
    }
    
	@Override
	public void UpdateCount(CartVo cartVo) {
		cartDao.UpdateCount(cartVo);
	}
	
	@Override
	public void cartListDelete(List<CartVo> data) {
		for(int i=0; i<data.size(); i++)
			cartDao.cartListDelete(data.get(i));
	}
	
	@Override
	public int sumMoney(String userId) {
		return cartDao.sumMoney(userId);
	}

	@Override
	public CartVo cartBookDetail(CartVo cartVo) {
		return cartDao.cartBookDetail(cartVo);
	}

}
