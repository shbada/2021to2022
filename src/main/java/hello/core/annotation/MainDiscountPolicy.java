package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
// 해당 어노테이션을 쓰면 위 기능이 모두 동작한다.
// @Qualifier("mainDiscountPolicy") 을 어노테이션으로 대체
// 이렇게 하면 코드 추적도 가능
public @interface MainDiscountPolicy {
}
