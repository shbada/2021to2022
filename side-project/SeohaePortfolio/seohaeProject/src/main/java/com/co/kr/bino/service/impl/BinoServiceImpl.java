package com.co.kr.bino.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.kr.bino.dao.BinoDao;
import com.co.kr.bino.service.BinoService;
import com.co.kr.bino.vo.BinoVo;
import com.co.kr.question.vo.QuestionVo;
import com.co.kr.user.vo.UserVo;


@Service
public class BinoServiceImpl implements BinoService{
	
	@Autowired
	private BinoDao binoDao;

	@Override
	public int binoSumCount(String user_id) {
		return binoDao.binoSumCount(user_id);
	}

	@Override
	public void questionBino(BinoVo binoVo) {
		binoDao.questionBino(binoVo);
	}

	@Override
	public int userBinoAdd(String userId) {
		return binoDao.userBinoAdd(userId);
	}

	@Override
	public int userBinoMinus(String userId) {
		return binoDao.userBinoMinus(userId);
	}

	@Override
	public void userBinoUpdate(UserVo userVo) {
		binoDao.userBinoUpdate(userVo);
	}

	@Override
	public List<BinoVo> binoList(BinoVo binoVo) {
		return binoDao.binoList(binoVo);
	}
	
}
