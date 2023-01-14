package com.designpattern.report._22_template.step2_after;

/**
 * ConcreteClass
 */
public class Multiply extends FileProcessor {
    public Multiply(String path) {
        super(path);
    }

    @Override
    protected int getResult(int result, int number) {
        return result *= number;
    }

}
