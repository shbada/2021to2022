/**
 * UserController
 * 
 * @author 김서해
 * @since 2017. 11. 04.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>

 * 1. (2017. 11. 04 / seohae / 최초생성)
 *
 * </pre>
 */

package com.co.kr.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.co.kr.user.service.UserService;
import com.co.kr.user.vo.UserVo;
/*import com.co.kr.common.util.Sha256Util;*/

@Controller
public class UserController {
	
	private Log log = LogFactory.getLog(UserController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	    * @Method userCreatePage
	    * @Date 2017. 11. 04.
	    * @Writter seohae
	    * @Param UserVo
	    * @Param Model
	    * @EditHistory
	    * @Discript 회원가입 페이지.
	    * @Return String
	  */
	
	@RequestMapping(value="/createUser")
	public String userCreatePage(@ModelAttribute("userVo") UserVo userVo, Model model){ //""안에 값 -> jsp 에서 userVo.필드명 으로 데이터 불러올수있음
		return "user/createUser";
	}
	
	/**
	    * @Method createIdCheck
	    * @Date 2017. 11. 04.
	    * @Writter seohae
	    * @Param UserVo
	    * @Param
	    * @EditHistory
	    * @Discript 중복 회원 아이디 체크
	    * @Return String
	  */
	
	@RequestMapping(value="/createIdCheck", method=RequestMethod.POST)
	@ResponseBody //ajax사용시 무조건 어노테이션을 써줘야한다!!
	public String createIdCheck(@ModelAttribute("userVo") UserVo userVo){
		UserVo checkUserVo = userService.createIdCheck(userVo); //view에서 입력한 ID값으로 조회했을때 조회된 값 저장. (중복: 아이디저장됨, 중복X: NULL) ID 중복체크
		if(checkUserVo == null) return "ok"; //중복X: 아이디생성 가능. -> 리턴값은 jsp로 다시 감
		else return "fal";
	}
	
	/**
	    * @Method createEmailCheck
	    * @Date 2017. 11. 04.
	    * @Writter seohae
	    * @Param UserVo
	    * @Param
	    * @EditHistory
	    * @Discript 중복 회원 이메일 체크
	    * @Return String
	  */
	
	@RequestMapping(value="/createEmailCheck", method=RequestMethod.POST)
	@ResponseBody
	public String createEmailCheck(@ModelAttribute("userVo") UserVo userVo){
		UserVo checkUserVo = userService.createEmailCheck(userVo); //이메일 중복체크
		if(checkUserVo == null) return "ok";
		else return "fal";
	}
	
	/**
	    * @Method createIdAndEmailCheck
	    * @Date 2017. 11. 04.
	    * @Writter seohae
	    * @Param UserVo
	    * @Param
	    * @EditHistory
	    * @Discript 중복 회원 이메일, 아이디 체크
	    * @Return String
	  */
	
	@RequestMapping(value="/createIdAndEmailCheck", method=RequestMethod.POST)
	@ResponseBody //json 방식 사용시, 써야하는 어노테이션.
	public String createIdAndEmailCheck(@ModelAttribute("userVo") UserVo userVo){
		UserVo idCheckUserVo = userService.createIdCheck(userVo); //아이디 중복체크
		UserVo EmailCheckUserVo = userService.createEmailCheck(userVo); //이메일 중복체크
		if(idCheckUserVo == null && EmailCheckUserVo == null) return "ok"; //호출한 jsp파일로 다시간다!
		else return "fal";
	}
	
	/**
	    * @Method userCreateSuccess
	    * @Date 2017. 11. 04.
	    * @Writter seohae
	    * @Param UserVo
	    * @Param Model
	    * @Param HttpServletResponse
	    * @EditHistory
	    * @Discript 회원가입 완료, DB에 데이터 저장
	    * @Return void
	  */
	
	@RequestMapping(value="/userCreateSuccess", method=RequestMethod.POST)
	public void userCreateSuccess(@ModelAttribute("userVo") UserVo userVo, Model model, HttpServletResponse response) throws IOException{
		String hashPassword = BCrypt.hashpw(userVo.getUserPw(), BCrypt.gensalt()); //비밀번호를 암호화해서 hashPassword에 저장한다
		userVo.setUserPw(hashPassword); //저장된 암호화된 비밀번호를 userVo의 userPw에 담는다
		
		if(StringUtils.isNotBlank(userVo.getUserPhone())){ //휴대폰값이 null이 아니면
			userVo.setUserPhone(userVo.getUserPhone().replaceAll("-", "").replaceAll(" ", "")); //-, 공백을 치환한다
		}
		
		userService.userCreateSuccess(userVo); //입력된 값을 sql 테이블에 insert하는 쿼리를 실행을 위함!
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter(); //alert 창 출력을 위함!
		out.println("<script>alert('정상적으로 회원가입이되었습니다. \\n로그인 페이지로 이동합니다.'); location.href='/login.do'</script>");
		
	}
	
}
