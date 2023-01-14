package com.westssun.designpatterns._03_behavioral_patterns._22_template._02_after.callback;

/**
 * ConcreteClass
 *
 */
public class PlusCallback implements Operator {
    @Override
    public int getResult(int result, int number) {
        return result += number;
    }
}
