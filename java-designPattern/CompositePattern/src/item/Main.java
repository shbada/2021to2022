package item;

import item.composite.Drawing;
import item.leaf.Circle;
import item.leaf.Line;
import item.leaf.Triangel;

public class Main {
    public static void main(String[] args) {
        Shape t1 = new Triangel();
        Shape t2 = new Triangel();
        Shape c1 = new Circle();
        Shape l1 = new Line();

        Drawing drawing = new Drawing();
        drawing.add(t1);
        drawing.add(t2);
        drawing.add(c1);
        drawing.add(l1);
        drawing.draw("red");

        drawing.clear();

        drawing.add(t2);
        drawing.add(c1);
        drawing.draw("blue");

        /**
         item.leaf.Triangle draw color : red
         item.leaf.Triangle draw color : red
         item.leaf.Circle draw color : red
         item.leaf.Line draw color : red
         item.leaf.Triangle draw color : blue
         item.leaf.Circle draw color : blue
         */
    }
}
