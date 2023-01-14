package hello.aop.member.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) // 런타임해야 동적으로 실행시점에 어노테이션을 읽을수있다.
public @interface MethodAop {
    // 어노테이션도 값을 가질 수 있다.
    String value();
}
