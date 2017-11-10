/**
 * BinoController
 * 
 * @author 김서해
 * @since 2017. 11. 10.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>

 * 1. (2017. 11. 10 / seohae / 최초생성)
 *
 * </pre>
 */

package com.co.kr.bino.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BinoController {
	
	private Log log = LogFactory.getLog(BinoController.class);
	
	/**
	    * @Method BinoList
	    * @Date 2017. 11. 10
	    * @Writter seohae
	    * @Discript bino 내역 목록
	    * @Return String
	  */
	
	@RequestMapping(value="/binoList")
	public String BinoList() {
		return "bino/binoList";
	}
}
