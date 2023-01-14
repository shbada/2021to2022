/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.greglturnquist.hackingspringboot.reactive;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Collections;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.greglturnquist.hackingspringboot.reactive.AltInventoryService;
import com.greglturnquist.hackingspringboot.reactive.Cart;
import com.greglturnquist.hackingspringboot.reactive.CartItem;
import com.greglturnquist.hackingspringboot.reactive.CartRepository;
import com.greglturnquist.hackingspringboot.reactive.Item;
import com.greglturnquist.hackingspringboot.reactive.ItemRepository;

/**
 * @author Greg Turnquist
 */
// tag::1[]
@ExtendWith(SpringExtension.class) // <1>
public class BlockHoundIntegrationTest {

	AltInventoryService inventoryService; // <2>

	@MockBean ItemRepository itemRepository; // <3>
	@MockBean CartRepository cartRepository;
	// end::1[]

	// tag::2[]
	@BeforeEach
	void setUp() {
		// Define test data <1>

		Item sampleItem = new Item("item1", "TV tray", "Alf TV tray", 19.99);
		CartItem sampleCartItem = new CartItem(sampleItem);
		Cart sampleCart = new Cart("My Cart", Collections.singletonList(sampleCartItem));

		// Define mock interactions provided
		// by your collaborators <2>

		when(cartRepository.findById(anyString())) //
				/*
				비어있는 결과를 리액터로부터 감춘다.
				-> cardRepository.findItemById()는 Mono.empty()를 반환한다.
				Mono.empty()는 MonoEmpty 클래스의 싱글턴 객체를 반환한다.
				리액터는 이런 인스턴스를 감지하고 런타임에서 최적화한다.
				block() 호출이 없으므로 블록하운드는 아무것도 검출하지 않고 지나간다.
				이는 테스트 시나리오의 문제다.
				개발자는 장바구니가 없을때도 문제없이 처리하기를 바랐지만, 리액터는 필요하지 않다면 블로킹 호출을 친절하게 알아서 삭제한다.
				테스트 관점에서 이처럼 블로킹 호출이 알아서 제거되는 문제를 해결하기 위해 MonoEmpty를 숨겨서 리액터의 최적화 루틴한테 걸리지 않게 해야한다.
				"Mono.hide()의 주 목적은 진단을 정확하게 수행하기 위해 식별성 기준 최적화를 방지하는 것이다."
				 */
				.thenReturn(Mono.<Cart> empty().hide()); // <3>

		when(itemRepository.findById(anyString())).thenReturn(Mono.just(sampleItem));
		when(cartRepository.save(any(Cart.class))).thenReturn(Mono.just(sampleCart));

		inventoryService = new AltInventoryService(itemRepository, cartRepository);
	}
	// end::2[]

	// tag::3[]
	@Test
	void blockHoundShouldTrapBlockingCall() { //
		Mono.delay(Duration.ofSeconds(1)) // <1> 후속 작업이 리액터 스레드 안에서 실행되게 만든다.
				.flatMap(tick -> inventoryService.addItemToCart("My Cart", "item1")) // <2>
				.as(StepVerifier::create) // <3> addItemToCart()가 반환하는 Mono를 리액터 StepVerifier 로 전환한다.
				.verifyErrorSatisfies(throwable -> { // <4> 블로킹 호출이 있으므로 예외 발생
					assertThat(throwable).hasMessageContaining( //
							"block()/blockFirst()/blockLast() are blocking");
				});
	}
	// end::3[]
}
// end::code[]
