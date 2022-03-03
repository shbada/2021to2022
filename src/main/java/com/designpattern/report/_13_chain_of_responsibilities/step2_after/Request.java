package com.designpattern.report._13_chain_of_responsibilities.step2_after;

import lombok.Getter;
import lombok.Setter;

public class Request {

    @Getter
    @Setter
    private String body;

    public Request(String body) {
        this.body = body;
    }
}
