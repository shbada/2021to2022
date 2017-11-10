package com.co.kr.question.vo;

import com.co.kr.common.annotaion.Comment;
import com.co.kr.common.vo.PageVo;

public class QuestionVo extends PageVo{
	@Comment("질문글 번호")
	private int qIdx;
	@Comment("질문글 종류")
	private String qCg;
	@Comment("질문글 제목")
	private String qTitle;
	@Comment("질문글 내용")
	private String qDesc;
	@Comment("조회수")
	private int qViewCnt;
	@Comment("작성자 아이디")
	private String qUserId;
	@Comment("작성날짜")
	private String qRegDe;
	@Comment("삭제여부")
	private String qDelYn;
	@Comment("답변글 채택 여부")
	private String qAnsYn;
	@Comment("페이징")
	private String pageCnt;
	@Comment("검색어")
	private String searchView;
	
	public int getqIdx() {
		return qIdx;
	}
	public void setqIdx(int qIdx) {
		this.qIdx = qIdx;
	}
	public String getqCg() {
		return qCg;
	}
	public void setqCg(String qCg) {
		this.qCg = qCg;
	}
	public String getqTitle() {
		return qTitle;
	}
	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}
	public String getqDesc() {
		return qDesc;
	}
	public void setqDesc(String qDesc) {
		this.qDesc = qDesc;
	}
	public int getqViewCnt() {
		return qViewCnt;
	}
	public void setqViewCnt(int qViewCnt) {
		this.qViewCnt = qViewCnt;
	}
	
	public String getqUserId() {
		return qUserId;
	}
	public void setqUserId(String qUserId) {
		this.qUserId = qUserId;
	}

	public String getqRegDe() {
		return qRegDe;
	}
	public void setqRegDe(String qRegDe) {
		this.qRegDe = qRegDe;
	}
	public String getqDelYn() {
		return qDelYn;
	}
	public void setqDelYn(String qDelYn) {
		this.qDelYn = qDelYn;
	}
	public String getqAnsYn() {
		return qAnsYn;
	}
	public void setqAnsYn(String qAnsYn) {
		this.qAnsYn = qAnsYn;
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
	@Override
	public String toString() {
		return "QuestionVo [qIdx=" + qIdx + ", qCg=" + qCg + ", qTitle=" + qTitle + ", qDesc=" + qDesc + ", qViewCnt="
				+ qViewCnt + ", qUserId=" + qUserId + ", qRegDe=" + qRegDe + ", qDelYn=" + qDelYn + ", qAnsYn=" + qAnsYn
				+ ", pageCnt=" + pageCnt + ", searchView=" + searchView + "]";
	}
}
