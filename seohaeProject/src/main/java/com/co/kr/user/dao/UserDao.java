package com.co.kr.user.dao;

import org.springframework.stereotype.Repository;

import com.co.kr.common.dao.AbstractDAO;
import com.co.kr.user.vo.UserVo;

@Repository
public class UserDao extends AbstractDAO {
	
	/** 중복 아이디 체크 */
	public UserVo createIdCheck(UserVo userVo) {
		return (UserVo)selectOne("userSql.createIdCheck", userVo);
	}
	
	/** 중복 이메일 체크 */
	public UserVo createEmailCheck(UserVo userVo) {
		return (UserVo)selectOne("userSql.createEmailCheck", userVo);
	}
	
	/** 회원가입 성공 */
	public void userCreateSuccess(UserVo userVo) {
		insert("userSql.userCreateSuccess", userVo);
	}
	
	/** 최근 접속시간 조회 */
	public Object selectLastContactFmtDt(Object userId) {
		return selectOne("userSql.selectLastContactFmtDt", userId);
	}
	
	/**  */
	public UserVo findAccount(String email) {
		return (UserVo) selectOne("userSql.findAccount", email);
	}
	
	/** 비밀번호 변경 */
	public void userChangePwUpdate(UserVo userVo) {
		update("userSql.userChangePwUpdate", userVo);
	}

}
