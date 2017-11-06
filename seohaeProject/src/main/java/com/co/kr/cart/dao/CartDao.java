package com.co.kr.cart.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.co.kr.cart.vo.CartVo;
import com.co.kr.common.dao.AbstractDAO;

@Repository
public class CartDao extends AbstractDAO{
	
	public List<CartVo> cartList(String user_id) {
		return selectList("cartSql.cartList", user_id);
	}
	
	public void cartInsert(CartVo cartVo) {
		insert("cartSql.cartInsert", cartVo);
	}
	
	public void cartDelete(int product_no) {
		delete("cartSql.cartDelete", product_no);
	}

	public void cartUpdate(CartVo cartVo) {
		update("cartSql.cartUpdate", cartVo);
	}

	public int countCart(CartVo cartVo) {
        return (int)selectOne("cartSql.countCart", cartVo);
	}

	public void UpdateCount(CartVo cartVo) {
		update("cartSql.UpdateCount", cartVo);
	}
	
	public void cartListDelete(CartVo cartVo) {
		delete("cartSql.cartListDelete", cartVo);
	}

	public int sumMoney(String userId) {
		return (int)selectOne("cartSql.sumMoney",userId);
	}

	public CartVo cartBookDetail(CartVo cartVo) {
		return (CartVo) selectOne("cartSql.cartBookDetail", cartVo);
	}
}
