package com.westssun.designpatterns._03_behavioral_patterns._14_command._01_before;

public class MyApp {

    /* Button.java 와 중복 코드 발생 */
    private Game game;

    public MyApp(Game game) {
        this.game = game;
    }

    public void press() {
        game.start();
    }

    public static void main(String[] args) {
        Button button = new Button(new Light());
        button.press();
        button.press();
        button.press();
        button.press();
    }
}
