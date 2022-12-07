package com.westssun.designpatterns._03_behavioral_patterns._13_chain_of_responsibilities._01_before;

/**
 * 인증/인가 관련 핸들러
 */
public class AuthRequestHandler extends RequestHandler {

    public void handler(Request request) {
        System.out.println("인증이 되었나?");
        System.out.println("이 핸들러를 사용할 수 있는 유저인가?");
        super.handler(request);
    }
}
