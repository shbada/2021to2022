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

	public void keepLogin(String userId, String id, Date sessionLimit) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("user_id", userId);
		paramMap.put("sessionId", id);
		paramMap.put("next", sessionLimit);
		update("loginSql.keepLogin", paramMap);
	}

}
