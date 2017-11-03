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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	private Log log = LogFactory.getLog(MainController.class);
	
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
	
	@RequestMapping(value="/mainTop")
	public String MainTop() {
		return "main/mainTopPage";
	}
}
