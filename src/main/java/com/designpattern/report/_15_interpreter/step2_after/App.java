package com.designpattern.report._15_interpreter.step2_after;

import com.designpattern.report._15_interpreter.step2_after.expression.PostfixExpression;

import java.util.Map;

/**
 * 인터프리터 패턴
 * 요청을 캡슐화하여 호출자와 수신자를 분리하는 패턴
 *
 * xyz+-a+ : 복잡한 패턴..
 * 선택의 기로 -> 위 패턴을 구현하는 비용 : 이게 문법으로 지원할정도일까?
 */
public class App {

    public static void main(String[] args) {
        PostfixExpression expression = PostfixParser.parse("xyz+-a+");
        int result = expression.interpret(Map.of('x', 1, 'y', 2, 'z', 3, 'a', 4));
        System.out.println(result);
    }
}
