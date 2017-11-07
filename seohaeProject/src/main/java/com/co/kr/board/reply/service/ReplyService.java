package com.co.kr.board.reply.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.co.kr.board.reply.vo.ReplyVo;

public interface ReplyService {

	public void freeBoardReplyWrite(ReplyVo replyVo);

	public List<ReplyVo> freeBoardJsonListReply(ReplyVo replyVo);

	public int freeBoardListReplyCount(ReplyVo replyVo);

	public List<ReplyVo> freeBoardListReply(ReplyVo replyVo, HttpSession session);

	public void replyOneDelete(int freeBorReplyIdx);

	public void replyOneUpdate(ReplyVo replyVo);

}
