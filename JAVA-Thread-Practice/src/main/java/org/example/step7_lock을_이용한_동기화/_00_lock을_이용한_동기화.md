## 'java.util.concurrent.locks' 패키지가 제공하는 lock 클래스 
synchronized 블럭을 사용했을때의 같은 메서드 내에서만 lock을 걸 수 있다는 제약이 불편하다. 그럴때 lock 클래스를 사용한다.

[lock 클래스의 종류]
- ReetrantLock : 재진입이 가능한 lock. 가장 일반적인 배타 lock
-> 특정 조건에서 lock을 풀고 나중에 다시 lock을 얻고 임계영역으로 들어와서 이후의 작업을 수행할 수 있기 때문이다.
-> 배타적인 락이라서 무조건 lock이 있어야만 임계 영역의 코드를 수행할 수 있다.

- ReetrantReadWriteLock : 읽기에는 공유적이고, 쓰기에는 배타적인 lock 
-> 읽기를 위한 lock과 쓰기를 위한 lock을 제공한다.
-> 읽기 lock이 걸려있으면, 다른 쓰레드가 읽기 lock을 중복해서 걸고 읽기를 수행할 수 있다. 
읽기는 내용을 변경하지 않으므로 동시에 여ㅓ러 쓰레드가 읽어도 문제가 되지 않는다. 
그러나 읽기 lock이 걸린 상태에서 쓰기 lock을 거는 것은 허용되지 않는다. 

- StampedLock : ReetrantReadWriteLock에 낙관적인 lock의 기능을 추가 
-> lock을 걸거나 해지할때 '스탬프(long 타입의 정수값)'를 사용하며, 읽기와 쓰기를 위한 lock외에
'낙관적 읽기 lock(optimistic reading lock)'이 추가된 것이다.
읽기 lock이 걸려있으면, 쓰기 lock을 얻기 위해서는 읽기 lock이 풀릴때까지 기다려야하는데 비해,
'낙관적 읽기 lock'은 쓰기 lock에 의해 바로 풀린다. 그래서 낙관적 읽기에 실패하면, 읽기 lock을 얻어서 다시 읽어와야한다.
무조건 읽기 lock을 걸지않고, 쓰기와 읽기가 충돌할때만 쓰기가 끝난 후에 읽기 lock을 거는 것이다. 

## Condition
wait(), notify()로 쓰레드의 종류를 구분하지 않고, 공유 객체의 waiting pool에 같이 몰아넣는 대신, 
손님 쓰레드를 위한 Condition과 요리사 쓰레드를 위한 condition을 만들어서
각각의 waiting pool에서 따로 기다리도록 할 수 있다.
이렇게 되면 요리사 스레드와 손님 쓰레드를 구분해서 통지할 수 있다.
