package com.westssun.designpatterns._01_creational_patterns._01_singleton._실무에서;

import com.westssun.designpatterns._01_creational_patterns._01_singleton._마무리_실제예제._01_singleton.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringExample {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);

        // 항상 같은 인스턴스가 나온다.
        // 싱글톤 패턴이라기 보다는, applicationContext 에서 유일한 객체로 관리되어 꺼낼때 같은 인스턴스다.
        String hello = applicationContext.getBean("hello", String.class);
        String hello2 = applicationContext.getBean("hello", String.class);

        // true
        System.out.println(hello == hello2);
    }

}
