package com.designpattern.report._22_template.step2_after;

/**
 * ConcreteClass
 */
public class Plus extends FileProcessor {
    public Plus(String path) {
        super(path);
    }

    @Override
    public int getResult(int result, int number) {
        return result += number;
    }
}
