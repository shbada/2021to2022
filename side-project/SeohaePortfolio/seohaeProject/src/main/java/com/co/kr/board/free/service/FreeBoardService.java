package com.co.kr.board.free.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.co.kr.board.free.vo.FreeBoardVo;

public interface FreeBoardService {

	public Map<String, Object> selectFreeBoardList(FreeBoardVo freeBoardVo);
	
	/*public List<FreeBoardVo> selectFreeBoardList(FreeBoardVo freeBoardVo);*/

	public int selectFreeBoardListCount(FreeBoardVo freeBoardVo);

	public void freeBorWriteSave(FreeBoardVo freeBoardVo);

	public int freeBoardIdx();

	public FreeBoardVo freeBoardDetail(FreeBoardVo freeBoardVo);

	public FreeBoardVo freeBoardUpdateView(FreeBoardVo freeBoardVo);

	public void freeBoardUpdate(FreeBoardVo freeBoardVo);

	public void freeBoardDelete(FreeBoardVo freeBoardVo);

	public int freeBoardViewCnt(int freeBorIdx);

	public void freeBoardUpdateCnt(FreeBoardVo freeBoardVo, HttpSession session);

	public int freeBoardLikeCnt(int freeBorIdx);

	public FreeBoardVo freeBoardLike(FreeBoardVo freeBoardVo);

	public void insertFreeBoardLike(FreeBoardVo freeBoardVo);

	public void freeBoardLikeDelete(FreeBoardVo freeBoardVo);

	public void freeBoardReplyDelete(FreeBoardVo freeBoardVo);

	public void freeBoardListDelete(List<FreeBoardVo> data);

}
