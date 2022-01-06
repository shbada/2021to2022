package com.spring.batch;

import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.SimpleBatchConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.boot.autoconfigure.batch.JpaBatchConfigurer;

@SpringBootApplication
/**
 * 스프링 배치가 작동하기 위해 선언해야하는 어노테이션
 *
 * 총 4개의 설정 클래스를 실행
 * 스프링 배치의 모든 초기화 / 실행 구성이 이루어짐
 * 스프링 부트 배치의 자동 설정 클래스가 실행됨으로 빈으로 등록된 모든 Job을 검색해서 초기화와
   동시에 Job을 수행할 수 있도록 구성
 * 수동으로 Job을 실행시킬 수도 있지만, 자동으로 실행해준다.
 *
 * 3. BatchAutoConfiguration
 * : 스프링 배치가 초기화될 때 자동으로 실행되는 설정 클래스
 * : ApplicationRunner 의 구현체들을 모두 찾는다.
 * : Job을 수행하는 JobLauncherApplicationRunner 빈 생성 (스프링 배치가 자동으로 잡을 실행해주는 빈)
 *
 * 1. SimpleBatchConfiguration
 * : JobBuilderFactory, StepBuilderFactory 생성
 * : 스프링 배치의 주요 구성 요소 생성 - 프록시 객체로 생성
 *
 * 2. BatchConfigurerConfiguration
 *   >BasicBatchConfigurer : SimpleBatchConfiguration 에서 생성한 프록시 객체의 실제 대상 객체를 생성하는 설정 클래스
 *   >JpaBatchConfigurer : JPA 관련 객체를 생성하는 설정 클래스 (BasicBatchConfigurer를 상속)
 *   (사용자 정의 BatchConfigurer 인터페이스를 구현하여 사용할 수 있음)
 */
@EnableBatchProcessing
public class BatchApplication {
    // SimpleBatchConfiguration
    // BatchConfigurer
    // BasicBatchConfigurer
    // JpaBatchConfigurer
    // BatchAutoConfiguration

    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }

}
