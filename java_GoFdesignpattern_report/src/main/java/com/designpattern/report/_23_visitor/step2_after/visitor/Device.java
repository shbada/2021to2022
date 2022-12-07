package com.designpattern.report._23_visitor.step2_after.visitor;

import com.designpattern.report._23_visitor.step2_after.element.Circle;
import com.designpattern.report._23_visitor.step2_after.element.Rectangle;
import com.designpattern.report._23_visitor.step2_after.element.Triangle;

public interface Device {
    void print(Circle circle);

    void print(Rectangle rectangle);

    void print(Triangle triangle);
}
