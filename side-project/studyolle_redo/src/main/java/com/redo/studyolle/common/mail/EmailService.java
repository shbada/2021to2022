package com.redo.studyolle.common.mail;

/**
 * Email 발송 관련 Service
 */
public interface EmailService {
    void sendEmail(EmailMessage emailMessage);
}
