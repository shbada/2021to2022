package com.co.kr.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.co.kr.bino.vo.BinoVo;
import com.co.kr.buy.vo.BuyVo;
import com.co.kr.common.dao.AbstractDAO;
import com.co.kr.user.vo.UserVo;

@Repository
public class AdminDao extends AbstractDAO{
	
	public List<UserVo> allMemberList() throws Exception {
		return selectList("adminSql.allMemberList");
	}
	
	public void allListDelete(UserVo userVo) throws Exception {
		update("adminSql.allListDelete", userVo);
		
	}
	
	public UserVo memberDetail(UserVo userVo) {
		return (UserVo) selectOne("adminSql.memberDetail", userVo);
	}

	public void memberDelete(UserVo userVo) {
		update("adminSql.memberDelete", userVo);
	}

	public void memberDown(UserVo userVo) {
		update("adminSql.memberDown", userVo);
	}

	public void memberUp(UserVo userVo) {
		update("adminSql.memberUp", userVo);
	}

	public List<BinoVo> binoList() {
		return selectList("adminSql.binoList");
	}

	public List<BuyVo> allBuyList() {
		return selectList("adminSql.allBuyList");
	}

}
