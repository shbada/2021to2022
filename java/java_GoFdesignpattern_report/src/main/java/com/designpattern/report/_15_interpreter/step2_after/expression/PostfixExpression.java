package com.designpattern.report._15_interpreter.step2_after.expression;

import java.util.Map;

public interface PostfixExpression {

    int interpret(Map<Character, Integer> context);

    /**
     * 직접 아래 메서드를 호출할 수도 있다.
     * @param left
     * @param right
     * @return
     */
    static PostfixExpression plus(PostfixExpression left, PostfixExpression right) {
        return context -> left.interpret(context) + right.interpret(context);
    }

    /**
     * MinusExpression 을 생성하면 제거
     * @param left
     * @param right
     * @return
     */
    static PostfixExpression minus(PostfixExpression left, PostfixExpression right) {
        return context -> left.interpret(context) - right.interpret(context);
    }

    static PostfixExpression variable(Character c) {
        return context -> context.get(c);
    }
}
