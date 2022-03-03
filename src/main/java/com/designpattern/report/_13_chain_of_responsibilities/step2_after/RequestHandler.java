package com.designpattern.report._13_chain_of_responsibilities.step2_after;

/**
 * 추상클래스
 */
public abstract class RequestHandler {

    // field 가 필요하여 인터페이스 불가능
    // 연쇄(연결하기 위해) next Handler 을 받는다.
    private RequestHandler nextHandler;

    public RequestHandler(RequestHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void handle(Request request) {
        if (nextHandler != null) { // null 이 아닐때만 이어지도록
            nextHandler.handle(request);
        }
    }
}
