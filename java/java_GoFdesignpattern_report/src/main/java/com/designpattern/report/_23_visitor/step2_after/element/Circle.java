package com.designpattern.report._23_visitor.step2_after.element;

import com.designpattern.report._23_visitor.step2_after.visitor.Device;

public class Circle implements Shape {

    @Override
    public void accept(Device device) {
        device.print(this);
    }
}
