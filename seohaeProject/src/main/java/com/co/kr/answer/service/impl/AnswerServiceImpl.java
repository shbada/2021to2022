package com.co.kr.answer.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.kr.answer.dao.AnswerDao;
import com.co.kr.answer.service.AnswerService;
import com.co.kr.answer.vo.AnswerVo;
import com.co.kr.notice.vo.NoticeVo;
import com.co.kr.question.vo.QuestionVo;


@Service
public class AnswerServiceImpl implements AnswerService{
	
	@Autowired
	private AnswerDao answerDao;

	@Override
	public void insertAnswer(AnswerVo answerVo) {
		answerDao.insertAnswer(answerVo);
	}

	@Override
	public Map<String, Object> answerDetail(int aIdx) {
		Map<String, Object> resultMap = new HashMap<String,Object>();
		AnswerVo detail = answerDao.answerDetail(aIdx);
	    resultMap.put("detail", detail);
		return resultMap;
	}

	@Override
	public void answerUpdateCnt(int aIdx, HttpSession session) {
		long viewTime = 0;
		
		// 세션에 저장된 조회 시간
		if(session.getAttribute("viewTime" + aIdx) != null){
			viewTime = (Long)session.getAttribute("viewTime" + aIdx);
		}
		
		// 시스템의 현재 시간
		long nowTime = System.currentTimeMillis();
		System.out.println("sadjfkladsjfkladsjfklasdjflkasdjflk"+nowTime);
		// 셋팅된 시간이 경과 후 조회수 증가(60초 후 조회수 증가)
		if(nowTime - viewTime > 60*1000){
			answerDao.answerUpdateCnt(aIdx);
			session.setAttribute("viewTime" + aIdx, nowTime);
		}
	}

	@Override
	public AnswerVo answerLike(AnswerVo answerVo) {
		return answerDao.answerLike(answerVo);
	}

	@Override
	public void insertAnswerLike(AnswerVo answerVo) {
		answerDao.insertAnswerLike(answerVo);
	}

	@Override
	public int answerLikeCnt(int aIdx) {
		return answerDao.answerLikeCnt(aIdx);
	}

	@Override
	public String findqUserId(AnswerVo answerVo) {
		return answerDao.findqUserId(answerVo);
	}

	@Override
	public int answerPickCheck(AnswerVo answerVo) {
		return answerDao.answerPickCheck(answerVo);
	}

	@Override
	public void answerPickSave(AnswerVo answerVo) {
		answerDao.answerPickSave(answerVo);
	}

	@Override
	public void questionUpdate(AnswerVo answerVo) {
		answerDao.questionUpdate(answerVo);
	}
	
}
