/**
 * BinoController
 * 
 * @author 김서해
 * @since 2017. 11. 10.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>

 * 1. (2017. 11. 10 / seohae / 최초생성)
 *
 * </pre>
 */

package com.co.kr.bino.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.co.kr.bino.service.BinoService;
import com.co.kr.bino.vo.BinoVo;

@Controller
public class BinoController {
	
	private Log log = LogFactory.getLog(BinoController.class);
	
	@Autowired
	private BinoService binoService;
	
	/**
	    * @Method BinoList
	    * @Date 2017. 11. 10
	    * @Writter seohae
	    * @Discript bino 내역 목록
	    * @Return String
	  */
	
	@RequestMapping(value="/binoList")
	public String BinoList(@ModelAttribute BinoVo binoVo, Model model, HttpSession session) {
		String userId = (String)session.getAttribute("userId");
		binoVo.setUserId(userId);
		model.addAttribute("binoList", binoService.binoList(binoVo));
		return "bino/binoList";
	}
}
