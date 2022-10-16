package org.example.step2_싱글스레드_멀티스레드;

/**
 * 1의 쓰레드로 2개의 작업 수행
 */
class _4_ThreadEx4 {
	public static void main(String args[]) {
		long startTime = System.currentTimeMillis();
		
		for (int i = 0; i < 300; i++)
			System.out.printf("%s", new String("-")); // new String() 사용해서 고의로 처리 시간 늦추기
		
		System.out.print("소요시간1 : " + (System.currentTimeMillis() - startTime));
		
		for (int i = 0; i < 300; i++)
			System.out.printf("%s", new String("|"));
		
		System.out.print("소요시간2 : " + (System.currentTimeMillis() - startTime));
	}
}