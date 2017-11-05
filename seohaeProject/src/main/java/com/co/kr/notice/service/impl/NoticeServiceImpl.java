package com.co.kr.notice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.kr.common.util.FileUtils;
import com.co.kr.notice.dao.NoticeDao;
import com.co.kr.notice.service.NoticeService;
import com.co.kr.notice.vo.NoticeVo;
import com.co.kr.notice.vo.UploadFileVo;


@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeDao noticeDao;
	
	@Autowired
    private FileUtils fileUtils;
	
	@Override
	public List<NoticeVo> selectNotice(NoticeVo noticeVo) {
		return noticeDao.selectNotice(noticeVo);
	}

	@Override
	public int getListBaordCount(NoticeVo noticeVo) {
		return noticeDao.getListBaordCount(noticeVo);
	}

	@Override
	public Map<String, Object> noticeInfo(int no) {
		Map<String, Object> resultMap = new HashMap<String,Object>();
		NoticeVo detail = noticeDao.noticeInfo(no);
	    resultMap.put("detail", detail);
		     
		List<UploadFileVo> list = noticeDao.selectFileList(no);
	    resultMap.put("list", list);
		return resultMap;
	}

	@Override	
	public void insertNotice(NoticeVo noticeVo, HttpServletRequest request) throws Exception{
		noticeDao.insertNotice(noticeVo);
        
		/*MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
	    Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
	    MultipartFile multipartFile = null;
	    while(iterator.hasNext()){
	        multipartFile = multipartHttpServletRequest.getFile(iterator.next());
	        if(multipartFile.isEmpty() == false){
	        	System.out.println("------------- file start -------------");
	            System.out.println("name : "+multipartFile.getName());
	            System.out.println("filename : "+multipartFile.getOriginalFilename());
	            System.out.println("size : "+multipartFile.getSize());
	            System.out.println("-------------- file end --------------\n");
	        }
	    }*/
	    
        List<UploadFileVo> list = fileUtils.parseInsertFileInfo(noticeVo, request);
        for(int i=0, size=list.size(); i<size; i++){
            noticeDao.insertFile(list.get(i));
        }
	}

	@Override
	public UploadFileVo downloadFile(int no) {
		return noticeDao.downloadFile(no);
	}

	@Override
	public void noticeUpdate(Map<String, Object>map, HttpServletRequest request) throws Exception{
		noticeDao.noticeUpdate(map);
		
		/*noticeDao.deleteFileList(map);*/
		List<Map<String, Object>> list = fileUtils.parseUpdateFileInfo(map, request);
	    Map<String,Object> tempMap = null;
	    for(int i=0, size=list.size(); i<size; i++){
	        tempMap = list.get(i);
	        if(tempMap.get("IS_NEW").equals("Y")){
	        	noticeDao.insertFile(tempMap);
	        }
	        else{
	        	noticeDao.updateFile(tempMap);
	        }
	    }
	}

	@Override
	public int noticeViewCnt(int no) {
		return noticeDao.noticeViewCnt(no);
	}

	@Override
	public void noticeUpdateCnt(int no, HttpSession session) {
		long viewTime = 0;
		
		// 세션에 저장된 조회 시간
		if(session.getAttribute("viewTime" + no) != null){
			viewTime = (Long)session.getAttribute("viewTime" + no);
		}
		
		// 시스템의 현재 시간
		long nowTime = System.currentTimeMillis();
		System.out.println("sadjfkladsjfkladsjfklasdjflkasdjflk"+nowTime);
		// 셋팅된 시간이 경과 후 조회수 증가(60초 후 조회수 증가)
		if(nowTime - viewTime > 60*1000){
			noticeDao.noticeUpdateCnt(no);
			session.setAttribute("viewTime" + no, nowTime);
		}
	}

	@Override
	public NoticeVo noticeLike(NoticeVo noticeVo) {
		return noticeDao.noticeLike(noticeVo);
	}

	@Override
	public void insertNoticeLike(NoticeVo noticeVo) {
		noticeDao.insertNoticeLike(noticeVo);
	}

	@Override
	public int noticeLikeCnt(int no) {
		return noticeDao.noticeLikeCnt(no);
	}

	
}
