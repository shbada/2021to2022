package com.designpattern.report._13_chain_of_responsibilities.step2_after.concrete;

import com.designpattern.report._13_chain_of_responsibilities.step2_after.Request;
import com.designpattern.report._13_chain_of_responsibilities.step2_after.RequestHandler;

/**
 * 요청을 처리하는쪽
 */
public class PrintRequestHandler extends RequestHandler {
    public PrintRequestHandler(RequestHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handle(Request request) {
        System.out.println(request.getBody());
        super.handle(request);
    }
}
