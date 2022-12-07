package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @Around : 메서드 호출 전후에 수행, 가장 강력한 어드바이스, 조인 포인트 실행 여부 선택, 반환 값 변환, 예외 변환 등이 가능
 * @Before : 조인 포인트 실행 이전에 실행
 * @AfterReturning : 조인 포인트가 정상 완료후 실행
 * @AfterThrowing : 메서드가 예외를 던지는 경우 실행
 * @After : 조인 포인트가 정상 또는 예외에 관계없이 실행(finally)
 */
@Slf4j
@Aspect
public class AspectV6Advice {
    /**
     * 모든 어드바이스는 org.aspectj.lang.JoinPoint 를 첫번째 파라미터에 사용할 수 있다. (생략해도 된다.)
     * 단 @Around 는 ProceedingJoinPoint 을 사용해야 한다.
     *
     * 스프링 아래 어노테이션 실행 순서
     * - 동일한 종류의 어드바이스가 2개 있으면 순서 보장은 안된다. 이땐 AspectV5Order.java 처럼 수행해야한다.
     * @Around , @Before , @After , @AfterReturning , @AfterThrowing
     * (호출 순서와 리턴 순서는 반대)
     */


    /**
     - 조인 포인트 실행 여부 선택 joinPoint.proceed() 호출 여부 선택
     - 전달 값 변환: joinPoint.proceed(args[])
     - 반환 값 변환
     - 예외 변환
     - 트랜잭션 처럼 try ~ catch~ finally 모두 들어가는 구문 처리 가능
     */
    @Around("hello.aop.order.aop.Pointcuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            // @Before
            log.info("[around][트랜잭션 시작] {}", joinPoint.getSignature());

            /* proceed() 를 여러번 실행할 수도 있음(재시도) */
            Object result = joinPoint.proceed(); // 직접 호출을 해줘야한다.

            // @AfterReturning
            log.info("[around][트랜잭션 커밋] {}", joinPoint.getSignature());

            // 위의 joinPoint.proceed() 호출 없이 아래 return null; 일 경우
            // 빼먹으면 아예 호출이 안된다. 특정 로직만 실행된다.
            // 위의 커밋 까지의 로그만 출력되고 그 이후로는 실행이 되지 않는다.
            return result;
        } catch (Exception e) {
            // @AfterThrowing
            log.info("[around][트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            // @After
            log.info("[around][리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }

    @Before("hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[before] {}", joinPoint.getSignature());
        // 메서드 종료시, 예외가 발생하지 않았을때 다음 타켓이 자동으로 호출된다.
    }

    @AfterReturning(value = "hello.aop.order.aop.Pointcuts.orderAndService()",
            returning = "result") // return 값이 아래 파라미터 result 와 일치하게, 그리고 결과값이 들어온다.
    // return 값을 바꿀수는 없다. 조작은 가능하다.
    public void doReturn(JoinPoint joinPoint, Object result) {
        log.info("[return] {} return={}", joinPoint.getSignature(), result);
    }

    @AfterReturning(value = "hello.aop.order.aop.Pointcuts.allOrder()",
            returning = "result") // return 값이 아래 파라미터 result 와 일치하게, 그리고 결과값이 들어온다.
    // return 값을 바꿀수는 없다. 조작은 가능하다.
    /* String 일때도 가능. 메서드의 returnType 이 String이므로. String의 상위는 Object 이므로 Object 도 가능한것 */
    /* 근데 만약, Integer 로 한다면? 호출이 안됨 */
    public void doReturn2(JoinPoint joinPoint, String result) {
        log.info("[return] {} return={}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "hello.aop.order.aop.Pointcuts.orderAndService()",
            throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[ex] {} message={}", joinPoint.getSignature(), ex.getMessage());
    }

    @After(value = "hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[after] {}", joinPoint.getSignature());
    }
}
