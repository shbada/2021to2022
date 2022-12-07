package com.co.kr.review.vo;

import org.springframework.web.multipart.MultipartFile;

import com.co.kr.common.annotaion.Comment;
import com.co.kr.common.vo.PageVo;

public class ReviewVo extends PageVo{
	
	private int rm;
	@Comment("리뷰 번호")
	private int no;
	@Comment("제품 번호")
	private int pdNo; 
	private String subject;
	private String writer;
	private String contents;
	@Comment("올리는 사진")
	private MultipartFile img;
	@Comment("올린 사진 변한 이름")
	private String url;
	private String regDe;
	private String pageCnt;
	private String search;
	private int reviewLevel;
	
	
	public int getRm() {
		return rm;
	}
	public void setRm(int rm) {
		this.rm = rm;
	}
	public String getPageCnt() {
		return pageCnt;
	}
	public void setPageCnt(String pageCnt) {
		this.pageCnt = pageCnt;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public int getPdNo() {
		return pdNo;
	}
	public void setPdNo(int pdNo) {
		this.pdNo = pdNo;
	}
	public MultipartFile getImg() {
		return img;
	}
	public void setImg(MultipartFile img) {
		this.img = img;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRegDe() {
		return regDe;
	}
	public void setRegDe(String regDe) {
		this.regDe = regDe;
	}
	
	
	public int getReviewLevel() {
		return reviewLevel;
	}
	public void setReviewLevel(int reviewLevel) {
		this.reviewLevel = reviewLevel;
	}
	@Override
	public String toString() {
		return "ReviewVo [rm=" + rm + ", no=" + no + ", pdNo=" + pdNo + ", subject=" + subject + ", writer=" + writer
				+ ", contents=" + contents + ", img=" + img + ", url=" + url + ", regDe=" + regDe + ", pageCnt="
				+ pageCnt + ", search=" + search + ", reviewLevel=" + reviewLevel + "]";
	}
	
}

