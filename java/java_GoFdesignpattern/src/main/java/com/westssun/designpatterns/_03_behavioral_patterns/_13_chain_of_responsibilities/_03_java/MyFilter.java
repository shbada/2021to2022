package com.westssun.designpatterns._03_behavioral_patterns._13_chain_of_responsibilities._03_java;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

// 이 요청이 왔을때 거친다.
@WebFilter(urlPatterns = "/hello")
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("게임에 참하신 여러분 모두 진심으로 환영합니다.");
        chain.doFilter(request, response); // 생략가능
        System.out.println("꽝!");
    }
}
