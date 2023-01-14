package com.java.effective.item29;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        StackGeneric<String> stack = new StackGeneric<>();

        Arrays.stream(args).forEach(stack::push);

        while (!stack.isEmpty()) {
            System.out.println(stack.pop().toUpperCase());
        }
    }
}
