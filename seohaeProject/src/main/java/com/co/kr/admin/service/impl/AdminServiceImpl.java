package com.co.kr.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.kr.admin.dao.AdminDao;
import com.co.kr.admin.service.AdminService;
import com.co.kr.bino.vo.BinoVo;
import com.co.kr.user.vo.UserVo;


@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDao adminDao;
	
	@Override
	public List<UserVo> allMemberList() throws Exception {
		return adminDao.allMemberList();
	}

	@Override
	public void allListDelete(List<UserVo> data) throws Exception {
		for(int i=0; i<data.size(); i++)
			adminDao.allListDelete(data.get(i));
		
	}
	@Override
	public UserVo memberDetail(UserVo userVo) {
		return adminDao.memberDetail(userVo);
	}

	@Override
	public void memberDelete(UserVo userVo) {
		adminDao.memberDelete(userVo);
	}

	@Override
	public void memberDown(UserVo userVo) {
		adminDao.memberDown(userVo);
	}

	@Override
	public void memberUp(UserVo userVo) {
		adminDao.memberUp(userVo);
	}

	@Override
	public List<BinoVo> binoList() {
		return adminDao.binoList();
	}
	
}
