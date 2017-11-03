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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NoticeController {
	
	private Log log = LogFactory.getLog(NoticeController.class);
	
	/**
	    * @Method NoticeList
	    * @Date 2017. 11. 03.
	    * @Writter seohae
	    * @Discript 공지사항 리스트
	    * @Return String
	  */
	
	@RequestMapping(value="/noticeList")
	public String NoticeList() {
		return "notice/noticeList";
	}
	
}
