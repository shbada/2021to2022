package seohae.algorithm.P01_재귀함수;

public class Recursion01 {
    public static void main(String[] args) {
        helloWorld(2);
    }

    public static int helloWorld(int index) {
        if (index == 10) {
            return index;
        }
        System.out.println("hello word" + index);

        int num = helloWorld(index + 1);

        System.out.println("num : " + num);
        return index * (index - 1);
    }
}
