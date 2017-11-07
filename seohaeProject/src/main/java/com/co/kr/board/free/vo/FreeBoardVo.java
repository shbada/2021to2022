package com.co.kr.board.free.vo;

import com.co.kr.common.annotaion.Comment;
import com.co.kr.common.vo.PageVo;

public class FreeBoardVo extends PageVo{
	@Comment("글번호")
	private int freeBorIdx;
	@Comment("글제목")
	private String freeBorTitle;
	@Comment("작성자 이름")
	private String userNm;
	@Comment("작성자 ID")
	private String userId;
	@Comment("글 내용")
	private String freeBorContents;
	@Comment("조회수")
	private int freeBorViewCnt;
	@Comment("수정자 ID")
	private String updateId;
	@Comment("수정날짜")
	private String updateDe;
	@Comment("등록날짜")
	private String regDe;
	@Comment("삭제날짜")
	private String delDe;
	@Comment("삭제자 ID")
	private String delId;
	@Comment("사용여부")
	private String userYn;
	@Comment("페이지 갯수")
	private String pageCnt;
	@Comment("검색어")
	private String search;
	@Comment("추천수")
	private int freeBorLike;
	@Comment("댓글개수")
	private int recent;
	@Comment("검색어번호")
	private int searchWordIdx;
	@Comment("검색어")
	private String searchWord;
	
	public int getFreeBorIdx() {
		return freeBorIdx;
	}
	public void setFreeBorIdx(int freeBorIdx) {
		this.freeBorIdx = freeBorIdx;
	}
	public String getFreeBorTitle() {
		return freeBorTitle;
	}
	public void setFreeBorTitle(String freeBorTitle) {
		this.freeBorTitle = freeBorTitle;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFreeBorContents() {
		return freeBorContents;
	}
	public void setFreeBorContents(String freeBorContents) {
		this.freeBorContents = freeBorContents;
	}
	public int getFreeBorViewCnt() {
		return freeBorViewCnt;
	}
	public void setFreeBorViewCnt(int freeBorViewCnt) {
		this.freeBorViewCnt = freeBorViewCnt;
	}
	public String getUpdateId() {
		return updateId;
	}
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	public String getUpdateDe() {
		return updateDe;
	}
	public void setUpdateDe(String updateDe) {
		this.updateDe = updateDe;
	}
	public String getRegDe() {
		return regDe;
	}
	public void setRegDe(String regDe) {
		this.regDe = regDe;
	}
	public String getDelDe() {
		return delDe;
	}
	public void setDelDe(String delDe) {
		this.delDe = delDe;
	}
	public String getDelId() {
		return delId;
	}
	public void setDelId(String delId) {
		this.delId = delId;
	}
	public String getUserYn() {
		return userYn;
	}
	public void setUserYn(String userYn) {
		this.userYn = userYn;
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
	public int getFreeBorLike() {
		return freeBorLike;
	}
	public void setFreeBorLike(int freeBorLike) {
		this.freeBorLike = freeBorLike;
	}
	public int getRecent() {
		return recent;
	}
	public void setRecent(int recent) {
		this.recent = recent;
	}
	
	public int getSearchWordIdx() {
		return searchWordIdx;
	}
	public void setSearchWordIdx(int searchWordIdx) {
		this.searchWordIdx = searchWordIdx;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	@Override
	public String toString() {
		return "FreeBoardVo [freeBorIdx=" + freeBorIdx + ", freeBorTitle=" + freeBorTitle + ", userNm=" + userNm
				+ ", userId=" + userId + ", freeBorContents=" + freeBorContents + ", freeBorViewCnt=" + freeBorViewCnt
				+ ", updateId=" + updateId + ", updateDe=" + updateDe + ", regDe=" + regDe + ", delDe=" + delDe
				+ ", delId=" + delId + ", userYn=" + userYn + ", pageCnt=" + pageCnt + ", search=" + search
				+ ", freeBorLike=" + freeBorLike + ", recent=" + recent + ", searchWordIdx=" + searchWordIdx
				+ ", searchWord=" + searchWord + "]";
	}	
	
}
