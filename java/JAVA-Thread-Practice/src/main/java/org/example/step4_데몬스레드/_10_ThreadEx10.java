package org.example.step4_데몬스레드;

class _10_ThreadEx10 implements Runnable {
	static boolean autoSave = false;
	
	public static void main(String[] args) {
		Thread t = new Thread(new _10_ThreadEx10());
		// start() 호출 전에 반드시 미리 실행되어야한다.
		t.setDaemon(true); // 데몬쓰레드 설정
		t.start();
		
		for (int i = 1; i <= 10; i++) {
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {}

			System.out.println(i);
			
			if (i == 5)
				autoSave = true;
		}

		// 데몬쓰레드이기 때문에 main 쓰레드 종료시 함께 종료된다.
		System.out.println("프로그램을 종료합니다.");
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(3 * 1000);	// 3초마다
			} catch(InterruptedException e) {}
			// autoSave의 값이 true이면 autoSave()를 호출한다.
			if (autoSave) {
				autoSave();
			}
		}
	}
	
	public void autoSave() {
		System.out.println("작업파일이 자동저장되었습니다.");
	}
}