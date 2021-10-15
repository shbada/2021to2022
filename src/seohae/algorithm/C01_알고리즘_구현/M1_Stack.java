package seohae.algorithm.C01_알고리즘_구현;

import java.util.Stack;

/**
 * Stack
 */
public class M1_Stack {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        stack.push(5); // push 5
        stack.push(3); // push 3
        stack.push(8); // push 8
        stack.pop(); // pop 8
        stack.push(2); // push 2
        stack.pop(); // pop 2

        while(!stack.isEmpty()) {
            System.out.println(stack.peek()); // 최상위 데이터 peek
            stack.pop(); // pop
        }
    }
}
