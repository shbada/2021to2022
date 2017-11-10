package com.co.kr.user.vo;

import com.co.kr.common.annotaion.Comment;

public class UserVo {
	@Comment("사용자 등록 번호")
	private int registNo;
	@Comment("사용자 아이디")
	private String userId;
	@Comment("사용자 비밀번호")
	private String userPw;
	@Comment("임시비밀번호")
	private String tmprPwNo;
	@Comment("사용자 이름")
	private String userNm;
	@Comment("사용자 이메일")
	private String userEmail;
	@Comment("사용자 전화번호")
	private String userPhone;
	@Comment("사용자 등급")
	private String userLevel;
	@Comment("사용자 탈퇴 여부")
	private String userDelYn;
	@Comment("사용자 탈퇴 날짜")
	private String userDelDe;
	@Comment("가입 날짜")
	private String regDe;
	@Comment("패스워드 마지막 변경일")
	private String passwordLastChangeDe;
	@Comment("패스워드 틀린 횟 수")
	private int failCnt;
	@Comment("리스트 코드")
	private String studyLictCode;
	@Comment("우편번호")
	private String userZipCode;
	@Comment("첫번째 주소")
	private String userFirstAddr;
	@Comment("두번째 주소")
	private String userSecondAddr;
	@Comment("bino 총액")
	private int bino;
	
	public int getRegistNo() {
		return registNo;
	}
	public void setRegistNo(int registNo) {
		this.registNo = registNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getTmprPwNo() {
		return tmprPwNo;
	}
	public void setTmprPwNo(String tmprPwNo) {
		this.tmprPwNo = tmprPwNo;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	public String getUserDelYn() {
		return userDelYn;
	}
	public void setUserDelYn(String userDelYn) {
		this.userDelYn = userDelYn;
	}
	public String getUserDelDe() {
		return userDelDe;
	}
	public void setUserDelDe(String userDelDe) {
		this.userDelDe = userDelDe;
	}
	public String getRegDe() {
		return regDe;
	}
	public void setRegDe(String regDe) {
		this.regDe = regDe;
	}
	public String getPasswordLastChangeDe() {
		return passwordLastChangeDe;
	}
	public void setPasswordLastChangeDe(String passwordLastChangeDe) {
		this.passwordLastChangeDe = passwordLastChangeDe;
	}
	public int getFailCnt() {
		return failCnt;
	}
	public void setFailCnt(int failCnt) {
		this.failCnt = failCnt;
	}
	public String getStudyLictCode() {
		return studyLictCode;
	}
	public void setStudyLictCode(String studyLictCode) {
		this.studyLictCode = studyLictCode;
	}
	public String getUserZipCode() {
		return userZipCode;
	}
	public void setUserZipCode(String userZipCode) {
		this.userZipCode = userZipCode;
	}
	public String getUserFirstAddr() {
		return userFirstAddr;
	}
	public void setUserFirstAddr(String userFirstAddr) {
		this.userFirstAddr = userFirstAddr;
	}
	public String getUserSecondAddr() {
		return userSecondAddr;
	}
	public void setUserSecondAddr(String userSecondAddr) {
		this.userSecondAddr = userSecondAddr;
	}
	
	public int getBino() {
		return bino;
	}
	public void setBino(int bino) {
		this.bino = bino;
	}
	@Override
	public String toString() {
		return "UserVo [registNo=" + registNo + ", userId=" + userId + ", userPw=" + userPw + ", tmprPwNo=" + tmprPwNo
				+ ", userNm=" + userNm + ", userEmail=" + userEmail + ", userPhone=" + userPhone + ", userLevel="
				+ userLevel + ", userDelYn=" + userDelYn + ", userDelDe=" + userDelDe + ", regDe=" + regDe
				+ ", passwordLastChangeDe=" + passwordLastChangeDe + ", failCnt=" + failCnt + ", studyLictCode="
				+ studyLictCode + ", userZipCode=" + userZipCode + ", userFirstAddr=" + userFirstAddr
				+ ", userSecondAddr=" + userSecondAddr + ", bino=" + bino + "]";
	}
}
