package com.westssun.designpatterns._02_structural_patterns._10_facade._02_after;

/**
 * 퍼사드 (Facade) 패턴
 * - 복잡한 서브 시스템 의존성을 최소화하는 방법
 * - 클라이언트가 사용해야 하는 복잡한 서브 시스템 의존성을 간단한 인터페이스로 추상화할 수 있다.
 * 복잡한 디테일을 퍼사드 뒤로 숨긴다.
 *
 * [장점]
 * - 서브 시스템에 대한 의존성을 한곳으로 모을 수 있다.
 * (EmailSender 가 모든 의존성을 가지게되는 문제점이 있긴하다..)
 * (Client 코드 기준으로 퍼사드로 만들었을때 코드가 더 편하거나 더 유연하게 느껴진다면? 퍼사드를 구현할만하다.)
 * [단점]
 * - 퍼사드 클래스가 서브 시스템에 대한 모든 의존성을 가지게된다. (EmailSender)
 */
public class Client {

    public static void main(String[] args) {
        /* Email Setting */
        EmailSettings emailSettings = new EmailSettings();
        emailSettings.setHost("127.0.0.1");

        /* Email Sender */
        EmailSender emailSender = new EmailSender(emailSettings);

        /* Email 내용 */
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setFrom("keesun");
        emailMessage.setTo("whiteship");
        emailMessage.setCc("일남");
        emailMessage.setSubject("오징어게임");
        emailMessage.setText("밖은 더 지옥이더라고..");

        emailSender.sendEmail(emailMessage);
    }
}
