package org.example.step2_싱글스레드_멀티스레드;

/**
 * 2개의 쓰레드로 2개의 작업 수행
 *
 * 두개의 쓰레드로 작업한 시간이 싱글쓰레드로 작업한 시간보다 더 걸린다.
 * 그 이유는, 쓰레드 간의 작업 전환(context switching)에 시간이 걸리기 때문이다.
 * 또, 한 스레드가 화면에 출력하고 있는 동안 다른 쓰레드는 출력이 끝나기를 기다려야하는데, 이때 대기시간이 발생한다.
 *
 * 싱글 코어인 경우에는 멀티쓰레드라도 하나의 코어가 번갈아가면서 작업을 수행하는 것이므로 두 작업이 절대 겹치지 않는다.
 * 멀티코어에서는 멀티 쓰레드로 두 작업을 수행하면 동시에 두 쓰레드가 수행될 수 있으므로 두 작업이 겹치는 부분이 발생할 수 있다.
 * 그래서 console 출력일때 console 자원을 놓고 두 쓰레드가 경쟁하게 된다.
 * OS의 프로세스 스케줄러의 영향을 받기 때문에 이 예제의 결과는 실행할때마다 다른 결과를 얻는다.
 *
 * 두 쓰레드가 서로 다른 자원을 사용하는 작업의 경우에는 싱글쓰레드 프로세스보다 멀티 쓰레드 프로세스가 더 효율적이다.
 */
class _5_ThreadEx5 {
	static long startTime = 0;
	
	public static void main(String args[]) {
		ThreadEx5_1 th1 = new ThreadEx5_1();
		th1.start();
		startTime = System.currentTimeMillis();
		
		for (int i=0; i<300; i++)
			System.out.printf("%s", new String("-"));
		
		System.out.print("소요시간1 : " + (System.currentTimeMillis() - _5_ThreadEx5.startTime));
	}
}

class ThreadEx5_1 extends Thread {
	public void run() {
		for (int i=0; i<300; i++)
			System.out.printf("%s", new String("|"));
		
		System.out.print("소요시간2 : " + (System.currentTimeMillis() - _5_ThreadEx5.startTime));
	}
}