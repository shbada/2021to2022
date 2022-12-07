package com.co.kr.notice.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.co.kr.notice.vo.NoticeVo;
import com.co.kr.notice.vo.UploadFileVo;

public interface NoticeService {
	
	public Map<String, Object> noticeInfo(int no);

	public void insertNotice(NoticeVo noticeVo, HttpServletRequest request) throws Exception;

	public UploadFileVo downloadFile(int no);

	public void noticeUpdate(Map<String, Object> map, HttpServletRequest request) throws Exception;

	public int noticeViewCnt(int no);

	public void noticeUpdateCnt(int no, HttpSession session);

	public NoticeVo noticeLike(NoticeVo noticeVo);

	public void insertNoticeLike(NoticeVo noticeVo);

	public int noticeLikeCnt(int no);

	public List<NoticeVo> selectNotice(NoticeVo noticeVo);

	public int getListBaordCount(NoticeVo noticeVo);
}
