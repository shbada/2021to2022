## fork & join 프레임웍
- 하나의 작업을 작은 단위로 나눠서 여러 쓰레드가 동시에 처리하는 것을 쉽게 만들어준다.
- 아래 두 클래스 중에서 하나를 상속받아 구현한다.
1) RecursiveAction : 반환값이 없는 작업을 구현할때 사용 
2) RecursiveTask  : 반환값이 있는 작업을 구현할때 사용
- invoke() 호출하여 작업 시작 
```java
ForkJoinPool pool = new ForkJoinPool(); // 쓰레드 풀 생성 
SumTask task = new SumTask(from, to); // 수행할 작업을 생성 

Long result = pool.invoke(task); 
```

- fork() : 작업을 쓰레드의 작업 큐에 넣는 것이고, 작업 큐에 들어간 작업은 더이상 나눌 수 없을때까지 나뉜다.
즉 compute()로 나누고 fork()로 작업 큐에 넣는 작업이 계속해서 반복된다.
-> 해당 작업을 쓰레드 풀이 작업 큐에 넣는다. 비동기 메서드다.
- join() : 나눠진 작업은 각 쓰레드가 골고루 나눠서 처리하고, 작업의 결과는 join()을 호출해서 얻을 수 있다.
-> 해당 작업의 수행이 끝날때까지 기다렸다가, 수행이 끝나면 그 결과를 반환한다. 동기 메서드다.

- form(), join() 둘의 중요한 차이는 fork()가 비동기 메서드이고 join()은 동기 메서드라는 것이다.

