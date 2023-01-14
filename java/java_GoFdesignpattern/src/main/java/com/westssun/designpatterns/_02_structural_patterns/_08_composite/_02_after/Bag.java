package com.westssun.designpatterns._02_structural_patterns._08_composite._02_after;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Bag implements Component {

    @Getter
    private List<Component> components = new ArrayList<>();

    /**
     * Component add
     * @param component
     */
    public void add(Component component) {
        components.add(component);
    }

    @Override
    public int getPrice() {
        return components.stream().mapToInt(Component::getPrice).sum();
    }
}
