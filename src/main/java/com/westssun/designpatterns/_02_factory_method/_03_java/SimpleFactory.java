package com.westssun.designpatterns._02_factory_method._03_java;

import com.westssun.designpatterns._02_factory_method._02_after.BlackShip;
import com.westssun.designpatterns._02_factory_method._02_after.WhiteShip;

public class SimpleFactory {

    public Object createProduct(String name) {
        if (name.equals("whiteship")) {
            return new WhiteShip();
        } else if (name.equals("blackship")) {
            return new BlackShip();
        }

        throw new IllegalArgumentException();
    }
}
