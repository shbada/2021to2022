package me.whiteship.refactoring._06_mutable_data._18_split_variable;

public class Rectangle_Done {

    private double perimeter;
    private double area;

    public void updateGeometry(double height, double width) {
        // 변수명 변경
        // final : 한번 셋팅 후 값 고정싴니기
        final double perimeter = 2 * (height + width);
        System.out.println("Perimeter: " + perimeter);
        this.perimeter = perimeter;

        final double area = height * width;
        System.out.println("Area: " + area);
        this.area = area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public double getArea() {
        return area;
    }
}
