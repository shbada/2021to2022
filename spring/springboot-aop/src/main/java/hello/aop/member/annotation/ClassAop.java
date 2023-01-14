package hello.aop.member.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // CLASS 에다 붙히는 어노테이션
@Retention(RetentionPolicy.RUNTIME) // RUNTIME (실제 실행) 때도 어노테이션이 살아있음
public @interface ClassAop {
}
