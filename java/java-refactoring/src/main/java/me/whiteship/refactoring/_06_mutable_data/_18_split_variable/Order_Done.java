package me.whiteship.refactoring._06_mutable_data._18_split_variable;

public class Order_Done {

    public double discount(double inputValue, int quantity) {
        // 변수에 담아서 로직 수행
        double result = inputValue;

        if (inputValue > 50) result = result - 2;
        if (quantity > 100) result = result - 1;

        return result;
    }
}
