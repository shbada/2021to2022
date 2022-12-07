package com.co.kr.buy.vo;

public class BuyVo {

	private int buyNo;
	private int buyTotalbino;
	private String buyRegDate;
	private String buyName;
	private String buyPhone;
	private String buyZipCode;
	private String buyFirstAddr;
	private String buySecondAddr;
	private String userId;
	private int pdQt;
	private int pdNo;
	private int pdPrice;
	private String pdName; 
    private String pdUrl;
    
	public int getBuyNo() {
		return buyNo;
	}
	public void setBuyNo(int buyNo) {
		this.buyNo = buyNo;
	}
	public int getBuyTotalbino() {
		return buyTotalbino;
	}
	public void setBuyTotalbino(int buyTotalbino) {
		this.buyTotalbino = buyTotalbino;
	}
	public String getBuyRegDate() {
		return buyRegDate;
	}
	public void setBuyRegDate(String buyRegDate) {
		this.buyRegDate = buyRegDate;
	}
	public String getBuyName() {
		return buyName;
	}
	public void setBuyName(String buyName) {
		this.buyName = buyName;
	}
	public String getBuyPhone() {
		return buyPhone;
	}
	public void setBuyPhone(String buyPhone) {
		this.buyPhone = buyPhone;
	}
	public String getBuyZipCode() {
		return buyZipCode;
	}
	public void setBuyZipCode(String buyZipCode) {
		this.buyZipCode = buyZipCode;
	}
	public String getBuyFirstAddr() {
		return buyFirstAddr;
	}
	public void setBuyFirstAddr(String buyFirstAddr) {
		this.buyFirstAddr = buyFirstAddr;
	}
	public String getBuySecondAddr() {
		return buySecondAddr;
	}
	public void setBuySecondAddr(String buySecondAddr) {
		this.buySecondAddr = buySecondAddr;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getPdQt() {
		return pdQt;
	}
	public void setPdQt(int pdQt) {
		this.pdQt = pdQt;
	}
	public int getPdNo() {
		return pdNo;
	}
	public void setPdNo(int pdNo) {
		this.pdNo = pdNo;
	}
	public int getPdPrice() {
		return pdPrice;
	}
	public void setPdPrice(int pdPrice) {
		this.pdPrice = pdPrice;
	}
	public String getPdName() {
		return pdName;
	}
	public void setPdName(String pdName) {
		this.pdName = pdName;
	}
	public String getPdUrl() {
		return pdUrl;
	}
	public void setPdUrl(String pdUrl) {
		this.pdUrl = pdUrl;
	}
	@Override
	public String toString() {
		return "BuyVo [buyNo=" + buyNo + ", buyTotalbino=" + buyTotalbino + ", buyRegDate=" + buyRegDate + ", buyName="
				+ buyName + ", buyPhone=" + buyPhone + ", buyZipCode=" + buyZipCode + ", buyFirstAddr=" + buyFirstAddr
				+ ", buySecondAddr=" + buySecondAddr + ", userId=" + userId + ", pdQt=" + pdQt + ", pdNo=" + pdNo
				+ ", pdPrice=" + pdPrice + ", pdName=" + pdName + ", pdUrl=" + pdUrl + "]";
	}
	
}
