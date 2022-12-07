
package com.algorithm._01_그리디_구현.Dont;

/**
 * @Date 2022/03/25
 * @URL https://programmers.co.kr/learn/courses/30/lessons/42885
 */
public class L43_MultiplyStrings {
    public static void main(String[] args) {
        // write your code here
        L43_MultiplyStrings main = new L43_MultiplyStrings();
        System.out.println(main.multiply("123", "456"));
    }

    public String multiply(String num1, String num2) {
        int[] res = new int[220];

        int x = 0;
        for (int i =  num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - 48;

            int y = 0;
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - 48;

                // 0 + 0, 0 + 1, 0 + 2
                // 1 + 0, 1 + 1, 1 + 2
                res[x + y] += n1 * n2;

                y++;
            }

            x++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num1.length() + num2.length(); i++) {
            if (res[i] >= 10) {
                res[i + 1] += res[i] / 10;
                res[i] = res[i] % 10;
            }

            sb.insert(0, res[i]);
        }

        if (sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}
