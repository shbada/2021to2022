/**
 * FreeBoardController
 * 
 * @author 김서해
 * @since 2017. 11. 07.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>

 * 1. (2017. 11. 07 / seohae / 최초생성)
 *
 * </pre>
 */

package com.co.kr.board.free.controller;

import java.io.IOException;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.co.kr.board.free.service.FreeBoardService;
import com.co.kr.board.free.vo.FreeBoardVo;

@Controller
public class FreeBoardController {
	
	private Log log = LogFactory.getLog(FreeBoardController.class);
	
	@Autowired
	private FreeBoardService freeBoardService;

	/**
	    * @Method freeBoardSelectList
	    * @Date 2017. 11. 07.
	    * @Writter seohae
	    * @Param 
	    * @EditHistory
	    * @Discript 자유게시판 리스트 조회.
	    * @Return String
	  */
	@RequestMapping(value="/freeBoardSelectList", method={RequestMethod.POST, RequestMethod.GET})
	public String freeBoardSelectList(@ModelAttribute FreeBoardVo freeBoardVo, Model model){
		System.out.println(freeBoardVo);
		if(freeBoardVo.getPageCnt() == null) freeBoardVo.setPageSize(5);
		else freeBoardVo.setPageSize(Integer.parseInt(freeBoardVo.getPageCnt()));
		
		Map<String, Object> map = freeBoardService.selectFreeBoardList(freeBoardVo);
		
		model.addAttribute("pageVO", freeBoardVo);
		model.addAttribute("boardList", map.get("list"));
		model.addAttribute("search", freeBoardVo.getSearch());
		model.addAttribute("searchWord", map.get("searchWord"));
		
		return "board/free/freeBoard";
	}
	
	/**
	    * @Method freeBoardWrite
	    * @Date 2017. 11. 07.
	    * @Writter seohae
	    * @Param 
	    * @EditHistory
	    * @Discript 새글작성 페이지
	    * @Return String
	  */
	
	@RequestMapping(value="/freeBoardWrite")
	public String freeBoardWhite(@ModelAttribute("freeBoardVo") FreeBoardVo freeBoardVo, Model model){
		/*model.addAttribute("test", freeBoardVo); -> 대신 위 @ModelAttribute("test")로 하면 됨*/
		return "board/free/freeBoardWrite";
	}
	/**
	    * @Method freeBorWriteSave
	    * @Date 2017. 11. 07.
	    * @Writter seohae
	    * @Param 
	    * @EditHistory
	    * @Discript 새글작성 완료
	    * @Return String
	  */
	@RequestMapping(value="/freeBorWriteSave", method=RequestMethod.POST)
	public String freeBorWriteSave(@ModelAttribute("freeBoardVo") FreeBoardVo freeBoardVo, Model model, HttpServletResponse response, HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			response.sendRedirect("/login.do?GBN=SESSIONOUT");
		} else {
			if(session.getAttribute("userId") == null){
				response.sendRedirect("/login.do?GBN=SESSIONOUT");
			} else {
				freeBoardVo.setUserId((String) session.getAttribute("userId")); //작성자 아이디
				freeBoardVo.setUserNm((String) session.getAttribute("userNm")); //작성자 이름
				freeBoardService.freeBorWriteSave(freeBoardVo); 
				int freeBorIdx = freeBoardService.freeBoardIdx(); //글번호 가져오기
				freeBoardVo.setFreeBorIdx(freeBorIdx); //글번호 담기
				model.addAttribute("pageVO", freeBoardVo);
				model.addAttribute("search", freeBoardVo.getSearch());
				return "board/free/freeBoardDetail";
			}
		}
		
