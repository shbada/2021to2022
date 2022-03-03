package com.designpattern.report._13_chain_of_responsibilities.step1_before;

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
