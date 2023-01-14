package com.westssun.designpatterns._03_behavioral_patterns._13_chain_of_responsibilities._01_before;

/**
 * 로깅 관련 핸들러
 */
public class LoggingRequestHandler extends RequestHandler {

    @Override
    public void handler(Request request) {
        System.out.println("로깅");
        super.handler(request);
    }
}
