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

package com.co.kr.login.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	private Log log = LogFactory.getLog(LoginController.class);
	
	/**
	    * @Method LoginPage
	    * @Date 2017. 11. 03.
	    * @Writter seohae
	    * @Discript 로그인 페이지
	    * @Return String
	  */
	
	@RequestMapping(value="/login")
	public String LoginPage() {
		return "login/login";
	}
	
}
