package com.jpa.bookmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing /* 선언 (AuditingEntityListener 사용) */
public class BookmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookmanagerApplication.class, args);
    }

    /**
     * 영속성 컨텍스트 (persistence context)
     * 컨텍스트(맥락) : 컨테이너들이 관리하고있는 내용들을 일컫는다. (스프링 빈들을 로딩하고 관리하는 모든 일련의 작업들은 스프링 컨텍스트가 함)
     *
     * persistence 란? (=영속화)
     * 사라지지않고 지속적으로 접근 가능한것
     *
     * JPA 도 사실은 java persistence api 의 줄임말이다. 데이터를 영속화하는데 사용하는 것이 영속성 컨텍스트다.
     * -> EntityManager.java
     * (persistence.xml 을 생성하여 직접 사용할 수도 있음) : META-INF/persistence.xml
     * 위 방법 보다는 스프링 부트 JPA 를 사용해서 더 편하게 영속성을 설정할 수 있음.
     *
     * spring-boot-starter-data-jpa dependency : 스프링부트가 JPA 관련 설정을 모두 해준다.
     *
     *
     */
}
