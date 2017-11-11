package com.co.kr.oneto.one.service;

import java.util.Map;

import com.co.kr.oneto.one.vo.OneVo;

public interface OneService {
	
	/** 해당 회원이 쓴 문의내역 불러오기 */
	Map<String, Object> oneList(OneVo oneVo);
	
	/** 작성한 문의내역 저장 */
	void oneWriteSave(OneVo oneVo);
	
	/** 게시글 번호 조회 */
	int freeBoardIdx();
	
	/** 문의내역글 상세페이지 */
	OneVo oneDetail(OneVo oneVo);
}
