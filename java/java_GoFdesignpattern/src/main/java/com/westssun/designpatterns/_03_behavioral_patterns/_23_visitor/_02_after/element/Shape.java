package com.westssun.designpatterns._03_behavioral_patterns._23_visitor._02_after.element;

import com.westssun.designpatterns._03_behavioral_patterns._23_visitor._02_after.visitor.Device;

public interface Shape {
    void accept(Device device);
}
