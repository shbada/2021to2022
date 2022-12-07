package com.designpattern.report._15_interpreter.step2_after.concrete;

import com.designpattern.report._15_interpreter.step2_after.expression.PostfixExpression;

import java.util.Map;

public class MinusExpression implements PostfixExpression {

    private PostfixExpression left;

    private PostfixExpression right;

    public MinusExpression(PostfixExpression left, PostfixExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Map<Character, Integer> context) {
        return left.interpret(context) - right.interpret(context);
    }
}
