package com.co.kr.message.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.co.kr.common.dao.AbstractDAO;
import com.co.kr.message.vo.MessageVo;

@Repository
public class MessageDao extends AbstractDAO {

	public void MessageWriteSave(MessageVo messageVo) {
		insert("messageSql.MessageWriteSave", messageVo);
	}

	public List<MessageVo> messageList(MessageVo messageVo) {
		return selectList("messageSql.messageList", messageVo);
	}

	public List<MessageVo> MessageSendList(MessageVo messageVo) {
		return selectList("messageSql.MessageSendList", messageVo);
	}

	public void MessageSendDelete(MessageVo messageVo) {
		update("messageSql.MessageSendDelete", messageVo);
	}

	public void MessageGetDelete(MessageVo messageVo) {
		update("messageSql.MessageGetDelete", messageVo);
	}

	public void messageGetListDel(MessageVo messageVo) {
		update("messageSql.MessageGetDelete", messageVo);
	}

	public void messageSendListDel(MessageVo messageVo) {
		update("messageSql.MessageSendDelete", messageVo);
	}

	public MessageVo messageDetail(MessageVo messageVo) {
		return (MessageVo) selectOne("messageSql.messageDetail", messageVo);
	}

	public void messageReadYn(MessageVo messageVo) {
		update("messageSql.messageReadYn", messageVo);
	}

	public int MessageIdCheck(String idCheck) {
		return (int) selectOne("messageSql.MessageIdCheck", idCheck);
	}

	public int messageNewCount(String user_id) {
		return (int) selectOne("messageSql.messageNewCount", user_id);
	}

}
