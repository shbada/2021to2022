package com.westssun.designpatterns._03_behavioral_patterns._13_chain_of_responsibilities._01_before;

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
