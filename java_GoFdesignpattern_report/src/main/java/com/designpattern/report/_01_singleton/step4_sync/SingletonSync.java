package com.designpattern.report._01_singleton.step4_sync;

/**
 * private 생성자와 public static 메소드를 사용하는 방법
 */
public class SingletonSync {
    // volatile 키워드
    private static volatile SingletonSync instance;

    private SingletonSync() {}

    /**
     * 필요로한 시점에 만들 수 있다.
     * 하지만 이는, 아주 복잡한 방법이다. (volatile 키워드 필요)
     *
     * @return
     */
    public static SingletonSync getInstance() {
        // double checked locking
        // Lock (락을 잡아서 락을 가지고 있는 쓰레드만 이 영역에 접근이 가능하다.
        // 다 쓰고나면 Lock 이 해제되어 다른 쓰레드가 접근이 가능하다. (다시 Lock)

        // 엄청나게 많은 쓰레드가 if문 안으로 동시에 접근했을때
        // 그때를 대비해서만 synchronized 를 쓰기 때문에
        // 위에 메서드에 synchronized 가 있었던 (메서드 진입 시점에 막음) 보다 비용이 적음
        if (instance == null) {
            // 블록 안에서
            synchronized (SingletonSync.class) { // Settings1Sync.class 의 Lock 체크
                if (instance == null) {
                    instance = new SingletonSync();
                }
            }
        }

        return instance;
    }
}
