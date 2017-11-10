package com.co.kr.admin.service;

import java.util.List;

import com.co.kr.bino.vo.BinoVo;
import com.co.kr.user.vo.UserVo;

public interface AdminService {

	public void allListDelete(List<UserVo> data) throws Exception;

	public UserVo memberDetail(UserVo userVo);

	public void memberDelete(UserVo userVo);

	public void memberDown(UserVo userVo);

	public void memberUp(UserVo userVo);

	List<UserVo> allMemberList() throws Exception;

	public List<BinoVo> binoList();
}
