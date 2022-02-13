package com.westssun.designpatterns._02_structural_patterns._06_adapter._03_java;

import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

public class AdapterInSpring {

    public static void main(String[] args) {
        /**
         * HandlerAdapter
         *
         * Object handler (다양한 형태의 handler)을 모두 지원하기 위해 확장을 열기위해
         * HandlerAdapter 인터페이스 제공
         *
         * handle() : Adapter
         */
        DispatcherServlet dispatcherServlet = new DispatcherServlet(); // doDispatch() 메서드 로직 중요
        HandlerAdapter handlerAdapter = new RequestMappingHandlerAdapter();
    }
}
