package com.co.kr.login.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.co.kr.common.dao.AbstractDAO;
import com.co.kr.login.vo.LoginVo;

@Repository
public class LoginDao extends AbstractDAO{
	
	/** 로그인 체크 */
	public LoginVo selectUserLoginCheck(LoginVo loginVo) {
		return (LoginVo)selectOne("loginSql.selectUserLoginCheck",loginVo);
	}

}
