package com.co.kr.bino.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.co.kr.bino.vo.BinoVo;
import com.co.kr.common.dao.AbstractDAO;
import com.co.kr.question.vo.QuestionVo;
import com.co.kr.user.vo.UserVo;

@Repository
public class BinoDao extends AbstractDAO{

	public int binoSumCount(String user_id) {
		return (int)selectOne("binoSql.binoSumCount", user_id);
	}

	public void questionBino(BinoVo binoVo) {
		insert("binoSql.questionBino", binoVo);
	}

	public int userBinoAdd(String userId) {
		return (int)selectOne("binoSql.userBinoAdd", userId);
	}

	public int userBinoMinus(String userId) {
		return (int)selectOne("binoSql.userBinoMinus", userId);
	}

	public void userBinoUpdate(UserVo userVo) {
		update("binoSql.userBinoUpdate", userVo);
	}

	public List<BinoVo> binoList(BinoVo binoVo) {
		return selectList("binoSql.binoList", binoVo);
	}

}
