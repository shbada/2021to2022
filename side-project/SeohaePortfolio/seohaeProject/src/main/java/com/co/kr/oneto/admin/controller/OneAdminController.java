/**
 * 관리자 1:1문의내역 controller
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

package com.co.kr.oneto.admin.controller;

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
public class OneAdminController {
	
	private Log log = LogFactory.getLog(OneAdminController.class);
	
	@Autowired
	private OneAdminService oneAdminService;
	
	@Autowired
	private OneService oneService;

	/**
	    * @Method OneAdminList
	    * @Date 2017. 11. 11.
	    * @Writter seohae
	    * @Param 
	    * @EditHistory
	    * @Discript 1:1문의 게시판
	    * @Return String
	  */
	
	@RequestMapping(value="/oneAdminList", method={RequestMethod.GET, RequestMethod.POST})
	public String OneAdminList(@ModelAttribute OneVo oneVo, Model model){ //qVo: 게시글 정보, Model: jsp로 데이터 전달을 위함.
		
		/** 페이징 처리 */
		if(oneVo.getPageCnt() == null) oneVo.setPageSize(5);
		else oneVo.setPageSize(Integer.parseInt(oneVo.getPageCnt()));
		
		Map<String, Object> map = oneAdminService.oneAdminList(oneVo); //게시글 데이터를 조회해온다. Map-> 여러개의 데이터
		
		/** 페이지를 클릭하고, 검색을 하고서 '새글작성'페이지로 이동해왔다가 다시 목록으로 갈때 그대로 값을 가져가기 위함 */
		model.addAttribute("pageVO", oneVo); //페이징 처리
		model.addAttribute("boardList", map.get("list")); //조회해온 데이터들을 저장한 map의 데이터들을 가져와서 boardList에 저장
		return "one/admin/oneAdminList";
	}
	
	/**
	    * @Method OneAdminDetail
	    * @Date 2017. 11. 11.
	    * @Writter seohae
	    * @Param 
	    * @EditHistory
	    * @Discript 문의내역글 상세페이지
	    * @Return String
	  */
	
	@RequestMapping(value="/oneAdminDetail", method=RequestMethod.POST)
	public String OneAdminDetail(@ModelAttribute("OneVo") OneVo oneVo, Model model, HttpSession session){
		OneVo list = oneAdminService.oneAdminDetail(oneVo); // 클릭한 글번호(qVo)에 맞는 게시글에 대한 데이터를 가져와서 list에 담는다.
		list.setReqReDesc(list.getReqReDesc().replaceAll("<", "&lt;").replaceAll("&lt;br>", "<br>"));		/** 페이지를 클릭하고, 검색을 하고서 '새글작성'페이지로 이동해왔다가 다시 목록으로 갈때 그대로 값을 가져가기 위함 */
		model.addAttribute("OneVo", list); // list에 담겨진 데이터들을 OneVo에 담아서, .jsp 에 뿌려준다.
		model.addAttribute("pageVO", oneVo);
		return "one/admin/oneAdminDetail";
	}
	
	/**
	    * @Method OneAdminWrite
	    * @Date 2017. 11. 11.
	    * @Writter seohae
	    * @Param 
	    * @EditHistory
	    * @Discript 해당 문의글의 답변쓰기
	    * @Return String
	  */
	
	@RequestMapping(value="/oneAdminWrite")
	public String OneAdminWrite(@ModelAttribute("OneVo") OneVo oneVo, Model model){
		return "one/admin/oneAdminWrite"; //새글 페이지로만 이동하므로 따로 로직이 없음.
	}
	
	/**
	    * @Method OneAdminWriteSave
	    * @Date 2017. 11. 11.
	    * @Writter seohae
	    * @Param 
	    * @EditHistory
	    * @Discript 문의내역 답변 글쓰기 저장
	    * @Return String
	  */
	@RequestMapping(value="/oneAdminWriteSave", method=RequestMethod.POST)
	public String OneAdminWriteSave(@ModelAttribute("OneVo") OneVo oneVo, Model model, HttpServletResponse response, HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession(false);
		
		if(session == null) { //로그인을 안한상태면,
			response.sendRedirect("/login.do?GBN=SESSIONOUT");
		} else {
			if(session.getAttribute("userId") == null){ //로그인을 안한상태면,
				response.sendRedirect("/login.do?GBN=SESSIONOUT");
			} else { // 새로 쓴 게시글  저장 실행
				log.info(oneVo+"@@@@@@@@@@@@@@@@@@@@@@@@@@"); // 현재 로그인한 아이디를 가져옴
				oneVo.setReqReNo(oneVo.getReqNo());
				oneAdminService.oneAdminWriteSave(oneVo); // 새로 쓴 글을 sql에 저장.
				//답변이 작성됬으므로 질문글의 답변여부 변경
				oneAdminService.oneYnUpdate(oneVo);
				return "one/admin/oneAdminDetail";
			}
		}
		
		return "";
	}
	
	/**
	    * @Method OneMemberDetail
	    * @Date 2017. 11. 11.
	    * @Writter seohae
	    * @Param 
	    * @EditHistory
	    * @Discript 문의내역글 상세페이지 [관리자->회원 문의글 상세페이지]
	    * @Return String
	  */
	
	@RequestMapping(value="/oneMemberDetail", method=RequestMethod.POST)
	public String OneMemberDetail(@ModelAttribute("OneVo") OneVo oneVo, Model model, HttpSession session){
		OneVo list = oneService.oneDetail(oneVo); // 클릭한 글번호(oneVo)에 맞는 게시글에 대한 데이터를 가져와서 list에 담는다.
		list.setReqDesc(list.getReqDesc().replaceAll("<", "&lt;").replaceAll("&lt;br>", "<br>"));
		/** 페이지를 클릭하고, 검색을 하고서 '새글작성'페이지로 이동해왔다가 다시 목록으로 갈때 그대로 값을 가져가기 위함 */
		model.addAttribute("OneVo", list); // list에 담겨진 데이터들을 OneVo에 담아서, .jsp 에 뿌려준다.
		model.addAttribute("pageVO", oneVo);
		return "one/admin/oneDetail";
	}

}
