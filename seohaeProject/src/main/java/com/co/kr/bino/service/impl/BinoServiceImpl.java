package com.co.kr.bino.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.kr.bino.dao.BinoDao;
import com.co.kr.bino.service.BinoService;


@Service
public class BinoServiceImpl implements BinoService{
	
	@Autowired
	private BinoDao binoDao;

	@Override
	public int binoSumCount(String user_id) {
		return binoDao.binoSumCount(user_id);
	}
	
}
