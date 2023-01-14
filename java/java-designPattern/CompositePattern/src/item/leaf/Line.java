package item.leaf;

import item.Shape;

public class Line implements Shape {
    @Override
    public void draw(String color) {
        System.out.println("item.leaf.Line draw color : " + color);
    }
}
