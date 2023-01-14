package com.co.kr.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.co.kr.notice.vo.NoticeVo;
import com.co.kr.notice.vo.UploadFileVo;

@Component("fileUtils")
public class FileUtils {
	 private static final String filePath = "C:\\upload\\";
     
	    public List<UploadFileVo> parseInsertFileInfo(NoticeVo noticeVo, HttpServletRequest request) throws Exception{
	        
	    	
	    	MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
	        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
	         
	        MultipartFile multipartFile = null;
	        String originalFileName = null;
	        String originalFileExtension = null;
	        String storedFileName = null;
	         
	        List<UploadFileVo> list = new ArrayList<UploadFileVo>();
	        UploadFileVo listMap = new UploadFileVo();
	        
	        int boardNo = noticeVo.getNo();
	        String userId = noticeVo.getWriter();
	        File file = new File(filePath);
	        if(file.exists() == false){
	            file.mkdirs();
	        }
	         
	        while(iterator.hasNext()){
	            multipartFile = multipartHttpServletRequest.getFile(iterator.next());
	            if(multipartFile.isEmpty() == false){
	                originalFileName = multipartFile.getOriginalFilename();
	                originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
	                storedFileName = CommonUtils.getRandomString() + originalFileExtension;
	                 
	                file = new File(filePath + storedFileName);
	                multipartFile.transferTo(file);
	                
	                listMap = new UploadFileVo();
	                listMap.setBoardNo(boardNo);
	                listMap.setoName(originalFileName);
	                listMap.setFileName(storedFileName);
	                listMap.setFileSize(multipartFile.getSize());
	                listMap.setUserId(userId);
	                list.add(listMap);
	            }
	        }
	        return list;
	    }
	    public List<Map<String, Object>> parseUpdateFileInfo(Map<String, Object> map, HttpServletRequest request) throws Exception{
	    	MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
	        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
	         
	        MultipartFile multipartFile = null;
	        String originalFileName = null;
	        String originalFileExtension = null;
	        String storedFileName = null;
	         
	        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	        Map<String, Object> listMap = null; 
	        
	        String userId = (String) map.get("userId");
	        String boardIdx = (String)map.get("no");
	        String requestName = null;
	        String idx = null;
	         
	         
	        while(iterator.hasNext()){
	            multipartFile = multipartHttpServletRequest.getFile(iterator.next());
	            if(multipartFile.isEmpty() == false){
	                originalFileName = multipartFile.getOriginalFilename();
	                originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
	                storedFileName = CommonUtils.getRandomString() + originalFileExtension;
	                 
	                multipartFile.transferTo(new File(filePath + storedFileName));
	                 
	                listMap = new HashMap<String,Object>();
	                listMap.put("userId", userId);
	                listMap.put("IS_NEW", "Y");
	                listMap.put("boardNo", boardIdx);
	                listMap.put("oName", originalFileName);
	                listMap.put("fileName", storedFileName);
	                listMap.put("fileSize", multipartFile.getSize());
	                list.add(listMap);
	            }
	            else{
	                requestName = multipartFile.getName();
	                idx = "IDX_"+requestName.substring(requestName.indexOf("_")+1);
	                if(map.containsKey(idx) == true && map.get(idx) != null){
	                    listMap = new HashMap<String,Object>();
	                    listMap.put("IS_NEW", "N");
	                    listMap.put("no", map.get(idx));
	                    list.add(listMap);
	                }
	            }
	        }
	        return list;
	    }
}	
