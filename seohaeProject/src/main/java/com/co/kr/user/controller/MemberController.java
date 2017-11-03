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

package com.co.kr.user.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.co.kr.user.vo.UserVo;

@Controller
public class MemberController {
	
	private Log log = LogFactory.getLog(MemberController.class);
	
	/**
	    * @Method CreateUser
	    * @Date 2017. 11. 03.
	    * @Writter seohae
	    * @Discript 회원가입 페이지
	    * @Return String
	  */
	
	@RequestMapping(value="/createUser")
	public String CreateUser(@ModelAttribute("userVo") UserVo userVo, Model model) {
		return "member/createUser";
	}
	
}
