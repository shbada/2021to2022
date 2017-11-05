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
	
	/** 이메일 _ 아이디 찾기 */
	public UserVo findAccount(String email) {
		return (UserVo) selectOne("mailSql.findAccount", email);
	}
	
	/** 비밀번호 변경 */
	public void userChangePwUpdate(UserVo userVo) {
		update("userSql.userChangePwUpdate", userVo);
	}
	
	/** 회원정보 조회 */
	public UserVo editUser(String userId) {
		return (UserVo)selectOne("userSql.editUser",userId);
	}

	/** 회원정보 수정완료 */
	public void editUserSave(UserVo userVo) {
		update("userSql.editUserSave", userVo);
	}
	
	/** 변경할 이메일이 원래 이메일과 같은지 확인 */
	public UserVo CheckEmail(UserVo userVo) {
		return (UserVo) selectOne("userSql.CheckEmail", userVo);
	}
	
	/** 변경할 이메일의 중복체크 */
	public UserVo memberEmailCheck(UserVo userVo) {
		return (UserVo) selectOne("userSql.memberEmailCheck", userVo);
	}
	
	/** 비밀번호 체크 */ 
	public UserVo CheckPw(UserVo userVo) {
		return (UserVo)selectOne("userSql.CheckPw", userVo);
	}
	
	/** 회원 탈퇴 */
	public void memberDelete(UserVo userVo) {
		update("userSql.memberDelete", userVo);
	}
	
	/** 비밀번호 변경 */
	public void pwUpdateOk(UserVo userVo) {
		update("userSql.pwUpdateOk", userVo);
	}

}
