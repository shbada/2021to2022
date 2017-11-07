package com.co.kr.board.reply.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.co.kr.board.reply.vo.ReplyVo;
import com.co.kr.common.dao.AbstractDAO;

@Repository
public class ReplyDao extends AbstractDAO {

	public void freeBoardReplyWrite(ReplyVo replyVo) {
		insert("replySql.freeBoardReplyWrite",replyVo);
	}

	public List<ReplyVo> freeBoardJsonListReply(ReplyVo replyVo) {
		return selectList("replySql.freeBoardJsonListReply", replyVo);
	}

	public int freeBoardListReplyCount(ReplyVo replyVo) {
		return (int) selectOne("replySql.freeBoardListReplyCount", replyVo);
	}

	public List<ReplyVo> freeBoardListReply(ReplyVo replyVo) {
		return selectList("replySql.freeBoardListReply", replyVo);
	}

	public void replyOneDelete(int freeBorReplyIdx) {
		delete("replySql.replyOneDelete", freeBorReplyIdx);
	}

	public void replyOneUpdate(ReplyVo replyVo) {
		update("replySql.replyOneUpdate", replyVo);
	}

}
