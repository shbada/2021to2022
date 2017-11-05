package com.co.kr.login.service;

import java.util.Date;

import com.co.kr.login.vo.LoginVo;

public interface LoginService {
	
	/** 로그인 체크 */
	LoginVo selectUserLoginCheck(LoginVo loginVo);

}
