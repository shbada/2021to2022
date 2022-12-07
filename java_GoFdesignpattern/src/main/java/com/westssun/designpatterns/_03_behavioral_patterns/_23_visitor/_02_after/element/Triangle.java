package com.westssun.designpatterns._03_behavioral_patterns._23_visitor._02_after.element;

import com.westssun.designpatterns._03_behavioral_patterns._23_visitor._02_after.visitor.Device;

public class Triangle implements Shape {


    @Override
    public void accept(Device device) {
        device.print(this);
    }
}
