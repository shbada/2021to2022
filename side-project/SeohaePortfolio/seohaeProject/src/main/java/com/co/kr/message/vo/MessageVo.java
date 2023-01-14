package com.co.kr.message.vo;

import com.co.kr.common.annotaion.Comment;

public class MessageVo {
	@Comment("쪽지 번호")
	private int msgNo; 
	@Comment("쪽지 보낸이")
	private String msgSend; 
	@Comment("보낸날짜")
	private String msgRegdate;
	@Comment("쪽지 받는이")
	private String msgGet;
	@Comment("쪽지 내용")
	private String msgDesc;
	@Comment("쪽지 제목")
	private String msgName;
	@Comment("보낸쪽지 삭제여부")
	private String msgSendyn;
	@Comment("받은쪽지 삭제여부")
	private String msgGetyn;
	@Comment("접속자 아이디")
	private String userId;
	@Comment("읽음여부")
	private String msgReadyn;
	public int getMsgNo() {
		return msgNo;
	}
	public void setMsgNo(int msgNo) {
		this.msgNo = msgNo;
	}
	public String getMsgSend() {
		return msgSend;
	}
	public void setMsgSend(String msgSend) {
		this.msgSend = msgSend;
	}
	public String getMsgRegdate() {
		return msgRegdate;
	}
	public void setMsgRegdate(String msgRegdate) {
		this.msgRegdate = msgRegdate;
	}
	public String getMsgGet() {
		return msgGet;
	}
	public void setMsgGet(String msgGet) {
		this.msgGet = msgGet;
	}
	public String getMsgDesc() {
		return msgDesc;
	}
	public void setMsgDesc(String msgDesc) {
		this.msgDesc = msgDesc;
	}
	public String getMsgName() {
		return msgName;
	}
	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}
	public String getMsgSendyn() {
		return msgSendyn;
	}
	public void setMsgSendyn(String msgSendyn) {
		this.msgSendyn = msgSendyn;
	}
	public String getMsgGetyn() {
		return msgGetyn;
	}
	public void setMsgGetyn(String msgGetyn) {
		this.msgGetyn = msgGetyn;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMsgReadyn() {
		return msgReadyn;
	}
	public void setMsgReadyn(String msgReadyn) {
		this.msgReadyn = msgReadyn;
	}
	@Override
	public String toString() {
		return "MessageVo [msgNo=" + msgNo + ", msgSend=" + msgSend + ", msgRegdate=" + msgRegdate + ", msgGet="
				+ msgGet + ", msgDesc=" + msgDesc + ", msgName=" + msgName + ", msgSendyn=" + msgSendyn + ", msgGetyn="
				+ msgGetyn + ", userId=" + userId + ", msgReadyn=" + msgReadyn + "]";
	}
	
}
