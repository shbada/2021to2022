package com.co.kr.admin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.co.kr.admin.service.AdminService;
import com.co.kr.bino.vo.BinoVo;
import com.co.kr.user.vo.UserVo;

/**
 * AdminController
 * 
 * @author 김서해
 * @since 2017. 11. 11.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>

 * 1. (2017. 11. 11 / seohae / 최초생성)
 *
 * </pre>
 */

@Controller
public class AdminController {
	
	private Log log = LogFactory.getLog(AdminController.class);
	
	@Autowired
	AdminService adminService;
	
	/**
	    * @Method BookList
	    * @Date 2017. 11. 05.
	    * @Writter seohae
	    * @Discript 판매교재 목록
	    * @Return String
	  */
	
	@RequestMapping("/allMemberList")
	public String allMemberList(Model model) throws Exception {
		List<UserVo> list = adminService.allMemberList();
		model.addAttribute("list", list);
		
		return "admin/allMemberList";
	}

	@RequestMapping(value="/memberDetail", method=RequestMethod.POST)
	public String MemberDetail(@ModelAttribute("UserVo") UserVo userVo, Model model, HttpSession session){
		UserVo list = adminService.memberDetail(userVo); 
		model.addAttribute("UserVo", list);
		
		return "admin/memberDetail";
	}
	
	@RequestMapping(value="/memberDelete", method=RequestMethod.POST)
	public String MemberDelete(@ModelAttribute("UserVo") UserVo userVo, Model model, HttpSession session){
		adminService.memberDelete(userVo); 
		return "redirect:/allMemberList.do";
	}
	
	@RequestMapping(value="/memberDown", method=RequestMethod.POST)
	public String MemberDown(@ModelAttribute("UserVo") UserVo userVo, Model model, HttpSession session){
		adminService.memberDown(userVo); 
		return "redirect:/allMemberList.do";
	}
	
	@RequestMapping(value="/memberUp", method=RequestMethod.POST)
	public String MemberUp(@ModelAttribute("UserVo") UserVo userVo, Model model, HttpSession session){
		adminService.memberUp(userVo); 
		return "redirect:/allMemberList.do";
	}
	
	@RequestMapping(value="/allBinoList")
	public String BinoList(@ModelAttribute BinoVo binoVo, Model model) {
		model.addAttribute("binoList", adminService.binoList());
		return "admin/allBinoList";
	}
}
