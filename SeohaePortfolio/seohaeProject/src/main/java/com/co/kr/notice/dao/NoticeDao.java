package com.co.kr.notice.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.co.kr.common.dao.AbstractDAO;
import com.co.kr.notice.vo.NoticeVo;
import com.co.kr.notice.vo.UploadFileVo;
import com.co.kr.user.vo.UserVo;

@Repository
public class NoticeDao extends AbstractDAO{
	
	public List<NoticeVo> selectNotice(NoticeVo noticeVo) {
		return selectList("noticeSql.selectNotice", noticeVo);
	}

	public int getListBaordCount(NoticeVo noticeVo) {
		return (int)selectOne("noticeSql.getListBaordCount", noticeVo);
	}

	public NoticeVo noticeInfo(int no) {
		return (NoticeVo) selectOne("noticeSql.noticeInfo",no);
	}

	public void insertNotice(NoticeVo noticeVo) {
		insert("noticeSql.insertNotice", noticeVo);
		
	}

	public void insertFile(UploadFileVo uploadFileVo) {
		insert("noticeSql.insertFile", uploadFileVo);
	}

	public List<UploadFileVo> selectFileList(int no) {
		return selectList("noticeSql.selectFileList",no);
	}

	public UploadFileVo downloadFile(int no) {
		return (UploadFileVo) selectOne("noticeSql.downloadFile",no);
	}

	public void noticeUpdate(Map<String, Object> map) {
		update("noticeSql.noticeUpdate", map);
	}

	public void deleteFileList(Map<String, Object> map) {
		update("noticeSql.deleteFileList", map);
	}

	public void insertFile(Map<String, Object> tempMap) {
		insert("noticeSql.insertFile2",tempMap);
	}

	public void updateFile(Map<String, Object> tempMap) {
		update("noticeSql.updateFile",tempMap);
	}

	public UserVo findAccount(String email) {
		return (UserVo)selectOne("noticeSql.findAccount",email);
	}

	public void userChangePwUpdate(UserVo userVo) {
		update("noticeSql.userChangePwUpdate", userVo);
	}

	public int noticeViewCnt(int no) {
		return (int)selectOne("noticeSql.noticeViewCnt",no);
	}

	public void noticeUpdateCnt(int no) {
		update("noticeSql.noticeUpdateCnt", no);
	}

	public NoticeVo noticeLike(NoticeVo noticeVo) {
		return (NoticeVo)selectOne("noticeSql.noticeLike",noticeVo);
	}

	public void insertNoticeLike(NoticeVo noticeVo) {
		insert("noticeSql.insertNoticeLike", noticeVo);
	}

	public int noticeLikeCnt(int no) {
		return (int)selectOne("noticeSql.noticeLikeCnt",no);
	}

}
