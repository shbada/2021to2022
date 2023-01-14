package com.co.kr.buy.vo;

public class BuyProductInfoVo {

	private int pdQt;
	private int pdNo;
	private int buyNo;
	private int pdPrice;
	public int getPdAmount() {
		return pdQt;
	}
	public void setPdAmount(int pdQt) {
		this.pdQt = pdQt;
	}
	public int getPdNo() {
		return pdNo;
	}
	public void setPdNo(int pdNo) {
		this.pdNo = pdNo;
	}
	public int getBuyNo() {
		return buyNo;
	}
	public void setBuyNo(int buyNo) {
		this.buyNo = buyNo;
	}
	public int getPdPrice() {
		return pdPrice;
	}
	public void setPdPrice(int pdPrice) {
		this.pdPrice = pdPrice;
	}
	@Override
	public String toString() {
		return "BuyProductInfoVo [pdQt=" + pdQt + ", pdNo=" + pdNo + ", buyNo=" + buyNo + ", pdPrice=" + pdPrice
				+ "]";
	}
	
}
