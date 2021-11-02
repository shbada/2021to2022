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
 * @Component 말고도 @Controller, @Service, @Repository, @Configuration 도 추가로 대상에 포함한다.
 * 어노테이션에는 상속관계가 없다. (어노테이션에 어노테이션을 붙혔다해서 연동된다거나 하는 등의 긴으은 없다)
 * 어노테이션이 특정 어노테이션을 들고있는것을 인식하는것은 스프링이 지원하는 기능이다.
 *
 * @Controller : 스프링 MVC 컨트롤러로 인식
 * @Repository : 스프링 데이터 접근 계층으로 인식, 데이터 계층의 예외를 스프링 예외로 변환
 * @Configuration : 스프링 설정 정보로 인식하고 스프링 빈이 싱글톤을 유지하돌고 추가 처리
 * @Service : 별다른 처리는 없고, 개발자들이 핵심 비즈니스 로직이 여기에 있껬구나 라고 비즈니스 계층을 인식하는데 도움을 준다.
 */
@ComponentScan( // 기존 AppConfig 와는 다르게 @Bean 으로 등록한 클래스가 하나도 없다. @Component 붙힌 클래스들을 모두 스캔하여 빈 등록한다.
        // @Configuration 이 붙어있는 클래스는 제외할 것이다. (이미 수동으로 등록하므로 등록된다면 충돌이 발생할 것임)
        // @Configuration 안에 들어가면 @Component 가 있음.
        /** basePackages 지정 안하면 default 는? @Compoent 이 붙은 설정 정보 클래스의 패키지가 시작위치가 된다.
         *  설정 정보 클래스를 프로젝트 최상단에 두자. (권장)
         *  com.hello, com.hello.service 등의 구조일때 com.hello/클래스를 둔다는것 (@ComponentScan 을 붙이고)
         *  이렇게해서 basePackages 는 생략하자.
         *  스프링 부트는 @SpringBootApplication 에 @ComponentScan 이 들어가있는데, 이 클래스는 최상단 경로에 위치해있다.
         *  그래서 스프링부트를 쓰면 자동으로 빈이 등록된다.
         * */
        //basePackages = "hello.core", // package 지정 가능 (해당 위치에서부터 시작하여 빈을 탐색한다; 시작위치 지정) 이거 안쓰면 라이브러리까지 다 찾음
        //basePackageClasses = AutoAppConfig.class, // 해당 클래스의 패키지로 시작점 지정
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // 뺄것을 지정
) // 스프링 빈을 긁어서 자동으로 모두 등록해주는 것.
public class AutoAppConfig {

}
