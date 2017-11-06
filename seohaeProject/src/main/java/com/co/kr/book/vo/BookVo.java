package com.co.kr.book.vo;

import org.springframework.web.multipart.MultipartFile;

import com.co.kr.common.annotaion.Comment;

public class BookVo {
	private int rm;
	@Comment("제품 번호")
	private int pdNo;
	@Comment("제품 종류")
	private String pdCg;
	@Comment("제품 이름")
	private String pdName;
	@Comment("제품 가격")
	private int pdPrice;
	@Comment("제품 설명")
	private String pdInfo;
	@Comment("제품 수량")
	private int pdQt;
	@Comment("제품 사진")
	private MultipartFile pdImg;
	@Comment("제품 이미지 경로")
	private String pdUrl;
	@Comment("등록날짜")
	private String regDe;
	@Comment("이미지저장")
	private MultipartFile product_photo;
	
	public int getRm() {
		return rm;
	}
	public void setRm(int rm) {
		this.rm = rm;
	}
	public int getPdNo() {
		return pdNo;
	}
	public void setPdNo(int pdNo) {
		this.pdNo = pdNo;
	}
	public String getPdCg() {
		return pdCg;
	}
	public void setPdCg(String pdCg) {
		this.pdCg = pdCg;
	}
	public String getPdName() {
		return pdName;
	}
	public void setPdName(String pdName) {
		this.pdName = pdName;
	}
	public int getPdPrice() {
		return pdPrice;
	}
	public void setPdPrice(int pdPrice) {
		this.pdPrice = pdPrice;
	}
	public String getPdInfo() {
		return pdInfo;
	}
	public void setPdInfo(String pdInfo) {
		this.pdInfo = pdInfo;
	}
	public int getPdQt() {
		return pdQt;
	}
	public void setPdQt(int pdQt) {
		this.pdQt = pdQt;
	}
	public MultipartFile getPdImg() {
		return pdImg;
	}
	public void setPdImg(MultipartFile pdImg) {
		this.pdImg = pdImg;
	}
	public String getPdUrl() {
		return pdUrl;
	}
	public void setPdUrl(String pdUrl) {
		this.pdUrl = pdUrl;
	}
	public String getRegDe() {
		return regDe;
	}
	public void setRegDe(String regDe) {
		this.regDe = regDe;
	}
	public MultipartFile getProduct_photo() {
		return product_photo;
	}
	public void setProduct_photo(MultipartFile product_photo) {
		this.product_photo = product_photo;
	}
	@Override
	public String toString() {
		return "BookVo [rm=" + rm + ", pdNo=" + pdNo + ", pdCg=" + pdCg + ", pdName=" + pdName + ", pdPrice=" + pdPrice
				+ ", pdInfo=" + pdInfo + ", pdQt=" + pdQt + ", pdImg=" + pdImg + ", pdUrl=" + pdUrl + ", regDe=" + regDe
				+ ", product_photo=" + product_photo + "]";
	}
	
}
