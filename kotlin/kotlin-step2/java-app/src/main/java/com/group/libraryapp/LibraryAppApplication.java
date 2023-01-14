package com.group.libraryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Kotlin 리팩토링 계획 세우기
 * 1) Domain
 * 2) Repository (Spring Bean이고, 다른 스프링 bean 의존성 X)
 * 3) Service (비즈니스 로직)
 * 4) Controller, DTO ()
 */
@SpringBootApplication
public class LibraryAppApplication {

  public static void main(String[] args) {
    SpringApplication.run(LibraryAppApplication.class, args);
  }

}
