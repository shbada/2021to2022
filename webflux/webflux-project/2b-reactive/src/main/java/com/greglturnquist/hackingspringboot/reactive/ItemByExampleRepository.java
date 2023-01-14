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

/**
 * @author Greg Turnquist
 *
 * Example 타입의 파라미터를 인자로 받아서 검색을 수행하고 하나 또는 그 이상의 T 타입 값을 반환한다.
 * 정렬(Sort) 옵션도 줄 수 있고, 검색 결과 개수를 세거나 데이터 존재 여부를 반환하는 메서드도 있다.
 */
// tag::code[]
public interface ItemByExampleRepository extends ReactiveQueryByExampleExecutor<Item> {

}
// end::code[]
