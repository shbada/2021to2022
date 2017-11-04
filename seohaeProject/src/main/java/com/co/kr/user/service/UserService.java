package com.co.kr.user.service;

import com.co.kr.user.vo.UserVo;

public interface UserService {

	public UserVo createIdCheck(UserVo userVo);

	public UserVo createEmailCheck(UserVo userVo);

	public void userCreateSuccess(UserVo userVo);

	public Object selectLastContactFmtDt(Object userId);

	public UserVo findAccount(String email);

	public void userChangePwUpdate(UserVo userVo);
}
