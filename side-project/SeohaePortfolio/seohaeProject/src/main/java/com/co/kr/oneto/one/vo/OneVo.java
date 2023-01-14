package com.co.kr.oneto.one.vo;

import com.co.kr.common.annotaion.Comment;
import com.co.kr.common.vo.PageVo;

public class OneVo extends PageVo{
	/** 질문 */
	@Comment("글번호")
	private int reqNo;
	@Comment("글제목")
	private String reqTitle;
	@Comment("작성자 ID")
	private String userId;
	@Comment("글 내용")
	private String reqDesc;
	@Comment("답변여부")
	private String reqReYn;
	@Comment("등록날짜")
	private String regDe;
	@Comment("페이지 갯수")
	private String pageCnt;
	
	/** 답변 */
	@Comment("답변글번호")
	private int reqReNo;
	@Comment("글제목")
	private String reqReDesc;
	@Comment("사용여부")
	private String useYn;
	@Comment("등록날짜")
	private String regReDe;
	@Comment("페이지 갯수")
	private String pageCnt2;
	public int getReqNo() {
		return reqNo;
	}
	public void setReqNo(int reqNo) {
		this.reqNo = reqNo;
	}
	public String getReqTitle() {
		return reqTitle;
	}
	public void setReqTitle(String reqTitle) {
		this.reqTitle = reqTitle;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getReqDesc() {
		return reqDesc;
	}
	public void setReqDesc(String reqDesc) {
		this.reqDesc = reqDesc;
	}
	public String getReqReYn() {
		return reqReYn;
	}
	public void setReqReYn(String reqReYn) {
		this.reqReYn = reqReYn;
	}
	public String getRegDe() {
		return regDe;
	}
	public void setRegDe(String regDe) {
		this.regDe = regDe;
	}
	public String getPageCnt() {
		return pageCnt;
	}
	public void setPageCnt(String pageCnt) {
		this.pageCnt = pageCnt;
	}
	public int getReqReNo() {
		return reqReNo;
	}
	public void setReqReNo(int reqReNo) {
		this.reqReNo = reqReNo;
	}
	public String getReqReDesc() {
		return reqReDesc;
	}
	public void setReqReDesc(String reqReDesc) {
		this.reqReDesc = reqReDesc;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getRegReDe() {
		return regReDe;
	}
	public void setRegReDe(String regReDe) {
		this.regReDe = regReDe;
	}
	public String getPageCnt2() {
		return pageCnt2;
	}
	public void setPageCnt2(String pageCnt2) {
		this.pageCnt2 = pageCnt2;
	}
	@Override
	public String toString() {
		return "OneVo [reqNo=" + reqNo + ", reqTitle=" + reqTitle + ", userId=" + userId + ", reqDesc=" + reqDesc
				+ ", reqReYn=" + reqReYn + ", regDe=" + regDe + ", pageCnt=" + pageCnt + ", reqReNo=" + reqReNo
				+ ", reqReDesc=" + reqReDesc + ", useYn=" + useYn + ", regReDe=" + regReDe + ", pageCnt2=" + pageCnt2
				+ "]";
	}
	
}