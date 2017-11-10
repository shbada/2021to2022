package com.co.kr.question.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.co.kr.common.dao.AbstractDAO;
import com.co.kr.notice.vo.NoticeVo;
import com.co.kr.question.vo.QuestionVo;

@Repository
public class QuestionDao extends AbstractDAO{

	public int getListBaordJavaCount(QuestionVo questionVo) {
		return (int)selectOne("questionSql.getListBaordJavaCount", questionVo);
	}

	public List<QuestionVo> selectJavaList(QuestionVo questionVo) {
		return selectList("questionSql.selectJavaList", questionVo);
	}

	public QuestionVo questionDetail(int qIdx) {
		return (QuestionVo) selectOne("questionSql.questionDetail",qIdx);
	}

	public void JavaUpdateCnt(int qIdx) {
		update("questionSql.JavaUpdateCnt", qIdx);
		
	}

	public void insertQuestion(QuestionVo questionVo) {
		insert("questionSql.insertQuestion", questionVo);
	}

	public int questionViewCnt(int qIdx) {
		return (int) selectOne("questionSql.questionViewCnt", qIdx);
	}

	public List<QuestionVo> selectAnswerList(int qIdx) {
		return selectList("questionSql.selectAnswerList", qIdx);
	}

}
