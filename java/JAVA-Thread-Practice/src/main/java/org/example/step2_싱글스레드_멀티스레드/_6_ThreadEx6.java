package org.example.step2_싱글스레드_멀티스레드;

import javax.swing.*;

public class _6_ThreadEx6 {
    public static void main(String[] args) throws Exception {
        /* 입력이 마치기 전까지는 화면에 숫자가 출력되지 않다가, 사용자가 입력을 마치고 나서야 화면에 숫자가 출력된다. */
        String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
        System.out.println("입력하신 값은 " + input + " 입니다.");

        for (int i=10; i>0; i--) {
            System.out.println(i);
            try {
                Thread.sleep(1000);	// 1초간 시간 지연
            } catch(Exception e) {}
        }
    }
}
