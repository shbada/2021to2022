package com.co.kr.user.vo;

public class UserAuthVo {
	private String userId;
	private int userSeq;
	private String userAuthCd;
	private String useYn;
	
	private String divGb;
	private String firstMenu;
	private String firstUrl;
	private String pgmId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	public String getUserAuthCd() {
		return userAuthCd;
	}
	public void setUserAuthCd(String userAuthCd) {
		this.userAuthCd = userAuthCd;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getDivGb() {
		return divGb;
	}
	public void setDivGb(String divGb) {
		this.divGb = divGb;
	}
	public String getFirstMenu() {
		return firstMenu;
	}
	public void setFirstMenu(String firstMenu) {
		this.firstMenu = firstMenu;
	}
	public String getFirstUrl() {
		return firstUrl;
	}
	public void setFirstUrl(String firstUrl) {
		this.firstUrl = firstUrl;
	}
	public String getPgmId() {
		return pgmId;
	}
	public void setPgmId(String pgmId) {
		this.pgmId = pgmId;
	}
	
	@Override
	public String toString() {
		return "UserAuthVo [userId=" + userId + ", userSeq=" + userSeq + ", userAuthCd=" + userAuthCd + ", useYn="
				+ useYn + ", divGb=" + divGb + ", firstMenu=" + firstMenu + ", firstUrl=" + firstUrl + ", pgmId="
				+ pgmId + "]";
	}
}
