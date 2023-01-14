package com.co.kr.board.reply.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.kr.board.reply.dao.ReplyDao;
import com.co.kr.board.reply.service.ReplyService;
import com.co.kr.board.reply.vo.ReplyVo;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	public ReplyDao replyDao;
	
	@Override
	public void freeBoardReplyWrite(ReplyVo replyVo) {
		replyDao.freeBoardReplyWrite(replyVo);
	}

	@Override
	public List<ReplyVo> freeBoardJsonListReply(ReplyVo replyVo) {
		return replyDao.freeBoardJsonListReply(replyVo);
	}

	@Override
	public int freeBoardListReplyCount(ReplyVo replyVo) {
		return replyDao.freeBoardListReplyCount(replyVo);
	}

	@Override
	public List<ReplyVo> freeBoardListReply(ReplyVo replyVo, HttpSession session) {
		List<ReplyVo> items = replyDao.freeBoardListReply(replyVo);
		String userId = (String) session.getAttribute("userId");
		
		for(ReplyVo vo : items){
			vo.setReplyText(vo.getReplyText().replaceAll("<", "&lt;").replaceAll("&lt;br>", "<br>"));
		}
		
		return items;
	}

	@Override
	public void replyOneDelete(int freeBorReplyIdx) {
		replyDao.replyOneDelete(freeBorReplyIdx);
	}

	@Override
	public void replyOneUpdate(ReplyVo replyVo) {
		replyDao.replyOneUpdate(replyVo);
		
	}
}
