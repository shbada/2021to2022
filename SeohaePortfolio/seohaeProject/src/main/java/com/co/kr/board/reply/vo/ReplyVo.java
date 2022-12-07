package com.co.kr.board.reply.vo;

import com.co.kr.common.annotaion.Comment;
import com.co.kr.common.vo.PageVo;

public class ReplyVo extends PageVo{
	@Comment("댓글번호")
	private int freeBorReplyIdx;
	@Comment("게시글 번호")
	private int freeBorIdx;
	@Comment("댓글내용")
	private String replyText;
	@Comment("댓글작성자")
	private String replyer;
	@Comment("등록날짜")
	private String regDe;
	@Comment("수정날짜")
	private String updateDe;
	@Comment("페이지 갯수")
	private String pageCnt;
	@Comment("비밀댓글 여부")
	private String secretReply;
	@Comment("댓글작성자 이름")
	private String writer;
	@Comment("댓글작성자 ID")
	private String userId;
	
	public int getFreeBorReplyIdx() {
		return freeBorReplyIdx;
	}
	public void setFreeBorReplyIdx(int freeBorReplyIdx) {
		this.freeBorReplyIdx = freeBorReplyIdx;
	}
	public int getFreeBorIdx() {
		return freeBorIdx;
	}
	public void setFreeBorIdx(int freeBorIdx) {
		this.freeBorIdx = freeBorIdx;
	}
	public String getReplyText() {
		return replyText;
	}
	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}
	public String getReplyer() {
		return replyer;
	}
	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}
	public String getRegDe() {
		return regDe;
	}
	public void setRegDe(String regDe) {
		this.regDe = regDe;
	}
	public String getUpdateDe() {
		return updateDe;
	}
	public void setUpdateDe(String updateDe) {
		this.updateDe = updateDe;
	}
	public String getPageCnt() {
		return pageCnt;
	}
	public void setPageCnt(String pageCnt) {
		this.pageCnt = pageCnt;
	}
	public String getSecretReply() {
		return secretReply;
	}
	public void setSecretReply(String secretReply) {
		this.secretReply = secretReply;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	@Override
	public String toString() {
		return "ReplyVo [freeBorReplyIdx=" + freeBorReplyIdx + ", freeBorIdx=" + freeBorIdx + ", replyText=" + replyText
				+ ", replyer=" + replyer + ", regDe=" + regDe + ", updateDe=" + updateDe + ", pageCnt=" + pageCnt
				+ ", secretReply=" + secretReply + ", writer=" + writer + ", userId=" + userId + "]";
	}
	
	
}
