package com.designpattern.report._13_chain_of_responsibilities.step2_after.concrete;


import com.designpattern.report._13_chain_of_responsibilities.step2_after.Request;
import com.designpattern.report._13_chain_of_responsibilities.step2_after.RequestHandler;

public class AuthRequestHandler extends RequestHandler {

    public AuthRequestHandler(RequestHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handle(Request request) {
        // 조건문 넣어서 비즈니스 로직 분기 처리도 가능함
        System.out.println("인증이 되었는가?");
        super.handle(request);
    }
}
