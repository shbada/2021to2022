/**
 * MainController
 * 
 * @author 김서해
 * @since 2017. 11. 02.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>

 * 1. (2017. 11. 02 / seohae / 최초생성)
 *
 * </pre>
 */

package com.co.kr.notice.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.co.kr.notice.service.NoticeService;
import com.co.kr.notice.vo.NoticeVo;
import com.co.kr.notice.vo.UploadFileVo;

@Controller
public class NoticeController {
	
	private Log log = LogFactory.getLog(NoticeController.class);
	
	@Autowired
	private NoticeService noticeService;
	
	/**
	    * @Method NoticeList
	    * @Date 2017. 11. 05.
	    * @Writter seohae
	    * @Param NoticeVo
	    * @Discript 공지사항 리스트
	    * @Return String
	  */

	@RequestMapping(value = "/noticeList")
	public String noticeList(@ModelAttribute NoticeVo noticeVo, Model model) {
		
		int totalCount=noticeService.getListBaordCount(noticeVo);
		
		if(noticeVo.getPageCnt()==null) noticeVo.setPageSize(10);
	    else noticeVo.setPageSize(Integer.parseInt(noticeVo.getPageCnt()));
	      
	    noticeVo.setTotalCount(totalCount);
	    model.addAttribute("pageVO", noticeVo);
	    
		model.addAttribute("noticeList",noticeService.selectNotice(noticeVo));
		return "/notice/noticeList";
	}
	
	/**
	    * @Method NoticeWrite
	    * @Date 2017. 11. 05.
	    * @Writter seohae
	    * @Discript 공지사항 작성글
	    * @Return String
	  */
	
	@RequestMapping(value="/noticeWrite")
	public String NoticeWrite(){
		return "/notice/noticeEdit";
	}
	
	/**
	    * @Method NoticeInfo
	    * @Date 2017. 11. 05.
	    * @Writter seohae
	    * @param NoticeVo
	    * @Discript 공지사항 글 상세정보
	    * @Return String
	  */
	
	@RequestMapping(value="/noticeInfo")
	public String NoticeInfo(@ModelAttribute NoticeVo noticeVo,@RequestParam int no,Model model,HttpSession session){
		Map<String, Object> map = noticeService.noticeInfo(no);
		noticeService.noticeUpdateCnt(no,session);
		model.addAttribute("detail",map.get("detail"));
		model.addAttribute("list",map.get("list"));
	 return "/notice/noticeInfo";	
	}
	
	/**
	    * @Method InsertNotice
	    * @Date 2017. 11. 05.
	    * @Writter seohae
	    * @param NoticeVo
	    * @Discript 공지사항 글 작성
	    * @Return String
	  */
	@RequestMapping(value="/insertNotice",method=RequestMethod.POST)
	public String InsertNotice(@ModelAttribute NoticeVo noticeVo, HttpServletRequest request) throws Exception{
		
		noticeService.insertNotice(noticeVo, request);
	    return "redirect:/noticeList.do";
	}
	
	/**
	    * @Method DownloadFile
	    * @Date 2017. 11. 05.
	    * @Writter seohae
	    * @param no
	    * @Discript 파일 다운로드
	    * @Return String
	  */
	@RequestMapping(value="downloadFile")
	public void DownloadFile(@RequestParam int no, HttpServletResponse response) throws IOException{
		UploadFileVo uploadFileVo = noticeService.downloadFile(no);
		System.out.println(uploadFileVo);
	    String storedFileName = uploadFileVo.getFileName();
	    String originalFileName = uploadFileVo.getoName();
	     
	    byte fileByte[] = FileUtils.readFileToByteArray(new File("C:\\upload\\"+storedFileName));
	     
	    response.setContentType("application/octet-stream");
	    response.setContentLength(fileByte.length);
	    response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(originalFileName,"UTF-8")+"\";");
	    response.setHeader("Content-Transfer-Encoding", "binary");
	    response.getOutputStream().write(fileByte);
	     
	    response.getOutputStream().flush();
	    response.getOutputStream().close();
	}
	
	/**
	    * @Method OpenNoticeUpdate
	    * @Date 2017. 11. 05.
	    * @Writter seohae
	    * @param no
	    * @param noticeVo
	    * @Discript 공지사항 글 정보 가져오기
	    * @Return String
	  */
	@RequestMapping(value="/openNoticeUpdate")
	public String OpenNoticeUpdate(@ModelAttribute NoticeVo noticeVo,@RequestParam int no,Model model){
		Map<String, Object> map = noticeService.noticeInfo(no);
		model.addAttribute("detail",map.get("detail"));
		model.addAttribute("list",map.get("list"));
	 return "/notice/noticeUpdate";	
	}
	
	/**
	    * @Method NoticeUpdate
	    * @Date 2017. 11. 05.
	    * @Writter seohae
	    * @param map
	    * @param noticeVo
	    * @Discript 공지사항 글 수정완료
	    * @Return String
	  */
	@RequestMapping(value="noticeUpdate")
	public String NoticeUpdate(@ModelAttribute NoticeVo noticeVo, @RequestParam Map<String, Object> map, HttpServletRequest request, Model model, RedirectAttributes reAttributes) throws Exception{
		System.out.println("@@@@@@@@@@@@@@"+map);
		HttpSession session = request.getSession(false);
    	String userId = (String) session.getAttribute("userId");
    	map.put("userId", userId);
		noticeService.noticeUpdate(map,request);
		reAttributes.addAttribute("no",noticeVo.getNo());
		
		return "redirect:/noticeInfo.do";	
	}
	
	/*@RequestMapping(value="/notice/deleteNotice.do")
	public String deleteNotice(@RequestParam(value="no")Integer no){
		dao.deleteNotice(no);
		return "redirect:/notice/notice.do";
	}
	*/
	
	/**
	    * @Method NoticeViewCnt
	    * @Date 2017. 11. 05.
	    * @Writter seohae
	    * @param no
	    * @Discript 공지글 조회수
	    * @Return String
	  */
	@ResponseBody
	@RequestMapping(value="noticeViewCnt")
	public int NoticeViewCnt(@RequestParam int no){
		int resultCnt = noticeService.noticeViewCnt(no);
		return resultCnt;
	}
	
	/**
	    * @Method NoticeLike
	    * @Date 2017. 11. 05.
	    * @Writter seohae
	    * @param noticeVo
	    * @Discript 공지글 추천수 증가
	    * @Return String
	  */
	@ResponseBody
	@RequestMapping(value="noticeLike")
	public String NoticeLike(@ModelAttribute NoticeVo noticeVo, HttpSession session){
		noticeVo.setWriter((String)session.getAttribute("userId"));
		NoticeVo noticeLike = noticeService.noticeLike(noticeVo);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>"+noticeLike);
		if(noticeLike == null){
			noticeService.insertNoticeLike(noticeVo);
			return "ok";
		}else return "false";
	}
	
	/**
	    * @Method NoticeLikeCnt
	    * @Date 2017. 11. 05.
	    * @Writter seohae
	    * @param noticeVo
	    * @Discript 공지글 추천수 가져오기
	    * @Return String
	  */
	@ResponseBody  
	@RequestMapping(value="noticeLikeCnt", method=RequestMethod.GET)
	public int NoticeLikeCnt(@RequestParam int no){
		int resultCnt = noticeService.noticeLikeCnt(no);
		return resultCnt;
	}
	
}
