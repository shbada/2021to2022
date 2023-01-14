package com.co.kr.mail.service;

public interface MailService {
	/*제목, 내용, 보내는 메일, 받는 메일, 첨부파일*/
	boolean send(String subject, String text, String from, String to, String filePath);

}
