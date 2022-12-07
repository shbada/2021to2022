package com.co.kr.oneto.one.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.kr.oneto.one.dao.OneDao;
import com.co.kr.oneto.one.service.OneService;
import com.co.kr.oneto.one.vo.OneVo;

@Service
public class OneServiceImpl implements OneService{
	
	private Log log = LogFactory.getLog(OneServiceImpl.class);
	
	@Autowired
	private OneDao oneDao;
	
	/** 해당 회원이 쓴 문의내역 불러오기 */
	@Override
	public Map<String, Object> oneList(OneVo oneVo) {
		int totalCount = oneDao.oneListCount(oneVo); // 총 게시글 수를 가져온다.
		oneVo.setTotalCount(totalCount); // 전체 게시글 수를 Vo의 필드에 저장시킨다.
		
		List<OneVo> list = oneDao.oneList(oneVo); // 게시글을 List타입으로 모두 가져와 list에 담는다.
		
		Map<String, Object> map = new HashMap<String, Object>(); //Map타입의 map 변수 선언한다.
		
		map.put("list", list); // 게시글을 저장시킨 list를 map에 "list"이름으로 모두 저장한다.
		
		return map;
	}
	
	/** 작성한 문의내역 저장 */
	@Override
	public void oneWriteSave(OneVo oneVo) {
		oneDao.oneWriteSave(oneVo);
	}
	
	/** 게시글 번호 조회 */
	@Override
	public int freeBoardIdx() {
		return oneDao.freeBoardIdx();
	}
	
	/** 문의내역글 상세페이지 */
	@Override
	public OneVo oneDetail(OneVo oneVo) {
		return oneDao.oneDetail(oneVo);
	}
	
}
