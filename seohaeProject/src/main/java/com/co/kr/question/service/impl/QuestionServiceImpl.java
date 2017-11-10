package com.co.kr.question.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.kr.answer.vo.AnswerVo;
import com.co.kr.question.dao.QuestionDao;
import com.co.kr.question.service.QuestionService;
import com.co.kr.question.vo.QuestionVo;


@Service
public class QuestionServiceImpl implements QuestionService{
	
	@Autowired
	private QuestionDao questionDao;

	@Override
	public int getListBaordJavaCount(QuestionVo questionVo) {
		return questionDao.getListBaordJavaCount(questionVo);
	}

	@Override
	public List<QuestionVo> selectJavaList(QuestionVo questionVo) {
		return questionDao.selectJavaList(questionVo);
	}

	@Override
	public Map<String, Object> questionDetail(int qIdx) {
		Map<String, Object> resultMap = new HashMap<String,Object>();
		QuestionVo detail = questionDao.questionDetail(qIdx);
	    resultMap.put("detail", detail);
		return resultMap;
	}

	@Override
	public void JavaUpdateCnt(int qIdx, HttpSession session) {
		long viewTime = 0;
		
		// 세션에 저장된 조회 시간
		if(session.getAttribute("viewTime" + qIdx) != null){
			viewTime = (Long)session.getAttribute("viewTime" + qIdx);
		}
		
		// 시스템의 현재 시간
		long nowTime = System.currentTimeMillis();
		System.out.println("sadjfkladsjfkladsjfklasdjflkasdjflk"+nowTime);
		// 셋팅된 시간이 경과 후 조회수 증가(60초 후 조회수 증가)
		if(nowTime - viewTime > 60*1000){
			questionDao.JavaUpdateCnt(qIdx);
			session.setAttribute("viewTime" + qIdx, nowTime);
		}
	}

	@Override
	public void insertQuestion(QuestionVo questionVo, HttpServletRequest request) {
		questionDao.insertQuestion(questionVo);
	    
	}

	@Override
	public int questionViewCnt(int qIdx) {
		return questionDao.questionViewCnt(qIdx);
	}

	@Override
	public List<QuestionVo> selectAnswerList(int qIdx) {
		return questionDao.selectAnswerList(qIdx);
	}

	@Override
	public int answerPickCheck(int qIdx) {
		return questionDao.answerPickCheck(qIdx);
	}

	@Override
	public Map<String, Object> ansPickDetail(int qIdx) {
		Map<String, Object> resultMap = new HashMap<String,Object>();
		AnswerVo pickDetail = questionDao.ansPickDetail(qIdx);
	    resultMap.put("pickDetail", pickDetail);
		return resultMap;
	}
	
}
