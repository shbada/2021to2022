package com.co.kr.bino.vo;

import com.co.kr.common.annotaion.Comment;

public class BinoVo {
	@Comment("번호")
	private int binoNo;
	@Comment("금액")
	private int bino;
	@Comment("아이디")
	private String userId;
	@Comment("등록날짜")
	private String binoRegdate;
	@Comment("적립/사용 여부")
	private String binoYn;
	public int getBinoNo() {
		return binoNo;
	}
	public void setBinoNo(int binoNo) {
		this.binoNo = binoNo;
	}
	public int getBino() {
		return bino;
	}
	public void setBino(int bino) {
		this.bino = bino;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBinoRegdate() {
		return binoRegdate;
	}
	public void setBinoRegdate(String binoRegdate) {
		this.binoRegdate = binoRegdate;
	}
	public String getBinoYn() {
		return binoYn;
	}
	public void setBinoYn(String binoYn) {
		this.binoYn = binoYn;
	}
	@Override
	public String toString() {
		return "BinoVo [binoNo=" + binoNo + ", bino=" + bino + ", userId=" + userId + ", binoRegdate=" + binoRegdate
				+ ", binoYn=" + binoYn + "]";
	}
}
