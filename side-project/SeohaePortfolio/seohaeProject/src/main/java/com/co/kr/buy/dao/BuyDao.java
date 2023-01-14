package com.co.kr.buy.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.co.kr.bino.vo.BinoVo;
import com.co.kr.buy.vo.BuyVo;
import com.co.kr.cart.vo.CartVo;
import com.co.kr.common.dao.AbstractDAO;

@Repository
public class BuyDao extends AbstractDAO{

	public List<CartVo> getCartList(String cart_no) throws Exception {
		return selectList("buySql.getCartList", cart_no);
	}

	public void buyInsert(BuyVo buyVo) {
		insert("buySql.buyInsert", buyVo);
	}

	public int getBuy_no(String userId) {
		return (int) selectOne("buySql.getBuy_no", userId);
	}

	public List<CartVo> cartList(String cartNo) throws Exception {
		return selectList("buySql.cartList", cartNo);
	}

	public void buyInfo_insert(Map<String, Integer> map) {
		insert("buySql.buyInfo_insert", map);
		
	}

	public void delete_product(String cart_no) throws Exception {
		delete("buySql.delete_product", cart_no);
	}

	public List<CartVo> purchaseHistory(String userId) {
		return selectList("buySql.purchaseHistory", userId);
	}

	public BuyVo buyDetail(BuyVo buyVo) {
		return (BuyVo) selectOne("buySql.buyDetail", buyVo);
	}

	public void buyUseBino(BinoVo binoVo) {
		insert("buySql.buyUseBino", binoVo);
	}
}
