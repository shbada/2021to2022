package com.co.kr.answer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.kr.answer.dao.AnswerDao;
import com.co.kr.answer.service.AnswerService;


@Service
public class AnswerServiceImpl implements AnswerService{
	
	@Autowired
	private AnswerDao answerDao;
	
}
