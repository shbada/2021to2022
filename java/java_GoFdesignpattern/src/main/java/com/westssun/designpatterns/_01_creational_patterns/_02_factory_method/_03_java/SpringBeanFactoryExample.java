package com.westssun.designpatterns._01_creational_patterns._02_factory_method._03_java;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanFactoryExample {

    public static void main(String[] args) {
        // BeanFactory 가 팩토리 패턴을 구현한 구현체라고 볼수 있다. : ShipFactory
        // BeanFactory 가 이전에 creater 인터페이스라고 볼수 있다. : WhiteShipFactory
        // ClassPathXmlApplicationContext, AnnotationConfigApplicationContext : ConcreteCreator 의 구현체
        BeanFactory xmlFactory = new ClassPathXmlApplicationContext("config.xml"); // xml 설정
        String hello = xmlFactory.getBean("hello", String.class);
        System.out.println(hello);

        BeanFactory javaFactory = new AnnotationConfigApplicationContext(Config.class); // 자바 설정
        String hi = javaFactory.getBean("hello", String.class);
        System.out.println(hi);
    }
}
