package com.designpattern.report._23_visitor.step2_after.visitor;

import com.designpattern.report._23_visitor.step2_after.element.Circle;
import com.designpattern.report._23_visitor.step2_after.element.Rectangle;
import com.designpattern.report._23_visitor.step2_after.element.Triangle;

public class Watch implements Device {
    @Override
    public void print(Circle circle) {
        System.out.println("Print Circle to Watch");
    }

    @Override
    public void print(Rectangle rectangle) {
        System.out.println("Print Rectangle to Watch");
    }

    @Override
    public void print(Triangle triangle) {
        System.out.println("Print Triangle to Watch");
    }
}
