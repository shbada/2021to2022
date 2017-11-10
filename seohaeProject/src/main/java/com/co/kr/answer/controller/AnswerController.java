package com.co.kr.answer.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.co.kr.book.service.BookService;

/**
 * BookController
 * 
 * @author 김서해
 * @since 2017. 11. 05.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>

 * 1. (2017. 11. 05 / seohae / 최초생성)
 *
 * </pre>
 */

@Controller
public class AnswerController {
	
	private Log log = LogFactory.getLog(AnswerController.class);
	
	@Autowired
	BookService bookService;
	
	/**
	    * @Method BookList
	    * @Date 2017. 11. 05.
	    * @Writter seohae
	    * @Discript 판매교재 목록
	    * @Return String
	  */
	
	@RequestMapping(value="/answerJavaList")
	public String BookList() {
		return "question/answer/answerList";
	}
}
