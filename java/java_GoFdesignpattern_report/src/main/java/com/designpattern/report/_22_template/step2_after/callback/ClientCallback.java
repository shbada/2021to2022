package com.designpattern.report._22_template.step2_after.callback;

import com.designpattern.report._22_template.step2_after.FileProcessor;
import com.designpattern.report._22_template.step2_after.Multiply;

/*
로직이 매우 비슷한데 중간에 몇줄만 다르면? 이런 경우 템플릿 메소드 사용하기가 좋다.
 */
public class ClientCallback {

    public static void main(String[] args) {
        FileProcessor fileProcessor = new Multiply("number.txt");
        // int result = fileProcessor.process(new PlusCallback());
        int result = fileProcessor.process((result1, number) -> result1 += number);
        System.out.println(result);
    }
}
