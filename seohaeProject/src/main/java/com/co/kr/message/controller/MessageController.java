package com.co.kr.message.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.co.kr.message.service.MessageService;
import com.co.kr.message.vo.MessageVo;

@Controller
public class MessageController {

	private Log log = LogFactory.getLog(MessageController.class);

	@Autowired
	private MessageService messageService;
	
	/**
	    * @Method MessageList
	    * @Date 2017. 11. 05.
	    * @Writter seohae
	    * @param messageVo
	    * @Discript 받은쪽지함 목록
	    * @Return String
	  */
	@RequestMapping(value="/messageList", method={RequestMethod.GET, RequestMethod.POST})
	public String MessageList(@ModelAttribute MessageVo messageVo, Model model, HttpSession session) {
		
		String userId = (String) session.getAttribute("userId");
		messageVo.setUser_id(userId);
		
		Map<String, Object> map = messageService.messageList(messageVo); 
		
		model.addAttribute("list", map.get("list"));
		return "message/messageList";
	}
	
	/**
	    * @Method MessageList
	    * @Date 2017. 11. 05.
	    * @Writter seohae
	    * @param messageVo
	    * @Discript 받은쪽지함 목록
	    * @Return String
	  */	
	@RequestMapping(value="/messageSendList", method={RequestMethod.GET, RequestMethod.POST})
	public String MessageSendList(@ModelAttribute MessageVo messageVo, Model model, HttpSession session) {
		
		String userId = (String) session.getAttribute("userId");
		messageVo.setUser_id(userId);
		
		Map<String, Object> map = messageService.MessageSendList(messageVo); 
		
		model.addAttribute("list", map.get("list"));
		return "message/messageSendList";
	}
	
	  /**
	    * @Method MessageList
	    * @Date 2017. 11. 05.
	    * @Writter seohae
	    * @param messageVo
	    * @Discript 받은쪽지함 목록
	    * @Return String
	  */
	@RequestMapping(value="/messageWrite", method={RequestMethod.GET, RequestMethod.POST})
	public String MessageWrite() {
		return "message/messageWrite";
	}
	
	/**
	    * @Method MessageSendWrite
	    * @Date 2017. 11. 05.
	    * @Writter seohae
	    * @param messageVo
	    * @Discript 쪽지 작성 페이지
	    * @Return String
	  */
	@RequestMapping(value="/messageSendWrite", method=RequestMethod.POST)
	public String MessageSendWrite(@ModelAttribute("MessageVo") MessageVo messageVo, Model model) {
		System.out.println(messageVo);
		model.addAttribute("sender", messageVo.getMsg_send());
		return "message/messageSendWrite";
	}
	
	/**
	    * @Method MessageWriteSave
	    * @Date 2017. 11. 05.
	    * @Writter seohae
	    * @param messageVo
	    * @Discript 쪽지 전송완료
	    * @Return String
	  */
	@RequestMapping(value="/messageWriteSave", method=RequestMethod.POST)
	@ResponseBody
	public String MessageWriteSave(@ModelAttribute MessageVo messageVo, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		messageVo.setUser_id(userId);
		
		String idCheck = messageVo.getMsg_get();
		int count = messageService.MessageIdCheck(idCheck);
		
		if(count == 0){
			return "fal";
		}
	
		messageService.MessageWriteSave(messageVo);
		
		return "ok";
	}
	
	/**
	    * @Method MessageSendWriteSave
	    * @Date 2017. 11. 05.
	    * @Writter seohae
	    * @param messageVo
	    * @Discript 쪽지 전송완료_받은편지함의 답장보내기
	    * @Return String
	  */
	@RequestMapping(value="/messageSendWriteSave", method=RequestMethod.POST)
	@ResponseBody
	public String MessageSendWriteSave(@ModelAttribute MessageVo messageVo, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		messageVo.setUser_id(userId);
		messageService.MessageWriteSave(messageVo);
		
		return "ok";
	}
	
	/**
	    * @Method MessageDelete
	    * @Date 2017. 11. 05.
	    * @Writter seohae
	    * @param messageVo
	    * @Discript 보낸쪽지함 삭제
	    * @Return String
	  */
	@RequestMapping(value="/messageSendDelete", method=RequestMethod.POST)
	public String MessageDelete(@ModelAttribute MessageVo messageVo) {
		messageService.MessageSendDelete(messageVo);
		return "redirect:/messageSendList";
	}
	
	/**
	    * @Method MessageGetDelete
	    * @Date 2017. 11. 05.
	    * @Writter seohae
	    * @param messageVo
	    * @Discript 받은쪽지함 삭제
	    * @Return String
	  */
	@RequestMapping(value="/messageGetDelete", method=RequestMethod.POST)
	public String MessageGetDelete(@ModelAttribute MessageVo messageVo) {
		messageService.MessageGetDelete(messageVo);
		return "redirect:/messageList";
	}
	
	/**
	    * @Method MessageGetListDel
	    * @Date 2017. 11. 05.
	    * @Writter seohae
	    * @param messageVo
	    * @Discript 받은쪽지 선택삭제
	    * @Return String
	  */
	@RequestMapping(value="/messageGetListDel", method=RequestMethod.POST, consumes="application/json")	 
	public void MessageGetListDel(@RequestBody List<MessageVo> data) {
		messageService.messageGetListDel(data);
	}
	
	/**
	    * @Method MessageSendListDel
	    * @Date 2017. 11. 05.
	    * @Writter seohae
	    * @param messageVo
	    * @Discript 보낸쪽지함 선택삭제
	    * @Return String
	  */
	@RequestMapping(value="/messageSendListDel", method=RequestMethod.POST, consumes="application/json")	 
	public void MessageSendListDel(@RequestBody List<MessageVo> data) {
		System.out.println(data);
		messageService.messageSendListDel(data);
	}
	
	/**
	    * @Method MessageDetail
	    * @Date 2017. 11. 05.
	    * @Writter seohae
	    * @param messageVo
	    * @Discript 받은쪽지 상세정보
	    * @Return String
	  */
	@RequestMapping(value="/messageDetail", method=RequestMethod.POST)
	public String MessageDetail(@ModelAttribute("MessageVo") MessageVo messageVo, Model model, HttpSession session){
		MessageVo list = messageService.messageDetail(messageVo); 
		if(list.getMsg_readyn().equals("N")){
			messageService.messageReadYn(messageVo);
		}
		model.addAttribute("MessageVo", list);
		return "message/messageDetail";
	}
	
	/**
	    * @Method MessageSendDetail
	    * @Date 2017. 11. 05.
	    * @Writter seohae
	    * @param messageVo
	    * @Discript 보낸쪽지 상세정보
	    * @Return String
	  */
	@RequestMapping(value="/messageSendDetail", method=RequestMethod.POST)
	public String MessageSendDetail(@ModelAttribute("MessageVo") MessageVo messageVo, Model model, HttpSession session){
		MessageVo list = messageService.messageDetail(messageVo); 

		model.addAttribute("MessageVo", list);
		return "message/messageSendDetail";
	}

}
