package com.co.kr.oneto.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.co.kr.common.dao.AbstractDAO;
import com.co.kr.oneto.one.vo.OneVo;

@Repository
public class OneAdminDao extends AbstractDAO{

	/** 문의내역 총 개수 구하기 */
	public int oneAdminListCount(OneVo oneVo) {
		return (int)selectOne("oneAdminSql.oneAdminListCount", oneVo);
	}
	
	/** 모든회원이 작성한 문의내역글 가져오기 */
	public List<OneVo> oneAdminList(OneVo oneVo) {
		return selectList("oneAdminSql.oneAdminList", oneVo);
	}
	
	/** 문의내역글 상세페이지 */
	public OneVo oneAdminDetail(OneVo oneVo) {
		return (OneVo)selectOne("oneAdminSql.oneAdminDetail", oneVo);
	}
	
	/** 문의글 답변글 쓰기 */
	public void oneAdminWriteSave(OneVo oneVo) {
		insert("oneAdminSql.oneAdminWriteSave", oneVo);
	}
	
	/** 해당 글의 답변글 가져오기 */
	public List<OneVo> oneAdminListRe(OneVo oneVo) {
		return selectList("oneAdminSql.oneAdminListRe", oneVo);
	}

	public void oneYnUpdate(OneVo oneVo) {
		update("oneAdminSql.oneYnUpdate",oneVo);
	}
	
}
