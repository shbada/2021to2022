package com.co.kr.oneto.one.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.co.kr.common.dao.AbstractDAO;
import com.co.kr.oneto.one.vo.OneVo;

@Repository
public class OneDao extends AbstractDAO{
	
	/** 해당 회원의 문의내역 총 개수 구하기 */
	public int oneListCount(OneVo oneVo) {
		return (int)selectOne("oneSql.oneListCount", oneVo);
	}
	
	/** 해당 회원이 쓴 문의내역 불러오기 */
	public List<OneVo> oneList(OneVo oneVo) {
		return selectList("oneSql.oneList", oneVo);
	}
	
	/** 작성한 문의내역 저장 */
	public void oneWriteSave(OneVo oneVo) {
		insert("oneSql.oneWriteSave", oneVo);
	}
	
	/** 게시글 번호 조회 */
	public int freeBoardIdx() {
		return (int) selectOne("oneSql.freeBoardIdx");
	}
	
	/** 문의내역글 상세페이지 */
	public OneVo oneDetail(OneVo oneVo) {
		return (OneVo)selectOne("oneSql.oneDetail", oneVo);
	}
}
