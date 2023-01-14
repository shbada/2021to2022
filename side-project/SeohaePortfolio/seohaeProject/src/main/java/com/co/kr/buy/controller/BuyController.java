package com.co.kr.buy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.co.kr.bino.service.BinoService;
import com.co.kr.bino.vo.BinoVo;
import com.co.kr.buy.service.BuyService;
import com.co.kr.buy.vo.BuyVo;
import com.co.kr.cart.vo.CartVo;
import com.co.kr.user.service.UserService;
import com.co.kr.user.vo.UserVo;

/**
 * BuyController
 * 
 * @author 김서해
 * @since 2017. 11. 12.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>

 * 1. (2017. 11. 12 / seohae / 최초생성)
 *
 * </pre>
 */

@Controller
public class BuyController {
	
	private Log log = LogFactory.getLog(BuyController.class);
	
	@Autowired
	BuyService buyService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BinoService binoService;
	
	/**
	* @Method buy
	* @Date 2017. 11. 12.
	* @Writter seohae
	* @EditHistory
	* @Discript 구매페이지로 이동
	* @Return String
	*/
	@RequestMapping("/buy")
	public String  buy(HttpSession session, HttpServletRequest request, Model model)throws Exception{
		
		int limitPoint;
		
		if(session.getAttribute("userId") == null) {
			model.addAttribute("msg", "로그인 후 사용 가능합니다.");
			model.addAttribute("url", "login.do");
			return "alert/alert";
		}
		String buyChoice = request.getParameter("buyChoice");				// 구매종류 선택시 필요!! 
		String userId = request.getParameter("userId"); 
		String totalCartNo = request.getParameter("totalCartNo");      // 콤마(,) 를 구분자로해서 cart_no를 가지고 있다.  
		String[] cartNo = totalCartNo.split(",");						// split() 메소드를 이용해서 문자열 자르기
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<CartVo> list = null;
		int sumMoney = 0;  
		if(buyChoice.trim().equals("buyAll")) {								// 전체구매시!!!
			list = buyService.getCartList(cartNo);
			for(int i=0; i<list.size(); i++) {							
				sumMoney += list.get(i).getPdPrice() * list.get(i).getPdAmount();
			}
		} else {															// 선택 구매시!!!
			list = buyService.getCartList(cartNo);
			for(int i=0; i<list.size(); i++) {								
				sumMoney += list.get(i).getPdPrice() * list.get(i).getPdAmount();
			}
		}
		int fee = 2500;
		
		int binoSum = binoService.binoSumCount(userId);

		map.put("list", list);
		map.put("count", list.size());
		map.put("sumMoney", sumMoney);
		map.put("fee", fee);
		map.put("allSum", sumMoney + fee);
		map.put("userId", userId);
		map.put("binoSum", binoSum);

		model.addAttribute("map", map);
		limitPoint = sumMoney;
		return "buy/buyForm";
	}
	
	/**
	* @Method buyDeliveryCheck
	* @Date 2017. 11. 12.
	* @Writter seohae
	* @EditHistory
	* @Discript 구매자 정보 조회
	* @Return String
	*/
	@RequestMapping("/buyDeliveryCheck")
	@ResponseBody
	public Object buyDeliveryCheck(HttpSession session) throws Exception {
		String userId = (String) session.getAttribute("userId");
		UserVo userVo = userService.editUser(userId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", userVo);
		return map;
	}
	
	/**
	* @Method payMent
	* @Date 2017. 11. 12.
	* @Writter seohae
	* @param BuyVo
	* @EditHistory
	* @Discript 구매 완료
	* @Return String
	*/
	@RequestMapping(value="/payment")
	public String payMent(HttpServletRequest request, BuyVo buyVo, HttpSession session, Model model) throws Exception {
		buyService.buyInsert(buyVo); // 구매정보 insert!
		int getBuy_no = buyService.getBuy_no(buyVo.getUserId()); // buyinfo table에 buy_no를 넣어주기 위해서 buy_no를 가져온다.
		String totalCartNo = request.getParameter("totalCartNo"); // 결제할 cart_no 를 가지고 있는 문자열을 받는다.  
		String[] cartNo = totalCartNo.split(","); // split() 메소드를 이용해서 문자열 자르기
		buyService.cartList(cartNo, getBuy_no);	 // 결제할 카트 번호와 저장된 구매번호 를 가져가서 3가지 일을 해준다 (1.카트 번호로 해당정보 select 2.해당 정보로 buy_info 에 insert 3.결제한 목록 장바구니에서 삭제하기)
		
		// bino사용내역을 bino 내역에 추가
		BinoVo binoVo = new BinoVo();
		binoVo.setBinoCg("교재 구매");
		binoVo.setBinoYn("N");
		binoVo.setUserId(buyVo.getUserId());
		binoVo.setBino(buyVo.getBuyTotalbino());
		buyService.buyUseBino(binoVo);
		// 총 구매 금액을 사용자 bino에서 차감
		int userBinoMinus = binoService.userBinoAdd(buyVo.getUserId()) - buyVo.getBuyTotalbino();
		//사용자 테이블의 bino 총액 변경
		UserVo userVo = new UserVo();
		userVo.setUserId(buyVo.getUserId());
		userVo.setBino(userBinoMinus);
		binoService.userBinoUpdate(userVo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buyVo", buyVo);
		return "redirect:/purchaseHistory.do";
	}
	
	/**
	* @Method purchaseHistory
	* @Date 2017. 11. 12.
	* @Writter seohae
	* @param BuyVo
	* @EditHistory
	* @Discript 구매 내역 조회
	* @Return String
	*/
	@RequestMapping("/purchaseHistory")
	public String PurchaseHistory(HttpSession session, Model model) {
		
		String userId = (String)session.getAttribute("userId");
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<CartVo> list = buyService.purchaseHistory(userId);
		
		map.put("list", list);
		map.put("userId", userId);

		model.addAttribute("map", map);
		
		return "buy/purchaseHistory";
	}
	
	/**
	* @Method BuyDetail
	* @Date 2017. 11. 12.
	* @Writter seohae
	* @param BuyVo
	* @EditHistory
	* @Discript 구매 내역 상세정보 조회
	* @Return String
	*/
	@RequestMapping(value="/buyDetail", method=RequestMethod.POST)
	public String BuyDetail(@ModelAttribute("BuyVo") BuyVo buyVo, Model model, HttpSession session){
		BuyVo list = buyService.buyDetail(buyVo); 
		model.addAttribute("BuyVo", list);
		
		return "buy/buyDetail";
	}
}
