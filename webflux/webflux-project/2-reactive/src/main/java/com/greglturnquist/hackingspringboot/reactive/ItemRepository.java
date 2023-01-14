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

// tag::code[]
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ItemRepository extends ReactiveCrudRepository<Item, String> {

    /*
        제공 메서드 : save(), saveAll(), findById(), findAll() 등
        -> Mono나 Flux 둘 중 하나를 리턴한다.
        Mono나 Flux를 구독하고 있다가 몽고디비가 데이터를 제공할 준비가 됐을때 데이터를 받을 수 있게된다.
        이 메서드 중 일부는 리액티브 스트림의 Publisher 타입을 인자로 받을 수 있다.
     */
}
// end::code[]
