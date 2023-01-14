package com.westssun.designpatterns._02_structural_patterns._07_bridge._03_java;

import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class BridgeInSpring {

    public static void main(String[] args) {
        MailSender mailSender = new JavaMailSenderImpl();

        //PlatformTransactionManager platformTransactionManager = new JdbcTransactionManager();
    }
}
