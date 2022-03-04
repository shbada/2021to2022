package com.designpattern.report._23_visitor.step2_after.element;

import com.designpattern.report._23_visitor.step2_after.visitor.Device;

public interface Shape {
    void accept(Device device);
}
