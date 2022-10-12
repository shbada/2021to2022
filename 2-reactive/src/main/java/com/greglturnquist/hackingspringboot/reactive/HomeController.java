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

import org.springframework.web.bind.annotation.DeleteMapping;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.result.view.Rendering;

/**
 * @author Greg Turnquist
 */
// tag::1[]
@Controller // <1>
public class HomeController {

	private final ItemRepository itemRepository;
	private final CartRepository cartRepository;

	public HomeController(ItemRepository itemRepository, // <2>
			CartRepository cartRepository) {
		this.itemRepository = itemRepository;
		this.cartRepository = cartRepository;
	}
	// end::1[]

	/**
	 * 장바구니를 보여줌
	 * 웹플럭스 컨테이너 Mono<Rendering> 리턴
	 * @return
	 */
	// tag::2[]
	@GetMapping
	Mono<Rendering> home() { // <1>
		return Mono.just(Rendering.view("home.html") // view(...)로 랜더링에 사용할 템플릿 이름 지정
				.modelAttribute("items", //
						this.itemRepository.findAll()) // <3>
				.modelAttribute("cart", //
						this.cartRepository.findById("My Cart") // <4>
								.defaultIfEmpty(new Cart("My Cart")))
				.build());
	}
	// end::2[]

	// tag::3[]

	/**
	 * 장바구니에 상품 추가
	 * @param id
	 * @return
	 */
	@PostMapping("/add/{id}") // <1>
	Mono<String> addToCart(@PathVariable String id) { // <2>
		return this.cartRepository.findById("My Cart") //
				.defaultIfEmpty(new Cart("My Cart")) // <3>
				.flatMap(cart -> cart.getCartItems().stream() // <4>
						.filter(cartItem -> cartItem.getItem() //
								.getId().equals(id)) //
						.findAny() //
						.map(cartItem -> {
							cartItem.increment(); /* 동일한 상품의 경우 수량만 증가 */
							return Mono.just(cart);
						}) //
						.orElseGet(() -> { // <5>
							return this.itemRepository.findById(id) //
									.map(item -> new CartItem(item)) //
									.map(cartItem -> {
										/* 장바구니에 추가 */
										cart.getCartItems().add(cartItem);
										return cart;
									});
						}))
				.flatMap(cart -> this.cartRepository.save(cart)) // <6> 몽고DB 저장
				.thenReturn("redirect:/"); // <7>
	}
	// end::3[]

	@PostMapping
	Mono<String> createItem(@ModelAttribute Item newItem) {
		return this.itemRepository.save(newItem) //
				.thenReturn("redirect:/");
	}

	@DeleteMapping("/delete/{id}")
	Mono<String> deleteItem(@PathVariable String id) {
		return this.itemRepository.deleteById(id) //
				.thenReturn("redirect:/");
	}
}
