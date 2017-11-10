package com.co.kr.answer.dao;

import org.springframework.stereotype.Repository;

import com.co.kr.answer.vo.AnswerVo;
import com.co.kr.common.dao.AbstractDAO;

@Repository
public class AnswerDao extends AbstractDAO{

	public void insertAnswer(AnswerVo answerVo) {
		insert("answerSql.insertAnswer", answerVo);
	}

	public AnswerVo answerDetail(int aIdx) {
		return (AnswerVo) selectOne("answerSql.answerDetail",aIdx);
	}

	public void answerUpdateCnt(int aIdx) {
		update("answerSql.answerUpdateCnt", aIdx);
	}

	public AnswerVo answerLike(AnswerVo answerVo) {
		return (AnswerVo) selectOne("answerSql.answerLike", answerVo);
	}

	public void insertAnswerLike(AnswerVo answerVo) {
		insert("answerSql.insertAnswerLike", answerVo);
	}

	public int answerLikeCnt(int aIdx) {
		return (int) selectOne("answerSql.answerLikeCnt", aIdx);
	}

	public String findqUserId(AnswerVo answerVo) {
		return (String) selectOne("answerSql.findqUserId", answerVo);
	}

	public int answerPickCheck(AnswerVo answerVo) {
		return (int) selectOne("answerSql.answerPickCheck", answerVo);
	}

	public void answerPickSave(AnswerVo answerVo) {
		update("answerSql.answerPickSave", answerVo);
	}

	public void questionUpdate(AnswerVo answerVo) {
		update("answerSql.questionUpdate", answerVo);
	}

}
