package com.co.kr.answer.vo;

import com.co.kr.common.annotaion.Comment;

public class AnswerVo {
	@Comment("답변글 번호=해당 질문글 번호")
	private int qIdx;
	@Comment("질문글 내용")
	private String aDesc;
	@Comment("조회수")
	private int aViewCnt;
	@Comment("작성자 아이디")
	private String userId;
	@Comment("작성날짜")
	private String aRegDe;
	@Comment("채택 답변글인지 아닌지 구분")
	
	private String aYn;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	@Override
	public String toString() {
		return "AnswerVo [qIdx=" + qIdx + ", aDesc=" + aDesc + ", aViewCnt=" + aViewCnt + ", userId=" + userId
				+ ", aRegDe=" + aRegDe + ", aYn=" + aYn + "]";
	}

}
