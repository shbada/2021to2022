package com.co.kr.oneto.admin.service;

import java.util.Map;

import com.co.kr.oneto.one.vo.OneVo;

public interface OneAdminService {
	
	/** 모든회원이 작성한 문의내역글 가져오기 */
	Map<String, Object> oneAdminList(OneVo oneVo);
	
	/** 문의내역글 상세페이지 */
	OneVo oneAdminDetail(OneVo oneVo);
	
	/** 문의글 답변글 쓰기 */
	void oneAdminWriteSave(OneVo oneVo);
	
	/** 해당 글의 답변글 가져오기 */
	Map<String, Object> oneAdminListRe(OneVo oneVo);

	void oneYnUpdate(OneVo oneVo);
	
}
