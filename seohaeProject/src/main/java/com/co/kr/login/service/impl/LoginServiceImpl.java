package com.co.kr.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.kr.login.dao.LoginDao;
import com.co.kr.login.service.LoginService;
import com.co.kr.login.vo.LoginVo;


@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private LoginDao loginDao;
	
	/** 로그인 체크 */
	@Override
	public LoginVo selectUserLoginCheck(LoginVo loginVo) {
		return loginDao.selectUserLoginCheck(loginVo);
	}
	
}
