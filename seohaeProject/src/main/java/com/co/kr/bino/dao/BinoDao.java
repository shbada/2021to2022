package com.co.kr.bino.dao;

import org.springframework.stereotype.Repository;

import com.co.kr.common.dao.AbstractDAO;

@Repository
public class BinoDao extends AbstractDAO{

	public int binoSumCount(String user_id) {
		return (int)selectOne("binoSql.binoSumCount", user_id);
	}

}
