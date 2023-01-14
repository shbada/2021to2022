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
import com.co.kr.bino.service.BinoService;
import com.co.kr.bino.vo.BinoVo;
import com.co.kr.message.service.MessageService;
import com.co.kr.message.vo.MessageVo;
import com.co.kr.notice.vo.NoticeVo;
import com.co.kr.question.vo.QuestionVo;
import com.co.kr.user.vo.UserVo;

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
	
	@Autowired
	private BinoService binoService;
	
	@Autowired
	private MessageService messageService;
	
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
		
		//작성 후 10 bino 적립!
		BinoVo binoVo = new BinoVo();
		binoVo.setBinoCg("답변글 작성");
		binoVo.setBinoYn("Y");
		binoVo.setUserId(userId);
		binoVo.setBino(10);
		binoService.questionBino(binoVo);
		//총 적립 포인트 조회
		int userBinoAdd = binoService.userBinoAdd(userId)+10;
		
		//사용자 테이블의 bino 총액 변경
		UserVo userVo = new UserVo();
		userVo.setUserId(userId);
		userVo.setBino(userBinoAdd);
		binoService.userBinoUpdate(userVo);
		
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
	public String AnswerDetail(@ModelAttribute AnswerVo answerVo, @RequestParam int aIdx, Model model,HttpSession session){
		Map<String, Object> map = answerService.answerDetail(aIdx);
		//질문글 작성자 아이디 가져오기
		String writer = answerService.findqUserId(answerVo);
		String qUserId = answerService.findqQUserId(answerVo);
		answerService.answerUpdateCnt(aIdx, session);
		model.addAttribute("writer", writer);
		model.addAttribute("qUserId", qUserId);
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
		} else return "fal";
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
	public int AnswerLikeCnt(@RequestParam int aIdx){
		System.out.println(">>>>>>>>>>>>>>>>>>>>>"+aIdx);
		int resultCnt = answerService.answerLikeCnt(aIdx);
		System.out.println(resultCnt);
		return resultCnt;
	}
	
	/**
	    * @Method AnswerPick
	    * @Date 2017. 11. 10.
	    * @Writter seohae
	    * @Param AnswerVo
	    * @Discript 답변글 채택완료
	    * @Return String
	  */
	@RequestMapping(value="answerPick", method=RequestMethod.POST)
	@ResponseBody
	public String AnswerPick(@ModelAttribute AnswerVo answerVo, HttpSession session, @RequestParam String aUserId) throws IOException{
		System.out.println(aUserId+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		//채택된 답변이 있는지 확인
		int answerPickCheck = answerService.answerPickCheck(answerVo);
		if(answerPickCheck == 0){
			answerService.answerPickSave(answerVo);
			//질문글: 채택여부 변경하기
			answerService.questionUpdate(answerVo);
			//채택된 답변 작성자: 20 bino 적립
			String userId = aUserId;
			BinoVo binoVo = new BinoVo();
			binoVo.setBinoCg("답변글 채택");
			binoVo.setBinoYn("Y");
			binoVo.setUserId(userId);
			binoVo.setBino(20);
			binoService.questionBino(binoVo);
			//총 적립 포인트 조회
			int userBinoAdd = binoService.userBinoAdd(userId)+20;
			
			//사용자 테이블의 bino 총액 변경
			UserVo userVo = new UserVo();
			userVo.setUserId(userId);
			userVo.setBino(userBinoAdd);
			binoService.userBinoUpdate(userVo);
			
			//채택된 답글의 작성자에게 쪽지로 채택됬음을 알려주기
			MessageVo messageVo = new MessageVo();
			messageVo.setMsgGet(userId);
			messageVo.setMsgSend("admin");
			messageVo.setMsgName("회원님의 답변글이 채택되셨습니다.");
			messageVo.setMsgDesc("축하드립니다! 20 bino의 적립이 완료되었습니다.");
			
			System.out.println("@@@@@"+messageVo);
			messageService.MessageWriteSave(messageVo);
			
			return "ok";
		} else return "fal";
	}
}
