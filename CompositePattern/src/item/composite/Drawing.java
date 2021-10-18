package item.composite;


import item.Shape;

import java.util.ArrayList;
import java.util.List;

public class Drawing implements Shape {
    private List<Shape> shapes = new ArrayList<Shape>();

    @Override
    public void draw(String color) {
        for (Shape sh : shapes) {
            sh.draw(color);
        }
    }

    // 아래서부터는 헬퍼 성격의 메소드이다. 추가/제거/전체제거
    public void add (Shape s) {
        this.shapes.add(s);
    }

    public void remove (Shape s) {
        this.shapes.remove(s);
    }

    public void clear () {
        this.shapes.clear();
    }
}
