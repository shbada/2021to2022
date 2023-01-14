package com.co.kr.bino.service;

import java.util.List;

import com.co.kr.bino.vo.BinoVo;
import com.co.kr.question.vo.QuestionVo;
import com.co.kr.user.vo.UserVo;

public interface BinoService {

	int binoSumCount(String user_id);

	void questionBino(BinoVo binoVo);

	int userBinoAdd(String userId);

	int userBinoMinus(String userId);

	void userBinoUpdate(UserVo userVo);

	List<BinoVo> binoList(BinoVo binoVo);
	
}
