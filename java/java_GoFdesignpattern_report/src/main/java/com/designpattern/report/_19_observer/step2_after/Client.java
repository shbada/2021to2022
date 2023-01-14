package com.designpattern.report._19_observer.step2_after;

/**
 * 옵저버 패턴
 * 다수의 객체가 특정 객체 상태 변화를 감지하고 알림을 받는 패턴
 * 발행(publish), 구독(subscribe) 패턴을 구현할 수 있다.
 *
 * [장점]
 * 변경/유연성/재사용성/느슨한결합
 * Subject 의 상태 변경을 주기적으로 조회하지 않고 자동으로 감지할 수 있다.
 *
 * [단점]
 * subscribers(Map) 에 데이터가 있으므로 가비지컬렉션의 대상이 될수없다 (메모리 누수 발생)
 */
public class Client {
    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        User user1 = new User("user1");
        User user2 = new User("user2");

        // "오징어게임" -> user1, user2
        // "디자인패턴" -> user1
        chatServer.register("오징어게임", user1);
        chatServer.register("오징어게임", user2);

        chatServer.register("디자인패턴", user1);

        // user1, user2 (2번 출력)
        chatServer.sendMessage(user1, "오징어게임", "아.. 이름이 기억났어.. 일남이야.. 오일남");

        // user1 알림 (1번 출력)
        chatServer.sendMessage(user2, "디자인패턴", "옵저버 패턴으로 만든 채팅");

        // user1 구독 취소
        chatServer.unregister("디자인패턴", user1);

        // "디자인패턴" key 를 구독한 유저는 현재 없음
        chatServer.sendMessage(user2, "디자인패턴", "옵저버 패턴 장, 단점 보는 중");

//        keesun: 아.. 이름이 기억났어.. 일남이야.. 오일남
//        keesun: 아.. 이름이 기억났어.. 일남이야.. 오일남
//        whiteship: 옵저버 패턴으로 만든 채팅
//        whiteship: 옵저버 패턴 장, 단점 보는 중
    }
}
