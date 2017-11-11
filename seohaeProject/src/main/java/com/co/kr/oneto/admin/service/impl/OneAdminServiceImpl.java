package com.co.kr.oneto.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.kr.oneto.admin.dao.OneAdminDao;
import com.co.kr.oneto.admin.service.OneAdminService;
import com.co.kr.oneto.one.vo.OneVo;

@Service
public class OneAdminServiceImpl implements OneAdminService{
	
	private Log log = LogFactory.getLog(OneAdminServiceImpl.class);
	
	@Autowired
	private OneAdminDao oneAdminDao;

	/** 모든회원이 작성한 문의내역글 가져오기 */
	@Override
	public Map<String, Object> oneAdminList(OneVo oneVo) {
		int totalCount = oneAdminDao.oneAdminListCount(oneVo); // 총 게시글 수를 가져온다.
		oneVo.setTotalCount(totalCount); // 전체 게시글 수를 Vo의 필드에 저장시킨다.
		
		List<OneVo> list = oneAdminDao.oneAdminList(oneVo); // 게시글을 List타입으로 모두 가져와 list에 담는다.
		
		Map<String, Object> map = new HashMap<String, Object>(); //Map타입의 map 변수 선언한다.
		
		map.put("list", list); // 게시글을 저장시킨 list를 map에 "list"이름으로 모두 저장한다.
		
		return map;
	}
	
	/** 해당 글의 답변글 가져오기 */
	@Override
	public Map<String, Object> oneAdminListRe(OneVo oneVo) {
		/*int totalCount = oneAdminDao.oneAdminListCount(oneAdminVo); // 총 게시글 수를 가져온다.
		oneAdminVo.setTotalCount(totalCount); // 전체 게시글 수를 Vo의 필드에 저장시킨다.
*/		
		List<OneVo> list2 = oneAdminDao.oneAdminListRe(oneVo); // 게시글을 List타입으로 모두 가져와 list에 담는다.
		
		Map<String, Object> map2 = new HashMap<String, Object>(); //Map타입의 map 변수 선언한다.
		
		map2.put("list2", list2); // 게시글을 저장시킨 list를 map에 "list"이름으로 모두 저장한다.
		
		return map2;
	}
	
	/** 문의내역글 상세페이지 */
	@Override
	public OneVo oneAdminDetail(OneVo oneVo) {
		return oneAdminDao.oneAdminDetail(oneVo);
	}
	
	/** 문의글 답변글 쓰기 */
	@Override
	public void oneAdminWriteSave(OneVo oneVo) {
		oneAdminDao.oneAdminWriteSave(oneVo);
	}

	@Override
	public void oneYnUpdate(OneVo oneVo) {
		oneAdminDao.oneYnUpdate(oneVo);
	}
	
}
