package com.co.kr.notice.vo;

import com.co.kr.common.annotaion.Comment;
import com.co.kr.common.vo.PageVo;

public class NoticeVo extends PageVo{
	
	@Comment("사용여부")
	private int rm;
	@Comment("공지글 번호")
	private int no;
	@Comment("공지글 제목")
	private String title;
	@Comment("공지글 내용")
	private String contents;
	@Comment("작성자")
	private String writer;
	@Comment("이미지")
	private String img;
	@Comment("등록날짜")
	private String regdate;
	@Comment("사용여부")
	private String useYn;
	@Comment("조회수")
	private int viewCnt;
	@Comment("추천수")
	private int likeCnt;
	@Comment("검색어")
	private String searchView;
	@Comment("파일 번호")
	private int fileNo;
	@Comment("댓글수")
	private int replyCnt;
	@Comment("페이징")
	private String pageCnt;
	@Comment("수정자 아이디")
	private String updateId;
	
	
	public int getReplyCnt() {
		return replyCnt;
	}
	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}
	public String getUpdateId() {
		return updateId;
	}
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	public int getLikeCnt() {
		return likeCnt;
	}
	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public int getRm() {
		return rm;
	}
	public void setRm(int rm) {
		this.rm = rm;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public String getPageCnt() {
		return pageCnt;
	}
	public void setPageCnt(String pageCnt) {
		this.pageCnt = pageCnt;
	}
	public String getSearchView() {
		return searchView;
	}
	public void setSearchView(String searchView) {
		this.searchView = searchView;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "NoticeVo [rm=" + rm + ", no=" + no + ", title=" + title + ", contents=" + contents + ", writer="
				+ writer + ", img=" + img + ", regdate=" + regdate + ", useYn=" + useYn + ", viewCnt=" + viewCnt
				+ ", likeCnt=" + likeCnt + ", searchView=" + searchView + ", fileNo=" + fileNo + ", replyCnt="
				+ replyCnt + ", pageCnt=" + pageCnt + ", updateId=" + updateId + "]";
	}
	
}
