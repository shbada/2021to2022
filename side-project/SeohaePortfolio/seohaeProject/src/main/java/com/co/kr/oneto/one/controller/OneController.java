/**
 * 회원 1:1문의내역 controller
 * 
 * @author 김서해
 * @since 2017. 11. 11.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 * 1. (2017. 11. 11 / seohae / 최초생성)
 *
 * </pre>
 */

package com.co.kr.oneto.one.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.co.kr.oneto.admin.service.OneAdminService;
import com.co.kr.oneto.one.service.OneService;
import com.co.kr.oneto.one.vo.OneVo;


@Controller
public class OneController {
	
	private Log log = LogFactory.getLog(OneController.class);
	
	@Autowired
	private OneService oneService;
	
	@Autowired
	private OneAdminService oneAdminService;
	
	/**
	    * @Method OneList
	    * @Date 2017. 11. 11.
	    * @Writter seohae
	    * @Param 
	    * @EditHistory
	    * @Discript 1:1문의 게시판
	    * @Return String
	  */
	
	@RequestMapping(value="/oneList", method={RequestMethod.GET, RequestMethod.POST})
	public String OneList(@ModelAttribute OneVo oneVo, Model model, HttpSession session){
		
		oneVo.setUserId((String) session.getAttribute("userId"));
		
		if(session.getAttribute("userId") == null){
			return "login/login";
		}
		
		/** 페이징 처리 */
		if(oneVo.getPageCnt() == null) oneVo.setPageSize(5);
		else oneVo.setPageSize(Integer.parseInt(oneVo.getPageCnt()));
		
		log.info(oneVo.getUserId());
		Map<String, Object> map = oneService.oneList(oneVo); //게시글 데이터를 조회해온다. Map-> 여러개의 데이터
		
		/** 페이지를 클릭하고, 검색을 하고서 '새글작성'페이지로 이동해왔다가 다시 목록으로 갈때 그대로 값을 가져가기 위함 */
		model.addAttribute("pageVO", oneVo); //페이징 처리
		model.addAttribute("boardList", map.get("list")); //조회해온 데이터들을 저장한 map의 데이터들을 가져와서 boardList에 저장
		return "one/oneList";
	}
	
	/**
	    * @Method OneWrite
	     * @Date 2017. 11. 11.
	    * @Writter seohae
	    * @Param 
	    * @EditHistory
	    * @Discript 문의내역 글쓰기로 이동
	    * @Return String
	  */
	
	@RequestMapping(value="/oneWrite")
	public String OneWrite(@ModelAttribute("OneVo") OneVo oneVo, Model model){
		return "one/oneWrite"; //새글 페이지로만 이동하므로 따로 로직이 없음.
	}
	
	/**
	    * @Method OneWriteSave
	     * @Date 2017. 11. 11.
	    * @Writter seohae
	    * @Param 
	    * @EditHistory
	    * @Discript 문의내역 글쓰기 저장
	    * @Return String
	  */
	@RequestMapping(value="/oneWriteSave", method=RequestMethod.POST)
	public String OneWriteSave(@ModelAttribute("OneVo") OneVo oneVo, Model model, HttpServletResponse response, HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession(false);
		
		if(session == null) { //로그인을 안한상태면,
			response.sendRedirect("/main/main.do?GBN=SESSIONOUT");
		} else {
			if(session.getAttribute("userId") == null){ //로그인을 안한상태면,
				response.sendRedirect("/main/main.do?GBN=SESSIONOUT");
			} else { // 새로 쓴 게시글  저장 실행
				log.info(session.getAttribute("userId")); // 현재 로그인한 아이디를 가져옴
				oneVo.setUserId((String) session.getAttribute("userId")); // 현재 로그인한 아이디를 작성자 아이디에 저장
				oneService.oneWriteSave(oneVo); // 새로 쓴 글을 sql에 저장.
				int reqNo = oneService.freeBoardIdx(); //글번호 가져오기
				oneVo.setReqNo(reqNo); //글번호 담기
				/** 페이지를 클릭하고, 검색을 하고서 '새글작성'페이지로 이동해왔다가 다시 목록으로 갈때 그대로 값을 가져가기 위함 */
				model.addAttribute("pageVO", oneVo); // 페이징
				return "one/oneDetail";
			}
		}
		
		return "";
	}
	
	/**
	    * @Method OneDetail
	    * @Date 2017. 11. 11.
	    * @Writter seohae
	    * @Param 
	    * @EditHistory
	    * @Discript 문의내역글 상세페이지
	    * @Return String
	  */
	
	@RequestMapping(value="/oneDetail", method=RequestMethod.POST)
	public String OneDetail(@ModelAttribute("OneVo") OneVo oneVo, Model model, HttpSession session){
		OneVo list = oneService.oneDetail(oneVo);
		list.setReqDesc(list.getReqDesc().replaceAll("<", "&lt;").replaceAll("&lt;br>", "<br>"));
		model.addAttribute("OneVo", list); 
		model.addAttribute("pageVO", oneVo);
		return "one/oneDetail";
	}
	
	/**
	    * @Method OneAdminDetail
	    * @Date 2017. 11. 11.
	    * @Writter seohae
	    * @Param 
	    * @EditHistory
	    * @Discript 문의내역글 상세페이지  [회원 ->관리자의 답변게시글 상세페이지]
	    * @Return String
	  */
	
	@RequestMapping(value="/oneMemberAdminDetail", method=RequestMethod.POST)
	public String OneMemberAdminDetail(@ModelAttribute("OneVo") OneVo oneVo, Model model, HttpSession session){
		OneVo list = oneAdminService.oneAdminDetail(oneVo); // 클릭한 글번호(qVo)에 맞는 게시글에 대한 데이터를 가져와서 list에 담는다.
		list.setReqReDesc(list.getReqReDesc().replaceAll("<", "&lt;").replaceAll("&lt;br>", "<br>"));
		model.addAttribute("OneVo", list); 
		model.addAttribute("pageVO", oneVo);
		return "one/oneAdminDetail";
	}

}
