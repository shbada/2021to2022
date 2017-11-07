package com.co.kr.board.free.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.co.kr.board.free.vo.FreeBoardVo;
import com.co.kr.common.dao.AbstractDAO;

@Repository
public class FreeBoardDao extends AbstractDAO {

	/*public List<FreeBoardVo> selectFreeBoardList(FreeBoardVo freeBoardVo) {
		return selectList("FreeBoard.selectFreeBoardList", freeBoardVo);
	}*/
	
	public List<FreeBoardVo> selectFreeBoardList(FreeBoardVo freeBoardVo) {
		return selectList("freeBoardSql.selectFreeBoardList", freeBoardVo);
	}

	public int selectFreeBoardListCount(FreeBoardVo freeBoardVo) {
		return (int)selectOne("freeBoardSql.selectFreeBoardListCount", freeBoardVo);
	}

	public void freeBorWriteSave(FreeBoardVo freeBoardVo) {
		insert("freeBoardSql.freeBorWriteSave", freeBoardVo);
	}
	
	public int freeBoardIdx(){
		return (int) selectOne("freeBoardSql.freeBoardIdx");
	}

	public FreeBoardVo freeBoardDetail(FreeBoardVo freeBoardVo) {
		return (FreeBoardVo)selectOne("freeBoardSql.freeBoardDetail", freeBoardVo);
	}

	public FreeBoardVo freeBoardUpdateView(FreeBoardVo freeBoardVo) {
		return (FreeBoardVo)selectOne("freeBoardSql.freeBoardUpdateView", freeBoardVo);
	}

	public void freeBoardUpdate(FreeBoardVo freeBoardVo) {
		update("freeBoardSql.freeBoardUpdate", freeBoardVo);
	}

	public void freeBoardDelete(FreeBoardVo freeBoardVo) {
		delete("freeBoardSql.freeBoardDelete", freeBoardVo);
	}

	public int freeBoardViewCnt(int freeBorIdx) {
		return (int) selectOne("freeBoardSql.freeBoardViewCnt", freeBorIdx);
	}

	public void freeBoardUpdateCnt(FreeBoardVo freeBoardVo) {
		update("freeBoardSql.freeBoardUpdateCnt", freeBoardVo);
	}

	public int freeBoardLikeCnt(int freeBorIdx) {
		return (int) selectOne("freeBoardSql.freeBoardLikeCnt", freeBorIdx);
	}

	public FreeBoardVo freeBoardLike(FreeBoardVo freeBoardVo) {
		return (FreeBoardVo) selectOne("freeBoardSql.freeBoardLike", freeBoardVo);
	}

	public void insertFreeBoardLike(FreeBoardVo freeBoardVo) {
		insert("freeBoardSql.insertFreeBoardLike", freeBoardVo);
	}

	public void freeBoardLikeDelete(FreeBoardVo freeBoardVo) {
		delete("freeBoardSql.freeBoardLikeDelete", freeBoardVo);
		
	}

	public void freeBoardReplyDelete(FreeBoardVo freeBoardVo) {
		delete("freeBoardSql.freeBoardReplyDelete", freeBoardVo);		
	}
	
	public List<FreeBoardVo> selectSearchWordList() {
		return selectList("freeBoardSql.selectSearchWordList");
	}
	
	public int selectSearchCheck(String search) {
		return (int) selectOne("freeBoardSql.selectSearchCheck", search);
	}

	public void insertSearchWord(String search) {
		insert("freeBoardSql.insertSearchWord", search);
	}

	public void updateSearchWordCount(String search) {
		update("freeBoardSql.updateSearchWordCount", search);
	}

	public void realTimeQueryMerge(String search) {
		update("freeBoardSql.realTimeQueryMerge", search);
	}

	public void freeBoardListDelete(FreeBoardVo freeBoardVo) {
		update("freeBoardSql.freeBoardListDelete", freeBoardVo);
	}

}
