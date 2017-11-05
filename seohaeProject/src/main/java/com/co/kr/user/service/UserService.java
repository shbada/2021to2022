package com.co.kr.user.service;

import com.co.kr.user.vo.UserVo;

public interface UserService {

	public UserVo createIdCheck(UserVo userVo);

	public UserVo createEmailCheck(UserVo userVo);

	public void userCreateSuccess(UserVo userVo);

	public Object selectLastContactFmtDt(Object userId);

	public UserVo findAccount(String email);

	public void userChangePwUpdate(UserVo userVo);
	
	public UserVo editUser(String userId);

	public void editUserSave(UserVo userVo);

	public UserVo CheckEmail(UserVo userVo);

	public UserVo memberEmailCheck(UserVo userVo);
	
	void memberDelete(UserVo userVo);

	void pwUpdateOk(UserVo userVo);
	
	UserVo CheckPw(UserVo userVo);
}
