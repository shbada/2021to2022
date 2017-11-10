package com.co.kr.question.controller;

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

import com.co.kr.question.service.QuestionService;
import com.co.kr.question.vo.QuestionVo;

/**
 * QuestionController
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
public class QuestionController {
	
	private Log log = LogFactory.getLog(QuestionController.class);
	
	@Autowired
	QuestionService questionService;
	
	/**
	    * @Method qJavaList
	    * @Date 2017. 11. 10.
	    * @Writter seohae
	    * @Param QuestionVo
	    * @Discript 자바 질문과답변 리스트
	    * @Return String
	  */

	@RequestMapping(value = "/qJavaList")
	public String QJavaList(@ModelAttribute QuestionVo questionVo, Model model) {
		int totalCount= questionService.getListBaordJavaCount(questionVo);
		
		if(questionVo.getPageCnt()==null) questionVo.setPageSize(10);
	    else questionVo.setPageSize(Integer.parseInt(questionVo.getPageCnt()));
	      
		questionVo.setTotalCount(totalCount);
	    model.addAttribute("pageVO", questionVo);
	    
		model.addAttribute("questionList", questionService.selectJavaList(questionVo));
		return "/question/questionList";
	}
	
	/**
	    * @Method QuestionWrite
	    * @Date 2017. 11. 10.
	    * @Writter seohae
	    * @Param QuestionVo
	    * @Discript 자바 질문과답변 글쓰기
	    * @Return String
	  */
	
	@RequestMapping(value="/questionWrite")
	public String QuestionWrite(){
		return "/question/questionWrite";
	}
	
	/**
	    * @Method qJavaList
	    * @Date 2017. 11. 10.
	    * @Writter seohae
	    * @Param QuestionVo
	    * @Discript 자바 질문과답변 작성완료
	    * @Return String
	  */
	@RequestMapping(value="/insertQuestion",method=RequestMethod.POST)
	public String InsertQuestion(@ModelAttribute QuestionVo QuestionVo, HttpServletRequest request, HttpSession session) throws Exception{
		String userId = (String)session.getAttribute("userId");
		QuestionVo.setqUserId(userId);
     
		questionService.insertQuestion(QuestionVo, request);
		
	    return "redirect:/qJavaList.do";
	}
	
	/**
	    * @Method QuestionDetail
	    * @Date 2017. 11. 10.
	    * @Writter seohae
	    * @Param QuestionVo
	    * @Discript 자바 질문과답변 상세정보
	    * @Return String
	  */
	
	@RequestMapping(value="/questionDetail")
	public String QuestionDetail(@ModelAttribute QuestionVo questionVo, @RequestParam int qIdx, Model model,HttpSession session){
		Map<String, Object> map = questionService.questionDetail(qIdx);
		model.addAttribute("answerList", questionService.selectAnswerList(qIdx));
		questionService.JavaUpdateCnt(qIdx,session);
		//채택된 답변이 있는지 확인
		int answerPickCheck = questionService.answerPickCheck(qIdx);
		//채택된 답변의 정보 가져오기
		Map<String, Object> map2 = questionService.ansPickDetail(qIdx);
		
		model.addAttribute("answerPickCheck", answerPickCheck);
		model.addAttribute("pickDetail", map2.get("pickDetail"));
		model.addAttribute("detail",map.get("detail"));
		model.addAttribute("list",map.get("list"));
	 return "/question/questionDetail";	
	}
	
	
	/**
	    * @Method QuestionViewCnt
	    * @Date 2017. 11. 10.
	    * @Writter seohae
	    * @Param QuestionVo
	    * @Discript 자바 질문과답변 조회수 가져오기
	    * @Return String
	  */
	@ResponseBody
	@RequestMapping(value="questionViewCnt")
	public int QuestionViewCnt(@RequestParam int qIdx){
		int resultCnt = questionService.questionViewCnt(qIdx);
		return resultCnt;
	}
}
