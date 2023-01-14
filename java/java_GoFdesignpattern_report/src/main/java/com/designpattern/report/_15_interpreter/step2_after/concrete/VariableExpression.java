package com.designpattern.report._15_interpreter.step2_after.concrete;

import com.designpattern.report._15_interpreter.step2_after.expression.PostfixExpression;

import java.util.Map;

public class VariableExpression implements PostfixExpression {

    private Character character;

    public VariableExpression(Character character) {
        this.character = character;
    }

    @Override
    public int interpret(Map<Character, Integer> context) {
        // x, y, z 문자열에 대한 value return
        return context.get(this.character);
    }
}
