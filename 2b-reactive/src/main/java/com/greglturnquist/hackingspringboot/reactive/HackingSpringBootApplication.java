package com.greglturnquist.hackingspringboot.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.reactive.HiddenHttpMethodFilter;

/**
 * 쿼리 방법별 장단점
 * 1) 표준 CRUD 메서드
 * [장점]
 * - 이미 정의되어 있음
 * - 소스 코드로 작성돼있음
 * - 리액터 타입을 포함해서 다양한 반환 타입 지원
 * - 데이터 스토어 간 호환성
 *
 * [단점]
 * - 1개 또는 전부에만 사용 가능
 * - 도메인 객체별로 별도의 인터페이스 작성 필요
 *
 * 2) 메서드 이름 기반 쿼리
 * [장점]
 * - 직관적
 * - 쿼리 자동 생성
 * - 리액터 타입을 포함한 다양한 반환 타입 지원
 * - 여러 데이터 스토어에서 모두 지원
 *
 * [단점]
 * - 도메인 객체마다 리포지토리 작성 필요
 * - 여러 필드와 조건이 포함된 복잡한 쿼리에 사용하면 메서드 이름이 매우 길어지고 불편
 *
 * 3) Example 쿼리
 * [장점]
 * - 쿼리 자동 생성
 * - 모든 쿼리 조건을 미리 알 수 없을때 유용
 * - JPA, 레디스(Redis)에서도 사용 가능
 *
 * [단점]
 * - 도메인 객체마다 리포지토리 작성 필요
 *
 * 4) MongoOperations
 * [장점]
 * - 데이터 스토어에 특화된 기능까지 모두 사용 가능
 * - 도메인 객체마다 별도의 인터페이스 작성 불필요
 *
 * [단점]
 * - 데이터 스토어에 종속적
 *
 * 5) @Query 사용 쿼리
 * [장점]
 * - 몽고QL 사용 가능
 * - 긴 메서드 이름 불필요
 * - 모든 데이터 스토어에서 사용 가능
 *
 * [단점]
 * - 데이터 스토어에 종속적
 *
 * 6) 평문형 API
 * [장점]
 * - 직관적
 * - 도메인 객체마다 별도의 인터페이스 작성 불필요
 *
 * [단점]
 * - 데이터 스토어에 종속적
 * - JPA와 레디스에서도 사용할 수 있지만 호환은 안됨
 */
@SpringBootApplication
public class HackingSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackingSpringBootApplication.class, args);
	}
}
