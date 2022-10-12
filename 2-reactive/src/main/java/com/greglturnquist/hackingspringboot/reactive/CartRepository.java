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

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author Greg Turnquist
 *
 * CartRepository
 * (Cart 관점에서 CartItem은 Item 객체와 수량 데이터만 포함한다. CartItemRepository는 필요 없지만,
 * CartRepository는 별도로 만들어야한다.)
 */
// tag::code[]
public interface CartRepository extends ReactiveCrudRepository<Cart, String> {

}
// end::code[]
