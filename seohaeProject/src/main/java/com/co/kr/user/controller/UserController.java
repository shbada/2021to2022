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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestParam;
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
	
	/**
	    * @Method EditUser
	    * @Date 2017. 11. 04.
	    * @Writter seohae
	    * @EditHistory
	    * @Discript 회원정보 수정페이지
	    * @Return String
	  */
	
    @RequestMapping(value="editUser")
    public String EditUser(Model model, HttpSession session){
    	String userId = (String)session.getAttribute("userId");
    	model.addAttribute("userVo",userService.editUser(userId));
    	return "user/editUser";
    }
    
    /**
	    * @Method EditUserSave
	    * @Date 2017. 11. 04.
	    * @Writter seohae
	    * @Param UserVo
	    * @EditHistory
	    * @Discript 회원정보 수정완료
	    * @Return void
	  */
    @RequestMapping(value="editUserSave")
    public void EditUserSave(@ModelAttribute UserVo userVo,HttpServletResponse response) throws Exception{
    	
    	String hashPassword = BCrypt.hashpw(userVo.getUserPw(), BCrypt.gensalt()); //pom.xml에 추가하여 사용 가능
		userVo.setUserPw(hashPassword); // 암호화 저장
		
		//전화번호에 -, 뛰어쓰기 있을 때 ""로  
		if(StringUtils.isNotBlank(userVo.getUserPhone())){
			userVo.setUserPhone(userVo.getUserPhone().replaceAll("-", "").replaceAll(" ", "")); 
		}
    	userService.editUserSave(userVo);
    	response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('정보가 수정되었습니다.'); "+"location.href='/editUser.do'</script>");
    }
	
	/**
	    * @Method MemberEmailCheck
	    * @Date 2017. 11. 04.
	    * @Writter seohae
	    * @Param UserVo
	    * @Param
	    * @EditHistory
	    * @Discript 회원정보수정_이메일 체크
	    * @Return String
	  */
	
	@RequestMapping(value="/memberEmailCheck", method=RequestMethod.POST)
	@ResponseBody
	public String MemberEmailCheck(@ModelAttribute("userVo") UserVo userVo, @RequestParam(value = "userEmail") String userEmail, HttpServletRequest request){
		
		HttpSession session = request.getSession(false);
		
		userVo.setUserId((String) session.getAttribute("userId"));  // 현재 로그인하고있는 회원의 아이디를 가져와서 userVo에 저장.
		
		UserVo checkEmailVo = new UserVo(); // userVo의 변수 선언
		checkEmailVo = userService.CheckEmail(userVo); // 현재 로그인한 아이디의 원래 이메일을 가져온다.
		
		if(!userEmail.equals(checkEmailVo.getUserEmail())){ //jsp페이지에서 입력된 이메일과 원래 이메일이 다를때, (이유? 처음 수정페이지에서 마우스로 대고 떼면 변경되지않은 이메일에도 중복된이메일이라고 뜨기때문)
			UserVo checkUserVo = userService.memberEmailCheck(userVo); //이메일 중복체크 실행
			if(checkUserVo == null) return "ok"; //중복된 이메일이 없으면,
			else return "fal"; //중복된 이메일이 있으면
		}
		return "no"; //원래 이메일이면.
	}
	
	/**
	 * @Method MemberDelete
	 * @Date 2017. 11. 04.
	 * @Writter seohae
	 * @Param 
	 * @Param 
	 * @EditHistory
	 * @Discript 회원탈퇴 기능
	 * @Return String
	 */
	
	@RequestMapping(value="/memberDelete", method=RequestMethod.POST)
	@ResponseBody
	public String MemberDelete(@ModelAttribute UserVo userVo) throws IOException{
		userService.memberDelete(userVo);
		return "ok";
	}
	
	/**
	    * @Method PwUpdate
	    * @Date 2017. 11. 04.
	    * @Writter seohae
	    * @Param 
	    * @Param
	    * @EditHistory
	    * @Discript 비밀변경 페이지로 이동
	    * @Return String
	  */
	
	@RequestMapping(value="/pwUpdate")
	public String PwUpdate(){  // 회원의 비밀번호를 입력하여, 맞으면 회원정보 게시판으로 이동할수 있도록 비밀번호 확인 페이지로 이동.
		return "user/pwUpdate";
	}
	
	/**
	    * @Method PwUpdateOk
	    * @Date 2017. 11. 04.
	    * @Writter seohae
	    * @EditHistory
	    * @Discript 비밀번호 변경 완료
	    * @Return String
	  */
	
	@RequestMapping(value="/pwUpdateOk", method=RequestMethod.POST) // 파라미터: nowPw(.jsp에서 사용자가 입력한 비밀번호 값을 가져옴 -> 맞는치 체크해야함), userPw(변경할 비밀번호를 가져옴)
	@ResponseBody
	public String PwUpdateOk(@ModelAttribute UserVo userVo, @RequestParam(value = "nowPw") String nowPw, @RequestParam(value = "userPw") String userPw, Model model, HttpServletRequest request){
		
		HttpSession session = request.getSession(false);
		
		userVo.setUserId((String) session.getAttribute("userId"));  // 현재 로그인하고있는 회원의 아이디를 가져와서 userVo에 저장.
		
		UserVo checkPwVo = new UserVo(); // userVo의 변수 선언
		checkPwVo = userService.CheckPw(userVo); // 현재 로그인하고있는 회원의 아이디를 저장시킨 userVo를 사용하여 해당 회원의 비밀번호를 가져온다. 
		
		if(BCrypt.checkpw(nowPw, checkPwVo.getUserPw())){// 파라미터로 가져온 userPw와 sql에서 조회해온 회원의 비밀번호가 일치하면,
			String hashPassword = BCrypt.hashpw(userPw, BCrypt.gensalt()); //비밀번호를 암호화해서 hashPassword에 저장한다
			userVo.setUserPw(hashPassword); //저장된 암호화된 비밀번호를 userVo의 userPw에 담는다
			
			userService.pwUpdateOk(userVo); //비밀번호 변경 실행.
			return "ok";
		} else{
			return "fal";
		}
	}
}
