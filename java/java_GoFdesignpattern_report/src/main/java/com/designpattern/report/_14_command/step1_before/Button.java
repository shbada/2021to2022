package com.designpattern.report._14_command.step1_before;

/**
 * 커맨드 패턴
 * 요청을 캡슐화하여 호출자(invoker)와 수신자(receiver)를 분리하는 패턴
 * 요청을 처리하는 방법이 바뀌더라도 호출자의 코드는 변경되지 않는다.
 */
public class Button {

    private Light light;

    public Button(Light light) {
        this.light = light;
    }

    public void press() {
        /** light 변경에 따라 수정 영향을 받는다. */
        // light.on();
        light.off();
    }

    public static void main(String[] args) {
        Button button = new Button(new Light());
        button.press();
        button.press();
        button.press();
        button.press();
    }
}
