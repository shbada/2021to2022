package hello.aop.order.aop;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    //hello.aop.order 패키지와 하위 패키지
    @Pointcut("execution(* hello.aop.order..*(..))") //pointcut expression
    public void allOrder() {} //pointcut signature

    //클래스 이름 패턴이 *Service (보통 트랜잭션은 service 에 들어가니깐 service 로 해보자)
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {}

    // allOrder && AllService
    @Pointcut("allOrder() && allService()")
    public void orderAndService() {}
}
