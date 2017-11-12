package com.co.kr.cart.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.co.kr.book.service.BookService;
import com.co.kr.cart.service.CartService;
import com.co.kr.cart.vo.CartVo;

/**
 * CartController
 * 
 * @author 김서해
 * @since 2017. 11. 06.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>

 * 1. (2017. 11. 06 / seohae / 최초생성)
 *
 * </pre>
 */

@Controller
public class CartController {

	private Log log = LogFactory.getLog(CartController.class);

	@Autowired
	private CartService cartService;
	
	/**
	    * @Method CartInsert
	    * @Date 2017. 11. 06.
	    * @Writter seohae
	    * @param cartVo
	    * @Discript 장바구니 추가
	    * @Return String
	  */
	
	@RequestMapping("/cartInsert")
    @ResponseBody
    public String CartInsert(@ModelAttribute CartVo cartVo, HttpSession session){
    	
		if (session.getAttribute("userId") == null){
    		return "fal";
    	}
		String user_id = (String) session.getAttribute("userId");
		
    	if (user_id != null){
	       
    		cartVo = cartService.cartBookDetail(cartVo);
    		cartVo.setUser_id(user_id); 
    		cartVo.setPdAmount(1);
	        int count = cartService.countCart(cartVo);
	        if(count == 0){
	            cartService.cartInsert(cartVo);
	        } else {
	            cartService.UpdateCount(cartVo); 
	        }
    	}
        return "ok";
    }
	
	/**
	    * @Method CartList
	    * @Date 2017. 11. 06.
	    * @Writter seohae
	    * @Discript 장바구니 목록
	    * @Return String
	  */
	@RequestMapping("/cartList")
	public String CartList(HttpSession session, Model model) {
		
		String userId = (String)session.getAttribute("userId");
    	Map<String, Object>map = new HashMap<String, Object>();
    	List<CartVo> list = cartService.cartList(userId); // 장바구니 리스트
    	System.out.println(list+"12asdkalsdjaslkjdlsaks");
    	int sumMoney = cartService.sumMoney(userId); // 장바구니 전체 금액
    	int fee = 2500; //전체금액이 6만 >= 0원 아니면 2500
    	
    	map.put("list", list);
    	map.put("count", list.size());
    	map.put("sumMoney", sumMoney); // 장바구니 전체 금액
    	map.put("fee", fee);
    	map.put("allSum", sumMoney+fee); 
    	map.put("userId", userId);
    	model.addAttribute("map",map);

		return "cart/cartList";
	}
	
	/**
	    * @Method CartDelete
	    * @Date 2017. 11. 06.
	    * @Writter seohae
	    * @param product_no
	    * @Discript 장바구니 삭제
	    * @Return String
	  */
	@RequestMapping("/cartDelete")
	public String CartDelete(@RequestParam int product_no) {
		cartService.cartDelete(product_no); 
		return "redirect:/cartList.do";
	}
	
	/**
	    * @Method CartUpdate
	    * @Date 2017. 11. 06.
	    * @Writter seohae
	    * @param pdAmount
	    * @param pdNo
	    * @Discript 장바구니 수량 수정
	    * @Return String
	  */
	@RequestMapping(value = "/cartUpdate", method = RequestMethod.POST)
	public String CartUpdate(@RequestParam int pdAmount, @RequestParam int pdNo, HttpSession session) {
		System.out.println(pdAmount + "@@@@"+ pdNo);
		String user_id = (String) session.getAttribute("userId");
		
		CartVo cartVo = new CartVo();
		cartVo.setUser_id(user_id); 
		cartVo.setPdAmount(pdAmount); 
		cartVo.setPdNo(pdNo); 
		cartService.cartUpdate(cartVo); 
		
		return "redirect:/cartList.do";
	}
	
	/**
	    * @Method CartListDelete
	    * @Date 2017. 11. 06.
	    * @Writter seohae
	    * @param data
	    * @Discript 장바구니 리스트 선택 삭제
	    * @Return String
	  */
	@RequestMapping(value="/cartListDelete", method=RequestMethod.POST, consumes="application/json")	 
	public void CartListDelete(@RequestBody List<CartVo> data) {
		cartService.cartListDelete(data);
	}
}
