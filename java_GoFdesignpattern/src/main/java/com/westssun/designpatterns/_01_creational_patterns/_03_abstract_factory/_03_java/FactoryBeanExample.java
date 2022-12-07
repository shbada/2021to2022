package com.westssun.designpatterns._01_creational_patterns._03_abstract_factory._03_java;

import com.westssun.designpatterns._01_creational_patterns._02_factory_method._02_after.Ship;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FactoryBeanExample {

    public static void main(String[] args) {
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config.xml");
//        Ship whiteship = applicationContext.getBean("whiteship", Ship.class);
//        System.out.println(whiteship.getName());

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(FactoryBeanConfig.class);
        ShipFactory bean1 = applicationContext.getBean(ShipFactory.class);
        Ship bean2 = applicationContext.getBean(Ship.class);
        System.out.println(bean1);
        System.out.println(bean2);
    }
}
