package com.designpattern.report._08_composite.step2_after.composite;

import com.designpattern.report._08_composite.step2_after.component.Component;
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
