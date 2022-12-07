package com.designpattern.report._10_facade.step1_before;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Client {

    public static void main(String[] args) {
        String to = "keesun@whiteship.me";
        String from = "whiteship@whiteship.me";
        String host = "127.0.0.1";

        // Properties 로 서버 정보 셋팅
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);

        Session session = Session.getDefaultInstance(properties);

        /**
         * SOLID 객체 지향 원칙
         */
        try {
            MimeMessage message = new MimeMessage(session);

            // 메시지 정보 설정
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Test Mail from Java Program");
            message.setText("message");

            // send
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
