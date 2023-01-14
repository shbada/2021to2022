package org.example.step4_데몬스레드;

import java.util.Iterator;
import java.util.Map;

/**
 * 데몬 스레드
 */
class ThreadEx11 {
	public static void main(String args[]) throws Exception {
		ThreadEx11_1 t1 = new ThreadEx11_1("Thread1");
		ThreadEx11_2 t2 = new ThreadEx11_2("Thread2");
		
		t1.start();
		t2.start();
	}
}

class ThreadEx11_1 extends Thread {
	ThreadEx11_1(String name) {
		super(name);
	}
	
	public void run() {
		try {
			sleep(5*1000);	// 5초 동안 기다린다.
		} catch(InterruptedException e) {}
	}
}

class ThreadEx11_2 extends Thread {
	ThreadEx11_2(String name) {
		super(name);
	}
	
	public void run() {
		// 실행중 또는 대기상태, 즉 작업이 완료되지 않은 모든 쓰레드의 호출 스택을 출력할 수 있다.
		Map map = getAllStackTraces();
		Iterator it = map.keySet().iterator();
		
		int x = 0;
		while(it.hasNext()) {
			Object obj = it.next();
			Thread t = (Thread) obj;
			StackTraceElement[] ste = (StackTraceElement[]) (map.get(obj));
			
			System.out.println("[" + ++x + "] name : " + t.getName()
							+ ", group : " + t.getThreadGroup().getName()
							+ ", deamon : " + t.isDaemon());
							
			for (int i=0; i<ste.length; i++) {
				System.out.println(ste[i]);
			}
			
			System.out.println();
		}
	}
}