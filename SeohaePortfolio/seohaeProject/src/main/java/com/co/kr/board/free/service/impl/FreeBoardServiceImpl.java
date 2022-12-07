package com.co.kr.board.free.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.kr.board.free.dao.FreeBoardDao;
import com.co.kr.board.free.service.FreeBoardService;
import com.co.kr.board.free.vo.FreeBoardVo;

@Service
public class FreeBoardServiceImpl implements FreeBoardService{
	
	private Log log = LogFactory.getLog(FreeBoardServiceImpl.class);
	
	@Autowired
	private FreeBoardDao freeBoardDao;

	@Override
	public Map<String, Object> selectFreeBoardList(FreeBoardVo freeBoardVo) {
		int totalCount = freeBoardDao.selectFreeBoardListCount(freeBoardVo);
		freeBoardVo.setTotalCount(totalCount);
		
		//실시간 검색어
		if(freeBoardVo.getSearch() != null && freeBoardVo.getSearch() != "" && freeBoardVo.getSearch().length() != 0) {		
			freeBoardDao.realTimeQueryMerge(freeBoardVo.getSearch()); //검색어 '실시간 검색어' 테이블에 insert.
		}
		
		List<FreeBoardVo> list = freeBoardDao.selectFreeBoardList(freeBoardVo);
		//실시간 검색어 (10위권에 해당하는 검색어 10개를 가져옴)
		List<FreeBoardVo> searchWord = freeBoardDao.selectSearchWordList();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("list", list);
		map.put("searchWord", searchWord);
		
		
		return map;
	}

	@Override
	public int selectFreeBoardListCount(FreeBoardVo freeBoardVo) {
		return freeBoardDao.selectFreeBoardListCount(freeBoardVo);
	}

	@Override
	public void freeBorWriteSave(FreeBoardVo freeBoardVo) {
		freeBoardDao.freeBorWriteSave(freeBoardVo);
		
	}

	@Override
	public int freeBoardIdx() {
		return freeBoardDao.freeBoardIdx();
	}

	@Override
	public FreeBoardVo freeBoardDetail(FreeBoardVo freeBoardVo) {
		return freeBoardDao.freeBoardDetail(freeBoardVo);
	}

	@Override
	public FreeBoardVo freeBoardUpdateView(FreeBoardVo freeBoardVo) {
		return freeBoardDao.freeBoardUpdateView(freeBoardVo);
	}

	@Override
	public void freeBoardUpdate(FreeBoardVo freeBoardVo) {
		freeBoardDao.freeBoardUpdate(freeBoardVo);
		
	}

	@Override
	public void freeBoardDelete(FreeBoardVo freeBoardVo) {
		freeBoardDao.freeBoardDelete(freeBoardVo);
	}

	@Override
	public int freeBoardViewCnt(int freeBorIdx) {
		return freeBoardDao.freeBoardViewCnt(freeBorIdx);
	}

	@Override
	public void freeBoardUpdateCnt(FreeBoardVo freeBoardVo, HttpSession session) {
		long viewTime = 0;
		
		//세션에 저장된 조회시간
		if(session.getAttribute("viewTime" + freeBoardVo.getFreeBorIdx()) !=null){ //글번호가 있으면
			viewTime = (Long)session.getAttribute("viewTime" + freeBoardVo.getFreeBorIdx());
		}
		
		//시스템의 현재시간
		long nowTime = System.currentTimeMillis();
		
		//셋팅된 시간이 경과 후 조회수 증가(60초 후 조회수 증가)
		if(nowTime - viewTime > 60 * 1000){
			freeBoardDao.freeBoardUpdateCnt(freeBoardVo);
			session.setAttribute("viewTime" + freeBoardVo.getFreeBorIdx(), nowTime);
		}
	}

	@Override
	public int freeBoardLikeCnt(int freeBorIdx) {
		return freeBoardDao.freeBoardLikeCnt(freeBorIdx);
	}

	@Override
	public FreeBoardVo freeBoardLike(FreeBoardVo freeBoardVo) {
		return freeBoardDao.freeBoardLike(freeBoardVo);
	}

	@Override
	public void insertFreeBoardLike(FreeBoardVo freeBoardVo) {
		freeBoardDao.insertFreeBoardLike(freeBoardVo);
		
	}

	@Override
	public void freeBoardLikeDelete(FreeBoardVo freeBoardVo) {
		freeBoardDao.freeBoardLikeDelete(freeBoardVo);
	}

	@Override
	public void freeBoardReplyDelete(FreeBoardVo freeBoardVo) {
		freeBoardDao.freeBoardReplyDelete(freeBoardVo);		
	}

	@Override
	public void freeBoardListDelete(List<FreeBoardVo> data) {
		for(int i=0; i<data.size(); i++)
			freeBoardDao.freeBoardListDelete(data.get(i));
	}
}
