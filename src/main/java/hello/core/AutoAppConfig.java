package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

@Configuration
/**
 * @Component 가 붙은 모든 클래스를 스프링 빈으로 등록
 * 스프링 빈의 기본 이름은 클래스명 (맨 앞글자 소문자)
 * 이름도 지정은 가능 @Component("");
 *
 */
@ComponentScan( // 기존 AppConfig 와는 다르게 @Bean 으로 등록한 클래스가 하나도 없다. @Component 붙힌 클래스들을 모두 스캔하여 빈 등록한다.
        // @Configuration 이 붙어있는 클래스는 제외할 것이다. (이미 수동으로 등록하므로 등록된다면 충돌이 발생할 것임)
        // @Configuration 안에 들어가면 @Component 가 있음.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // 뺄것을 지정
) // 스프링 빈을 긁어서 자동으로 모두 등록해주는 것.
public class AutoAppConfig {

}
