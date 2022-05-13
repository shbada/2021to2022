package com.designpattern.report._13_chain_of_responsibilities.step2_after.concrete;

import com.designpattern.report._13_chain_of_responsibilities.step2_after.Request;
import com.designpattern.report._13_chain_of_responsibilities.step2_after.RequestHandler;

/**
 * 요청을 처리하는쪽
 */
public class LoggingRequestHandler extends RequestHandler {
    public LoggingRequestHandler(RequestHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handle(Request request) {
        System.out.println("로깅");
        super.handle(request);
    }
}
