package com.westssun.designpatterns._03_behavioral_patterns._13_chain_of_responsibilities._01_before;

/**
 * 책임 연쇄
 *
 * 각각의 책임이 연결되어있음
 * (단일책임원칙의 책임)
 * 어떤 클래스가 변경되어야하는 이유는 한가지 이유여야 한다.
 *
 * 하나의 클래스는 하나의 책임을 가지고있다.
 * 특정한 책임을 가지고있는 클래스들이 연결되어있는 구조로 무언가를 처리하는거구나.
 *
 * 요청을 보내는 쪽에서 그 요청을 처리하는 handler 가 어떤 구체적인 타입인지 상관없이 요청을 처리한다.
 *
 * [책임 연쇄 패턴 Chain-of-Responsibility 패턴)
 * - 요청을 보내는쪽(sender)과 요청을 처리하는쪽(receiver)을 분리하는 패턴
 * 핸들러 체인을 사용해서 요청을 처리한다.
 */
public class Client {

    public static void main(String[] args) {
        Request request = new Request("무궁화 꽃이 피었습니다.");

        // 클라이언트가 직접 구현클래스를 선택해야한다.
        // 클라이언트가 알아야한다 -> 변경이 발생한다.
        //RequestHandler requestHandler = new AuthRequestHandler();
        RequestHandler requestHandler = new LoggingRequestHandler();
        requestHandler.handler(request);
    }
}
