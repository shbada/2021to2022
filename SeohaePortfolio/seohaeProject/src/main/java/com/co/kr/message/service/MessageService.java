package com.co.kr.message.service;

import java.util.List;
import java.util.Map;

import com.co.kr.message.vo.MessageVo;

public interface MessageService {

	void MessageWriteSave(MessageVo messageVo);

	Map<String, Object> messageList(MessageVo messageVo);

	Map<String, Object> MessageSendList(MessageVo messageVo);

	void MessageSendDelete(MessageVo messageVo);

	void MessageGetDelete(MessageVo messageVo);

	void messageGetListDel(List<MessageVo> data);

	void messageSendListDel(List<MessageVo> data);

	MessageVo messageDetail(MessageVo messageVo);

	void messageReadYn(MessageVo messageVo);

	int MessageIdCheck(String idCheck);

	int messageNewCount(String user_id);

}
