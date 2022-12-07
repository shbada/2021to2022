/**
 * MainController
 * 
 * @author 김서해
 * @since 2017. 11. 02.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>

 * 1. (2017. 11. 02 / seohae / 최초생성)
 *
 * </pre>
 */

package com.co.kr.main.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.co.kr.bino.service.BinoService;
import com.co.kr.message.service.MessageService;
import com.co.kr.message.vo.MessageVo;

@Controller
public class MainController {
	
	private Log log = LogFactory.getLog(MainController.class);
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private BinoService binoService;
	
	/**
	    * @Method mainSelectView
	    * @Date 2017. 11. 02.
	    * @Writter seohae
	    * @Discript 메인페이지
	    * @Return String
	  */
	
	@RequestMapping(value="/main")
	public String MainSelectView() {
		return "main/mainPage";
	}
	
	/**
	    * @Method MainTop
	    * @Date 2017. 11. 03.
	    * @Writter seohae
	    * @Discript 메인페이지 상단부분
	    * @Return String
	  */
	
	@RequestMapping(value="/mainTop", method={RequestMethod.GET, RequestMethod.POST})
	public String MainTop(@ModelAttribute MessageVo messageVo, HttpSession session, Model model) {
		if(session.getAttribute("userId") == null) {
			return "main/mainTopPage";
		}
		String user_id = (String) session.getAttribute("userId");
		
		int messageCount = messageService.messageNewCount(user_id);
		int binoSum = binoService.binoSumCount(user_id);
		
		model.addAttribute("messageCount", messageCount);
		model.addAttribute("binoSum", binoSum);
		return "main/mainTopPage";
	}
}
