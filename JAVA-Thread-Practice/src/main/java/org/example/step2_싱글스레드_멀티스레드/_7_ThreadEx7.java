package org.example.step2_싱글스레드_멀티스레드;

import javax.swing.*;

class _7_ThreadEx7 {
	public static void main(String[] args) throws Exception {
		ThreadEx7_1 th1 = new ThreadEx7_1();
		/* 쓰레드로 별도로 수행되므로, 화면에 입력이 마치지 않아도 화면에 숫자가 출력된다. */
		th1.start();
		
		String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
		System.out.println("입력하신 값은 " + input + " 입니다.");
	}
}

class ThreadEx7_1 extends Thread {
	public void run() {
		for (int i=10; i>0; i--) {
			System.out.println(i);
			try {
				sleep(1000);
			} catch(Exception e) {}
		}
	}
}