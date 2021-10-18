package item.leaf;

import item.Shape;

public class Circle implements Shape {
    @Override
    public void draw(String color) {
        System.out.println("item.leaf.Circle draw color : " + color);
    }
}
