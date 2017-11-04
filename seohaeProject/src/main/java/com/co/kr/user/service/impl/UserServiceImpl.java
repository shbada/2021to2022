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
}
