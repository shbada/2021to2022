package com.co.kr.cart.vo;

public class CartVo {
	
	private int cartNo; //장바구니 번호
	private int pdAmount; //상품 수량
	private String user_id; //회원 아이디
	private int pdNo; //상품번호
	private String pdName; //상품 이름
	private String pdUrl; //상품 사진경로
	private int pdPrice; //상품 가격
	private String pdCg;
	private String pdInfo;
	
	public int getCartNo() {
		return cartNo;
	}
	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}
	public int getPdAmount() {
		return pdAmount;
	}
	public void setPdAmount(int pdAmount) {
		this.pdAmount = pdAmount;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getPdNo() {
		return pdNo;
	}
	public void setPdNo(int pdNo) {
		this.pdNo = pdNo;
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
	public int getPdPrice() {
		return pdPrice;
	}
	public void setPdPrice(int pdPrice) {
		this.pdPrice = pdPrice;
	}
	public String getPdCg() {
		return pdCg;
	}
	public void setPdCg(String pdCg) {
		this.pdCg = pdCg;
	}
	public String getPdInfo() {
		return pdInfo;
	}
	public void setPdInfo(String pdInfo) {
		this.pdInfo = pdInfo;
	}
	@Override
	public String toString() {
		return "CartVo [cartNo=" + cartNo + ", pdAmount=" + pdAmount + ", user_id=" + user_id + ", pdNo=" + pdNo
				+ ", pdName=" + pdName + ", pdUrl=" + pdUrl + ", pdPrice=" + pdPrice + ", pdCg=" + pdCg + ", pdInfo="
				+ pdInfo + "]";
	}
	
}
