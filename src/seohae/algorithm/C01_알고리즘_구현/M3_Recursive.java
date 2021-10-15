package seohae.algorithm.C01_알고리즘_구현;

/**
 * Recursive
 */
public class M3_Recursive {
    public static void main(String[] args) {
        recursiveFunction();
    }

    private static void recursiveFunction() {
        System.out.println("재귀함수 호출");
        recursiveFunction();
    }
}
