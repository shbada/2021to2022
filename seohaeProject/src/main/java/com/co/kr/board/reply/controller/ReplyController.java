/**
 * Reply Controller
 * 
 * @author 김서해
 * @since 2017. 06. 11. 오후 3:50:00
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  수정일          		수정자           		  	수정내용
 *  -------        --------    --------------------------
 * 2017. 06. 11.    김서해              			최초생성
 *
 * </pre>
 */

package com.co.kr.board.reply.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.co.kr.board.reply.service.ReplyService;
import com.co.kr.board.reply.vo.ReplyVo;

/**
 * ReplyController
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

@Controller
@RequestMapping(value="/reply")
public class ReplyController {
	
	private Log log = LogFactory.getLog(ReplyController.class);
	
	@Autowired
	public ReplyService replyService;
	
	/**
	    * @Method freeBoardWrite
	    * @Date 2017. 11.07
	    * @Writter seohae
	    * @Param 
	    * @EditHistory
	    * @Discript 댓글쓰기
	    * @Return void
	  */
	
	@RequestMapping(value="/freeBoardReplyWrite", method=RequestMethod.POST)
	@ResponseBody
	public void freeBoardReplyWrite(@ModelAttribute ReplyVo replyVo, HttpSession session){
		replyVo.setReplyer((String) session.getAttribute("userNm"));
		replyVo.setUserId((String) session.getAttribute("userId"));
		replyService.freeBoardReplyWrite(replyVo);
	} 
	
	/**
	    * @Method freeBoardJsonListReply
	    * @Date 2017. 11.07
	    * @Writter seohae
	    * @Param 
	    * @EditHistory
	    * @Discript 댓글목록 (JSON)
	    * @Return List<ReplyVo>
	  */
	
	@RequestMapping(value="/freeBoardJsonListReply", method=RequestMethod.POST)
	public List<ReplyVo> freeBoardJsonListReply(@ModelAttribute ReplyVo replyVo, Model model, HttpSession session){
		List<ReplyVo> replyList = replyService.freeBoardJsonListReply(replyVo);
		return replyList;
	}
	
	/**
	    * @Method freeBoardListReply
	    * @Date 2017. 11.07
	    * @Writter seohae
	    * @Param 
	    * @EditHistory
	    * @Discript 댓글목록
	    * @Return String
	  */
	
	@RequestMapping(value="/freeBoardListReply", method=RequestMethod.POST)
	public String freeBoardListReply(@ModelAttribute ReplyVo replyVo, Model model, HttpSession session){
		int totalCount = replyService.freeBoardListReplyCount(replyVo); //총 댓글 수를 구한다
		
		if(replyVo.getPageCnt() == null) replyVo.setPageSize(5);
		else replyVo.setPageSize(Integer.parseInt(replyVo.getPageCnt()));
		replyVo.setTotalCount(totalCount);
		model.addAttribute("pageVO", replyVo);
		model.addAttribute("freeBorIdx", replyVo);

		List<ReplyVo> replyList = replyService.freeBoardListReply(replyVo, session);

		model.addAttribute("replyList", replyList);
		
		return "board/reply/replyList";
	}
	
	/**
	    * @Method replyOneDelete
	    * @Date 2017. 11.07
	    * @Writter seohae
	    * @Param 
	    * @EditHistory
	    * @Discript 댓글 삭제(ajax사용)
	    * @Return ResponseEntity<String>
	  */
	
	@RequestMapping(value="/replyOneDelete", method=RequestMethod.PATCH)
	@ResponseBody //ajax 사용했으므로 반드시 넣어준다
	public ResponseEntity<String> replyOneDelete(@RequestParam int freeBorReplyIdx){ //RequestParam은 파라미터 1개일때 
		ResponseEntity<String> entity = null;
		try{
			replyService.replyOneDelete(freeBorReplyIdx);
			entity = new ResponseEntity<String>("ok", HttpStatus.OK); //엔티티에 무언가 새로운걸 담는다. 성공하면 OK를 담음
		} catch (Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST); //에러 발생시 
		}
		return entity; //성공하면 entity=ok
	}
	
	/**
	    * @Method replyOneUpdate
	    * @Date 2017. 11.07
	    * @Writter seohae
	    * @Param 
	    * @EditHistory
	    * @Discript 댓글 수정(ajax)
	    * @Return String
	  */
	
	@RequestMapping(value="/replyOneUpdate", method=RequestMethod.PUT)
	@ResponseBody //ajax 사용했으므로 반드시 넣어준다
	public String replyOneUpdate(@RequestParam int freeBorReplyIdx, @RequestParam String replyText, @RequestParam String secretReply){ //RequestParam은 파라미터 1개일때 
		ReplyVo replyVo = new ReplyVo();
		replyVo.setFreeBorReplyIdx(freeBorReplyIdx);
		replyVo.setReplyText(replyText);
		replyVo.setSecretReply(secretReply);
		replyService.replyOneUpdate(replyVo);
		
		return "ok";
	}
}
