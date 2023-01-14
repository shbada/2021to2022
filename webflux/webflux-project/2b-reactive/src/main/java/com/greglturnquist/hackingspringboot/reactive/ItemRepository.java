/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.greglturnquist.hackingspringboot.reactive;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import reactor.core.publisher.Flux;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

// tag::code[]
public interface ItemRepository extends ReactiveCrudRepository<Item, String>, ReactiveQueryByExampleExecutor<Item> {

	/* 검색어로 상품 목록 조회 */
	Flux<Item> findByNameContaining(String partialName);
	// end::code[]

	// tag::code-2[]
	/* @Query 어노테이션이 붙은 메서드는 리포지토리 메서드 이름 규칙에 의해 자동 생성되는 쿼리문 대신,
	@Query 내용으로 개발자가 직접 명시한 쿼리문을 사용한다. */
//	@Query("{ 'name' : ?0, 'age' : ?1 }")
//	Flux<Item> findItemsForCustomerMonthlyReport(String name, int age);
//
//	@Query(sort = "{ 'age' : -1 }")
//	Flux<Item> findSortedStuffForWeeklyReport();
	// end::code-2[]

	// tag::code-3[]
	// search by name
	Flux<Item> findByNameContainingIgnoreCase(String partialName);

	// search by description
	Flux<Item> findByDescriptionContainingIgnoreCase(String partialName);

	// 메서드 이름이 매우 복잡하다.
	// search by name AND description
	Flux<Item> findByNameContainingAndDescriptionContainingAllIgnoreCase(String partialName, String partialDesc);

	// search by name OR description
	Flux<Item> findByNameContainingOrDescriptionContainingAllIgnoreCase(String partialName, String partialDesc);
	// end::code-3[]
}
