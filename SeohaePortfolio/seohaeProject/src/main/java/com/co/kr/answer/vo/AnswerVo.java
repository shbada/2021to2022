package com.co.kr.answer.vo;

import com.co.kr.common.annotaion.Comment;

public class AnswerVo {
	@Comment("해당 질문글 번호")
	private int qIdx;
	@Comment("답변글 번호")
	private int aIdx;
	@Comment("질문글 내용")
	private String aDesc;
	@Comment("조회수")
	private int aViewCnt;
	@Comment("작성자 아이디")
	private String aUserId;
	@Comment("작성날짜")
	private String aRegDe;
	@Comment("채택 답변글인지 아닌지 구분")
	private String aYn;
	@Comment("추천수")
	private int aLike;
	
	public int getqIdx() {
		return qIdx;
	}
	public void setqIdx(int qIdx) {
		this.qIdx = qIdx;
	}
	public String getaDesc() {
		return aDesc;
	}
	public void setaDesc(String aDesc) {
		this.aDesc = aDesc;
	}
	public int getaViewCnt() {
		return aViewCnt;
	}
	public void setaViewCnt(int aViewCnt) {
		this.aViewCnt = aViewCnt;
	}
	public String getaUserId() {
		return aUserId;
	}
	public void setaUserId(String aUserId) {
		this.aUserId = aUserId;
	}
	public String getaRegDe() {
		return aRegDe;
	}
	public void setaRegDe(String aRegDe) {
		this.aRegDe = aRegDe;
	}
	public String getaYn() {
		return aYn;
	}
	public void setaYn(String aYn) {
		this.aYn = aYn;
	}
	
	public int getaLike() {
		return aLike;
	}
	public void setaLike(int aLike) {
		this.aLike = aLike;
	}
	
	public int getaIdx() {
		return aIdx;
	}
	public void setaIdx(int aIdx) {
		this.aIdx = aIdx;
	}
	@Override
	public String toString() {
		return "AnswerVo [qIdx=" + qIdx + ", aIdx=" + aIdx + ", aDesc=" + aDesc + ", aViewCnt=" + aViewCnt
				+ ", aUserId=" + aUserId + ", aRegDe=" + aRegDe + ", aYn=" + aYn + ", aLike=" + aLike + "]";
	}
}
