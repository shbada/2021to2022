package item.leaf;

import item.Shape;

public class Triangel implements Shape {
    @Override
    public void draw(String color) {
        System.out.println("item.leaf.Triangle draw color : " + color);
    }
}
