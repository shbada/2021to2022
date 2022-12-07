package com.designpattern.report._15_interpreter.step1_before;

import java.util.Stack;

/**
 * 인터프리터 패턴
 * 자주 등장하는 문제를 간단한 언어로 정의하고 재사용하는 패턴
 * 반복되는 문제 패턴을 언어 또는 문법으로 정의하고 확장할 수 있다.
 *
 * Context
 * Interface : Expression (interpret(Context))
 * TerminalExpression , NonTerminalExpression
 */
public class PostfixNotation {

    private final String expression;

    public PostfixNotation(String expression) {
        this.expression = expression;
    }

    public static void main(String[] args) {
        PostfixNotation postfixNotation = new PostfixNotation("123+-");
        postfixNotation.calculate();

        // 산수 계산
        // inPix : 1 + 2- 5 (부호가 숫자 가운데)
        // postPix : 1 2 + 5 - (부호가 오른쪽)
        // 부호가 있을때 앞의 숫자를 계산
        // 1 2 3 + -
        // 2 + 3 = 5
        // 1 - 5 = -4
    }

    private void calculate() {
        Stack<Integer> numbers = new Stack<>();

        for (char c : this.expression.toCharArray()) {
            switch (c) {
                case '+':
                    numbers.push(numbers.pop() + numbers.pop());
                    break;
                case '-':
                    int right = numbers.pop();
                    int left = numbers.pop();
                    numbers.push(left - right);
                    break;
                default:
                    numbers.push(Integer.parseInt(c + ""));
            }
        }

        System.out.println(numbers.pop()); // 최종 출력 - 결과
    }
}
