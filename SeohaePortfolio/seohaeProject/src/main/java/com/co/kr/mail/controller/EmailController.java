package com.co.kr.mail.controller;


import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.co.kr.mail.service.MailService;
import com.co.kr.user.service.UserService;
import com.co.kr.user.vo.UserVo;

@Controller
public class EmailController {
	private Log log = LogFactory.getLog(EmailController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	MailService mailService;
	
	@RequestMapping(value="/idFind")
	public String idFind(){
		return "login/idFind"; 
	}
	
	@RequestMapping(value="/pwFind")
	public String pwFind(){
		return "login/pwFind"; 
	}
	
	@RequestMapping(value="sendMail", method=RequestMethod.POST)
	public String sendMail(HttpSession session, @RequestParam("email") String email,RedirectAttributes ra){
		UserVo userVo = userService.findAccount(email);
		
		if(userVo != null) {
			String subject = "요청하신 아이디 찾기 안내 입니다.";
			StringBuilder sb = new StringBuilder();
			sb.append("귀하의 아이디는" + userVo.getUserId() + " 입니다.");
			mailService.send(subject, sb.toString(), "kmw9186@gmail.com", email, null);
			ra.addFlashAttribute("resultMsg", "귀하의 이메일 주소로 해당 이메일로 가입된 아이디를 발송 하였습니다.");
		}else{
			ra.addFlashAttribute("resultMsg", "귀하의 이메일로 가입된 아이디가 존재하지 않습니다.");
		}
		return "redirect:/idFind.do";
	}
	
	@RequestMapping(value="joinCode")
	@ResponseBody
	public boolean joinCode(HttpSession session, @RequestParam("joinEmail")String joinEmail){
		int ran = new Random().nextInt(100000) + 10000;
		String joinCode = String.valueOf(ran);
		session.setAttribute("joinCode", joinCode);
		
		String subject = "비밀번호 초기화 인증 코드 발급 안내 입니다..";
		StringBuilder sb = new StringBuilder();
		sb.append("귀하의 인증 코드는" + joinCode + " 입니다.");
		return mailService.send(subject, sb.toString(), "kimseohae96@gmail.com", joinEmail, null);
	}
	
	@RequestMapping(value="joinCodeCheck")
	@ResponseBody
	public ResponseEntity<String> joinCodeCheck(HttpSession session,String joinCode){
		
		String originalJoinCode = (String)session.getAttribute("joinCode");
		if(originalJoinCode.equals(joinCode))
			return new ResponseEntity<String>("certification", HttpStatus.OK);
		else return new ResponseEntity<String>("false", HttpStatus.OK);
	}
	
	@RequestMapping(value="resetPassword", method=RequestMethod.POST)
	public String resetPassword(HttpSession session,@RequestParam String joinEmail,RedirectAttributes ra){
		
		UserVo userVo = userService.findAccount(joinEmail);
			String password = "";
	        // String encryptPassWord = "";
	        char pwCollection[] = new char[] {'1','2','3','4','5','6','7','8','9','0','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O',
	            'P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u',
	            'v','w','x','y','z','!','@','#','$','%','^','&','*','(',')'};
	         
	        for (int i = 0; i < 8; i++) {
	        	int selectRandomPw = (int)(Math.random()*(pwCollection.length));
	        	password += pwCollection[selectRandomPw];
			}
	        String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
	        userVo.setUserPw(hashPassword);
	        
	        userService.userChangePwUpdate(userVo);
	        
	        String subject = "임시 비밀번호 발급 안내 입니다.";
			StringBuilder sb = new StringBuilder();
			sb.append("귀하의 임시 비밀번호는" + password + " 입니다.");
			mailService.send(subject, sb.toString(), "kimseohae96@gmail.com", joinEmail, null);
			ra.addFlashAttribute("resultPwMsg", "귀하의 이메일 주소로 임시 비밀번호를 발송 하였습니다.");
			return "redirect:/pwFind.do";
	}
	
}
