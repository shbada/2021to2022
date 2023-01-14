package com.designpattern.report._23_visitor.step2_after.visitor;

import com.designpattern.report._23_visitor.step2_after.element.Circle;
import com.designpattern.report._23_visitor.step2_after.element.Rectangle;
import com.designpattern.report._23_visitor.step2_after.element.Triangle;

public class Phone implements Device {

    @Override
    public void print(Circle circle) {
        System.out.println("Print Circle to Phone");
    }

    @Override
    public void print(Rectangle rectangle) {
        System.out.println("Print Rectangle to Phone");

    }

    @Override
    public void print(Triangle triangle) {
        System.out.println("Print Triangle to Phone");
    }
}
