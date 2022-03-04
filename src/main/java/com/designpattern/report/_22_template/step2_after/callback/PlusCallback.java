package com.designpattern.report._22_template.step2_after.callback;

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
