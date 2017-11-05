package com.co.kr.notice.vo;

public class UploadFileVo {
	private int boardNo;
    private String oName;
    private String fileName;
    private long fileSize;
    private String regDe;
    private String userId;
    private String useYn;
    private int no;
    private String isNew;
    
    
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getIsNew() {
		return isNew;
	}
	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	public String getoName() {
		return oName;
	}
	public void setoName(String oName) {
		this.oName = oName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getRegDe() {
		return regDe;
	}
	public void setRegDe(String regDe) {
		this.regDe = regDe;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	
	@Override
	public String toString() {
		return "UploadFileVo [boardNo=" + boardNo + ", oName=" + oName + ", fileName=" + fileName + ", fileSize="
				+ fileSize + ", regDe=" + regDe + ", userId=" + userId + ", useYn=" + useYn + ", no=" + no + ", isNew="
				+ isNew + "]";
	}
	
    
}
