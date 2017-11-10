/**
 * LoginController
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

package com.co.kr.login.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.co.kr.login.LoginManager;
import com.co.kr.login.service.LoginService;
import com.co.kr.login.vo.LoginVo;
import com.co.kr.user.vo.UserAuthVo;

@Controller
public class LoginController {
	
	private Log log = LogFactory.getLog(LoginController.class);
	
	@Autowired
	LoginService loginService;
	
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
	
	/**
	* @Method LoginCheck
	* @Date 2017. 11. 04.
	* @Writter seohae
	* @Param LoginVo
	* @EditHistory
	* @Discript 로그인 확인
	* @Return String
	*/
	
	@RequestMapping(value="/login/loginCheck")
	public String LoginCheck(@ModelAttribute LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) throws IOException{
		String redirectUrl = "redirect:/main.do";
		String loginId = (request.getParameter("userId") != null) ? request.getParameter("userId") : "";
		String password = (request.getParameter("userPw") != null) ? request.getParameter("userPw") : "";
		
		LoginManager loginManager = LoginManager.getInstance();
		
		loginVo.setUserId(loginId);
				
		LoginVo checkLoginVo = new LoginVo();
		checkLoginVo = loginService.selectUserLoginCheck(loginVo);
		
		if (checkLoginVo == null) {
			redirectUrl = "redirect:/login.do?LOGIN_ERR=IDNE";

			loginVo.setUserId(loginId);

		} else {
			if (!checkLoginVo.getUserId().equals(loginId)) {
				redirectUrl = "redirect:/login.do?LOGIN_ERR=IDNE";

				loginVo.setUserId(checkLoginVo.getUserId());
				
			} else if (!BCrypt.checkpw(password, checkLoginVo.getUserPw())) {
				redirectUrl = "redirect:/login.do?LOGIN_ERR=PWNE";
			} else {
				// 이미 접속한 아이디인지 체크한다.
				if (!loginManager.isUsing(loginId)) {
					loginManager.setSession(request.getSession(), loginId);

				} else {
					HttpSession session = request.getSession();
					session.setAttribute("userId", loginId);
					redirectUrl = "redirect:/login.do?LOGIN_ERR=IDDUP";
					return redirectUrl;
				}

				UserAuthVo userAuthVo = new UserAuthVo();
				userAuthVo.setFirstUrl("/main.do");
				HttpSession session = request.getSession();
				session.setAttribute("lastContactFmtDt", checkLoginVo.getLastContactFmtDt());
				session.setAttribute("userId", checkLoginVo.getUserId());
				session.setAttribute("userNm", checkLoginVo.getUserNm());
				session.setAttribute("userLevel", checkLoginVo.getUserLevel());
				session.setAttribute("userFirstAddr", checkLoginVo.getUserFirstAddr());
				session.setAttribute("userSecondAddr", checkLoginVo.getUserSecondAddr());
				session.setAttribute("divGb", userAuthVo.getDivGb());
				session.setAttribute("firstMenu", userAuthVo.getFirstMenu());
				session.setAttribute("firstUrl", userAuthVo.getFirstUrl());
				session.setAttribute("ipAddr", getClientIP(request));
				loginVo.setIpAddr(getClientIP(request));
			}
		}
		
		return redirectUrl;
	}
	
	/**
	    * 접속된 클라이언트 아이피 추출
	    * @ HttpServletRequest request
	    * - 프록시나 Load Balancer 같은것을 거쳐 오게되는 경우 
	    *   request.getRemoteAddr() 호출로는 정확한 아이피를 가져오지 못하여 메소드 구현
	    *   localhost에서 테스트 하는 경우 0:0:0:0:0:0:0:1 값으로 넘어 오는 경우가 있다.
	    *     이 값은 IPv6 에서 IPv4의 127.0.0.1 과 같은 값이다.
	    *    Tomcat으로  개발시 이게 문제가 되는 경우 vm arguments에  -Djava.net.preferIPv4Stack=true 값을 넣어 주면 된다.
	 */   
	   public String getClientIP(HttpServletRequest request) {
	      String ip = request.getHeader("X-FORWARDED-FOR");
	      if(ip == null || ip.length() == 0) {
	         ip = request.getHeader("Proxy-Client-IP");
	      }
	      if(ip == null || ip.length() == 0) {
	         ip = request.getHeader("WL-Proxy-Client-IP");
	      }
	      if(ip == null || ip.length() == 0) {
	         ip = request.getRemoteAddr();
	      }
	      if(ip.equals("0:0:0:0:0:0:0:1")) {
	         ip = "127.0.0.1";
	      }
	      return ip;
	   }
	   
	   /**
		* @Method Logout
		* @Date 2017. 11. 04.
		* @Writter seohae
		* @EditHistory
		* @Discript 로그아웃
		* @Return String
		*/
	   
	   @RequestMapping(value = "/logOut", method = RequestMethod.GET)
		public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

		   	HttpSession session = request.getSession();
			session.invalidate();
			
			return "main/mainPage";
		}
}
