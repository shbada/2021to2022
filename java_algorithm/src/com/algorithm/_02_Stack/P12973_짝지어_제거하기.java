package com.algorithm._02_Stack;

import java.util.Stack;

/**
 * @Date 2022/03/09
 * @URL https://
 */
public class P12973_짝지어_제거하기 {
    static Stack<String> stack = new Stack<>();

    public static void main(String[] args) {
	    // write your code here
        P12973_짝지어_제거하기 main = new P12973_짝지어_제거하기();
        System.out.println(main.solution("baabaa"));
    }

    /*
       b aa baa -> bb aa -> aa ->   가능 (1)
       cd cd -> 불가능 (0)
     */
    public int solution(String s) {
        int answer = 0;

        for (int i = 0; i < s.length(); i++) {
            String target = String.valueOf(s.charAt(i));

            if (!stack.isEmpty()) {
                if (target.equals(stack.peek())) {
                    stack.pop();
                } else {
                    stack.add(target);
                }
            } else {
                stack.add(target);
            }
        }

        if (stack.isEmpty()) {
            answer = 1;
        }

        return answer;
    }
}
