package com.westssun.designpatterns._03_behavioral_patterns._13_chain_of_responsibilities._01_before;

public class RequestHandler {

    public void handler(Request request) {
        // 아래 체크를 여기서 할건가?
        // -> AuthRequestHandler 여기서 하자. RequestHandler 을 상속해서.
        // 단일책임원칙은 지킬 수 있겠다. (인증/인가 관련은 AuthRequestHandler.java 에서 하니깐)
        // 문제는 Client 에서 선택해야한다. new AuthRequestHandler();로
        System.out.println("인증이 됬나?");
        System.out.println("이 핸들러를 사용할 수 있는 유저인가?");

        System.out.println(request.getBody());
    }
}
