package org.example.step5_쓰레드의_동기화;

class ThreadEx22 {
	public static void main(String args[]) {
		Runnable r = new RunnableEx22();
		new Thread(r).start();
		new Thread(r).start();
	}
}

class Account2 {
	private int balance = 1000;	// private 으로 해야 동기화가 의미가 있음

	public int getBalance() {
		return balance;
	}

	/**
	 * synchronized 로 지정된 영역의 코드를 한번에 하나의 쓰레드가 수행하는 것을 보장하는 것일 뿐이다.
	 * @param money
	 */
	public synchronized void withdraw(int money) {	// synchronized 로 메서드를 동기화
		if (balance >= money) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			balance -= money;
		}
	}
}

class RunnableEx22 implements Runnable {
	Account2 acc = new Account2();
	
	public void run() {
		while (acc.getBalance() > 0) {
			// 100, 200, 300 중의 한 값을 임의로 선택해서 출금(withdraw)
			int money = (int)(Math.random() * 3 + 1) * 100;
			acc.withdraw(money);
			System.out.println("balance : " + acc.getBalance());
		}
	}
}