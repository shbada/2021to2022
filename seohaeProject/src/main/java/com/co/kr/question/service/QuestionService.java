package com.co.kr.question.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.co.kr.question.vo.QuestionVo;

public interface QuestionService {

	int getListBaordJavaCount(QuestionVo questionVo);

	List<QuestionVo> selectJavaList(QuestionVo questionVo);

	Map<String, Object> questionDetail(int qIdx);

	void JavaUpdateCnt(int qIdx, HttpSession session);

	void insertQuestion(QuestionVo questionVo, HttpServletRequest request);

	int questionViewCnt(int qIdx);

	List<QuestionVo> selectAnswerList(int qIdx);
	
}