		return "";
	}
	
	/**
	    * @Method freeBoardDetail
	    * @Date 2017. 11. 07.
	    * @Writter seohae
	    * @Param 
	    * @EditHistory
	    * @Discript 새글작성 페이지
	    * @Return String
	  */
	
	@RequestMapping(value="/freeBoardDetail", method=RequestMethod.POST)
	public String freeBoardDetail(@ModelAttribute("freeBoardVo") FreeBoardVo freeBoardVo, Model model, HttpSession session){
		FreeBoardVo list = freeBoardService.freeBoardDetail(freeBoardVo);
		freeBoardService.freeBoardUpdateCnt(freeBoardVo, session); //조회수 증가
		list.setFreeBorContents(list.getFreeBorContents().replaceAll("<", "&lt;").replaceAll("&lt;br>", "<br>"));
		model.addAttribute("freeBoardVo", list);
		model.addAttribute("pageVO", freeBoardVo);
		model.addAttribute("search", freeBoardVo.getSearch());
		return "board/free/freeBoardDetail";
	}
	
	/**
	    * @Method freeBoardUpdateView
	    * @Date 2017. 11. 07.
	    * @Writter seohae
	    * @Param 
	    * @EditHistory
	    * @Discript 수정 페이지
	    * @Return String
	  */
	
	@RequestMapping(value="/freeBoardUpdateView", method=RequestMethod.POST)
	public String freeBoardUpdateView(@ModelAttribute FreeBoardVo freeBoardVo, Model model){
		FreeBoardVo list = freeBoardService.freeBoardUpdateView(freeBoardVo); //글 수정페이지에 데이터 뿌리기
		list.setFreeBorContents(list.getFreeBorContents().replaceAll("<", "&lt;").replaceAll("&lt;br>", "<br>"));
		model.addAttribute("freeBoardVo", list);
		model.addAttribute("pageVO", freeBoardVo);
		model.addAttribute("search", freeBoardVo.getSearch());
		return "board/free/freeBoardUpdate";
	}
	
	/**
	    * @Method freeBoardUpdate
	    * @Date 2017. 11. 07.
	    * @Writter seohae
	    * @Param 
	    * @EditHistory
	    * @Discript 수정 완료
	    * @Return String
	  */
	
	@RequestMapping(value="/freeBoardUpdate", method=RequestMethod.POST)
	public String freeBoardUpdate(@ModelAttribute("freeBoardVo") FreeBoardVo freeBoardVo, Model model, HttpServletResponse response, HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			response.sendRedirect("/login/login.do?GBN=SESSIONOUT");
		} else {
			if(session.getAttribute("userId") == null){
				response.sendRedirect("/login/login.do?GBN=SESSIONOUT");
			} else {
				freeBoardVo.setUserId((String) session.getAttribute("userId")); //수정자 
				freeBoardVo.setUserNm((String) session.getAttribute("userNm")); //수정자
				freeBoardService.freeBoardUpdate(freeBoardVo); //게시글 번호로, 게시글을 조회하여 update(수정)완료.
				model.addAttribute("pageVO", freeBoardVo); //수정된 데이터 담음. -> pageVO: view에서 사용
				model.addAttribute("search", freeBoardVo.getSearch()); //검색어를 담음 -> search: view에서 사용
				return "board/free/freeBoardDetail";
			}
		}
		
		return "";
	}
	
	/**
	    * @Method freeBoardUpdate
	    * @Date 2017. 11. 07.
	    * @Writter seohae
	    * @Param 
	    * @EditHistory
	    * @Discript 글 삭제
	    * @Return String
	  */
	
	@RequestMapping(value="/freeBoardDelete", method=RequestMethod.POST)
	@ResponseBody
	public String freeBoardDelete(@ModelAttribute FreeBoardVo freeBoardVo) throws IOException{
		freeBoardService.freeBoardReplyDelete(freeBoardVo); //삭제하려는 글의 댓글 삭제
		freeBoardService.freeBoardLikeDelete(freeBoardVo); //삭제하려는 글의 추천수 삭제
		freeBoardService.freeBoardDelete(freeBoardVo); //글 삭제
		
		return "ok";
	}
	
	/**
	    * @Method freeBoardViewCnt
	    * @Date 2017. 11. 07.
	    * @Writter seohae
	    * @Param 
	    * @EditHistory
	    * @Discript 조회수 증가
	    * @Return int
	  */
	
	@RequestMapping(value="/freeBoardViewCnt", method=RequestMethod.GET)
	@ResponseBody
	public int freeBoardViewCnt(@RequestParam int freeBorIdx) {
		int resultCnt = freeBoardService.freeBoardViewCnt(freeBorIdx); //조회수를 가져옴 
		return resultCnt;
	}
	
	/**
	    * @Method freeBoardLikeCnt
	    * @Date 2017. 11. 07.
	    * @Writter seohae
	    * @Param 
	    * @EditHistory
	    * @Discript 추천수 증가
	    * @Return int
	  */
	
	@RequestMapping(value="/freeBoardLikeCnt", method=RequestMethod.GET)
	@ResponseBody
	public int freeBoardLikeCnt(@RequestParam int freeBorIdx) {
		int resultCnt = freeBoardService.freeBoardLikeCnt(freeBorIdx); //추천수 가져옴
		return resultCnt;
	}
	
	/**
	    * @Method freeBoardLike
	    * @Date 2017. 11. 07.
	    * @Writter seohae
	    * @Param 
	    * @EditHistory
	    * @Discript 추천수 +1
	    * @Return String
	  */
	
	@RequestMapping(value="/freeBoardLike", method=RequestMethod.POST)
	@ResponseBody
	public String freeBoardLike(@ModelAttribute FreeBoardVo freeBoardVo, HttpSession session) throws IOException{
		freeBoardVo.setUserId((String) session.getAttribute("userId"));
		FreeBoardVo freeBoardLike = freeBoardService.freeBoardLike(freeBoardVo); //추천수 테이블 조회 : 추천을 했었다면, 값이 있고 추천을 안했었다면 null. -> 추천을 안했어야 추천가능.
		if(freeBoardLike == null){ //그 글에 추천을 했었는지, 안했었는지
			freeBoardService.insertFreeBoardLike(freeBoardVo); //추천수 증가시킴
			return "ok";
		}
		else return "fal";
	}
	
	/**
	    * @Method freeBoardListDelete
	    * @Date 2017. 11. 07.
	    * @Writter seohae
	    * @Param 
	    * @EditHistory
	    * @Discript 체크박스 체크된 글 모두삭제
	    * @Return void
	  */
	@RequestMapping(value="/freeBoardListDelete", method=RequestMethod.POST, consumes="application/json")	 
	public void freeBoardListDelete(@RequestBody List<FreeBoardVo> data) {
		freeBoardService.freeBoardListDelete(data);
	}
}
