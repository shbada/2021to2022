package com.co.kr.answer.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.co.kr.answer.service.AnswerService;
import com.co.kr.answer.vo.AnswerVo;
import com.co.kr.notice.vo.NoticeVo;
import com.co.kr.question.vo.QuestionVo;

/**
 * AnswerController
 * 
 * @author 김서해
 * @since 2017. 11. 10.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>

 * 1. (2017. 11. 10 / seohae / 최초생성)
 *
 * </pre>
 */

@Controller
public class AnswerController {
	
	private Log log = LogFactory.getLog(AnswerController.class);
	
	@Autowired
	AnswerService answerService;
	
	/**
	    * @Method answerWrite
	    * @Date 2017. 11. 10.
	    * @Writter seohae
	    * @Param QuestionVo
	    * @Discript 자바 답변글 작성
	    * @Return String
	  */
	
	@RequestMapping(value="/answerWrite", method=RequestMethod.POST)
	public String BookList(@ModelAttribute("questionVo") QuestionVo questionVo, Model model) {
		System.out.println(questionVo);
		model.addAttribute("questionVo", questionVo);
		return "question/answer/answerWrite";
	}
	
	/**
	    * @Method qJavaList
	    * @Date 2017. 11. 10.
	    * @Writter seohae
	    * @Param QuestionVo
	    * @Discript 자바 질문과답변 작성완료
	    * @Return String
	  */
	@RequestMapping(value="/insertAnswer",method=RequestMethod.POST)
	public String InsertQuestion(@ModelAttribute AnswerVo answerVo, HttpServletRequest request, HttpSession session) throws Exception{
		log.info(answerVo);
		String userId = (String)session.getAttribute("userId");
		answerVo.setaUserId(userId);
  
		answerService.insertAnswer(answerVo);
		
	    return "redirect:/qJavaList.do";
	}
	
	/**
	    * @Method AnswerDetail
	    * @Date 2017. 11. 10.
	    * @Writter seohae
	    * @Param QuestionVo
	    * @Discript 답변글 상세정보
	    * @Return String
	  */
	
	@RequestMapping(value="/answerDetail")
	public String AnswerDetail(@ModelAttribute AnswerVo AnswerVo, @RequestParam int aIdx, Model model,HttpSession session){
		Map<String, Object> map = answerService.answerDetail(aIdx);
		answerService.answerUpdateCnt(aIdx,session);
		model.addAttribute("detail",map.get("detail"));
		model.addAttribute("list",map.get("list"));
	 return "/question/answer/answerDetail";	
	}
	
	/**
	    * @Method AnswerLike
	    * @Date 2017. 11. 10.
	    * @Writter seohae
	    * @Param QuestionVo
	    * @Discript 답변글 추천수 조회(추천한적 없는 회원이면 추천수 증가)
	    * @Return String
	  */
	@RequestMapping(value="answerLike", method=RequestMethod.POST)
	@ResponseBody
	public String AnswerLike(@ModelAttribute AnswerVo answerVo, HttpSession session) throws IOException{
		answerVo.setaUserId((String)session.getAttribute("userId"));
		AnswerVo answerLike = answerService.answerLike(answerVo);
		if(answerLike == null){
			System.out.println("@");
			answerService.insertAnswerLike(answerVo);
			return "ok";
		}else return "false";
	}
	
	/**
	    * @Method AnswerLikeCnt
	    * @Date 2017. 11. 10.
	    * @Writter seohae
	    * @Param QuestionVo
	    * @Discript 추천수 개수 가져오기
	    * @Return String
	  */
	@ResponseBody  
	@RequestMapping(value="answerLikeCnt", method=RequestMethod.GET)
	public int AnswerLikeCnt(@RequestParam int qIdx){
		int resultCnt = answerService.answerLikeCnt(qIdx);
		return resultCnt;
	}
}
