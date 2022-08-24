package com.sample.api.dto.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD }) // 해당 어노테이션은 필드에만 선언 가능
@Retention(RUNTIME) // 해당 어노테이션이 유지되는 시간은 런타임까지 유효
@Constraint(validatedBy = UserIdDuplicatedValidator.class) // RequestSaveOmVenValidator로 유효성 검사 진행
public @interface UserIdDuplicatedTarget {
    /**
     * 유효하지 않을 경우 반환할 메시지
     * @return
     */
    String message() default "{userid.invalid}";

    /**
     * 유효성 검증이 진행될 그룹
     * @return
     */
    Class<?>[] groups() default { };

    /**
     * 유효성 검증 시에 전달할 메타 정보
     * @return
     */
    Class<? extends Payload>[] payload() default { };
}
