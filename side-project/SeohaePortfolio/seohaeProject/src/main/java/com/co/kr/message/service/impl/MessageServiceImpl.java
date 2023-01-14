package com.co.kr.message.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.kr.message.dao.MessageDao;
import com.co.kr.message.service.MessageService;
import com.co.kr.message.vo.MessageVo;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageDao messageDao;

	@Override
	public void MessageWriteSave(MessageVo messageVo) {
		messageDao.MessageWriteSave(messageVo);
	}

	@Override
	public Map<String, Object> messageList(MessageVo messageVo) {
		List<MessageVo> list = messageDao.messageList(messageVo); 
		System.out.println(list);

		Map<String, Object> map = new HashMap<String, Object>(); 
		
		map.put("list", list);
		
		return map;
	}

	@Override
	public Map<String, Object> MessageSendList(MessageVo messageVo) {
		List<MessageVo> list = messageDao.MessageSendList(messageVo); 
		Map<String, Object> map = new HashMap<String, Object>(); 
		
		map.put("list", list);
		
		return map;
	}

	@Override
	public void MessageSendDelete(MessageVo messageVo) {
		messageDao.MessageSendDelete(messageVo);
	}

	@Override
	public void MessageGetDelete(MessageVo messageVo) {
		messageDao.MessageGetDelete(messageVo);
	}

	@Override
	public void messageGetListDel(List<MessageVo> data) {
		for(int i=0; i<data.size(); i++)
			messageDao.messageGetListDel(data.get(i));
	}

	@Override
	public void messageSendListDel(List<MessageVo> data) {
		for(int i=0; i<data.size(); i++)
			messageDao.messageSendListDel(data.get(i));
	}

	@Override
	public MessageVo messageDetail(MessageVo messageVo) {
		return messageDao.messageDetail(messageVo);
	}

	@Override
	public void messageReadYn(MessageVo messageVo) {
		messageDao.messageReadYn(messageVo);
	}

	@Override
	public int MessageIdCheck(String idCheck) {
		return messageDao.MessageIdCheck(idCheck);
	}

	@Override
	public int messageNewCount(String user_id) {
		return messageDao.messageNewCount(user_id);
	}

}
