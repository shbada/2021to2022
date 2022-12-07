package com.westssun.designpatterns._03_behavioral_patterns._23_visitor._02_after.visitor;

import com.westssun.designpatterns._03_behavioral_patterns._23_visitor._02_after.element.Rectangle;
import com.westssun.designpatterns._03_behavioral_patterns._23_visitor._02_after.element.Circle;
import com.westssun.designpatterns._03_behavioral_patterns._23_visitor._02_after.element.Triangle;

public interface Device {
    void print(Circle circle);

    void print(Rectangle rectangle);

    void print(Triangle triangle);
}
