package com.co.kr.user.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.kr.user.dao.UserDao;
import com.co.kr.user.service.UserService;
import com.co.kr.user.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {
	
	private Log log = LogFactory.getLog(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;

	@Override
	public UserVo createIdCheck(UserVo userVo) {
		return userDao.createIdCheck(userVo);
	}

	@Override
	public UserVo createEmailCheck(UserVo userVo) {
		return userDao.createEmailCheck(userVo);
	}

	@Override
	public void userCreateSuccess(UserVo userVo) {
		userDao.userCreateSuccess(userVo);
	}

	@Override
	public Object selectLastContactFmtDt(Object userId) {
		return userDao.selectLastContactFmtDt(userId);
	}
	
	@Override
	public UserVo findAccount(String email) {
		return userDao.findAccount(email);
	}

	@Override
	public void userChangePwUpdate(UserVo userVo) {
		userDao.userChangePwUpdate(userVo);
	}
	
	@Override
	public UserVo editUser(String userId) {
		return userDao.editUser(userId);
	}

	@Override
	public void editUserSave(UserVo userVo) {
		userDao.editUserSave(userVo);
	}
	
	@Override
	public UserVo CheckEmail(UserVo userVo) {
		return userDao.CheckEmail(userVo);
	}
	
	@Override
	public UserVo memberEmailCheck(UserVo userVo) {
		return userDao.memberEmailCheck(userVo);
	}
	
	/** 비밀번호 체크 */ 
	@Override
	public UserVo CheckPw(UserVo userVo) {
		return userDao.CheckPw(userVo);
	}
	
	/** 회원 탈퇴 */
	@Override
	public void memberDelete(UserVo userVo) {
		userDao.memberDelete(userVo);
	}
	
	/** 비밀번호 변경 */
	@Override
	public void pwUpdateOk(UserVo userVo) {
		userDao.pwUpdateOk(userVo);
	}
}
