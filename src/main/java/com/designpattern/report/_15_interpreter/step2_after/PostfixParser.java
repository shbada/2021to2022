package com.designpattern.report._15_interpreter.step2_after;

import com.designpattern.report._15_interpreter.step2_after.concrete.MinusExpression;
import com.designpattern.report._15_interpreter.step2_after.concrete.PlusExpression;
import com.designpattern.report._15_interpreter.step2_after.concrete.VariableExpression;
import com.designpattern.report._15_interpreter.step2_after.expression.PostfixExpression;

import java.util.Stack;

public class PostfixParser {

    // xyz+-
    public static PostfixExpression parse(String expression) {
        Stack<PostfixExpression> stack = new Stack<>();

        // 각 경우에 따라 PostfixExpression 구현체 객체 얻기
        // x : VariableExpression
        // y : VariableExpression
        // z : VariableExpression
        // + : PlusExpression
        // - : MinusExpression
        for (char c : expression.toCharArray()) {
            stack.push(getExpression(c, stack));
        }
        return stack.pop();
    }

    /**
     * 곱셈이 추가되면 여긴 수정이 발생 (당연한것)
     * Multiply 원하는 expression 을 추가할 수 있다는 점에서 유연성, 확장성
     * @param c
     * @param stack
     * @return
     */
    private static PostfixExpression getExpression(char c, Stack<PostfixExpression> stack) {
        // case 별로 각 Expression 객체 리턴
        switch (c) {
            case '+':
                // VariableExpression 2개겠다
                return new PlusExpression(stack.pop(), stack.pop());
            case '-':
                PostfixExpression right = stack.pop();
                PostfixExpression left = stack.pop();
                return new MinusExpression(left, right);
            default:
                return new VariableExpression(c);
        }
    }
}
