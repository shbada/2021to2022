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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.greglturnquist.hackingspringboot.reactive.Cart;
import com.greglturnquist.hackingspringboot.reactive.CartItem;
import com.greglturnquist.hackingspringboot.reactive.CartRepository;
import com.greglturnquist.hackingspringboot.reactive.InventoryService;
import com.greglturnquist.hackingspringboot.reactive.Item;
import com.greglturnquist.hackingspringboot.reactive.ItemRepository;

/**서비스 계층 단위 테스트
 * @author Greg Turnquist
 */
// tag::extend[]
@ExtendWith(SpringExtension.class) // <1>
class InventoryServiceUnitTest { // <2>
	// end::extend[]

	// tag::class-under-test[]
	InventoryService inventoryService; // <1> 테스트 대상 클래스다. 테스트할때 초기화된다.

	@MockBean
	private ItemRepository itemRepository; // <2> inventoryService에 주입되는 클래스 (가짜객체)

	@MockBean
	private CartRepository cartRepository; // <2> inventoryService에 주입되는 클래스 (가짜객체)
	// end::class-under-test[]

	// tag::before[]
	@BeforeEach // <1>
	void setUp() {
		// Define test data <2>
		Item sampleItem = new Item("item1", "TV tray", "Alf TV tray", 19.99);
		CartItem sampleCartItem = new CartItem(sampleItem);
		Cart sampleCart = new Cart("My Cart", Collections.singletonList(sampleCartItem));

		// Define mock interactions provided
		// by your collaborators <3>
		// 가짜 객체와의 상호작용을 정의한다.
		// 테스트 대상 클래스의 협력자가 리액티브하다면 테스트에 사용할 가짜 협력자는 리액터 타입을 반환해야한다.
		// 감싸는게 귀찮지만, 이걸 피하려면 리액터용 별도의 모키토 API를 사용해야한다.
		// 그러나 이 모키토 API를 사용하면 블록하운드가 잘못 사용된 블로킹 코드를 검출하기가 매우 어려워질 수 있다.
		when(cartRepository.findById(anyString())).thenReturn(Mono.empty());
		when(itemRepository.findById(anyString())).thenReturn(Mono.just(sampleItem));
		when(cartRepository.save(any(Cart.class))).thenReturn(Mono.just(sampleCart));

		// 가짜 협력자를 생성자에 주입하면서 테스트 대상 클래스를 생성한다.
		inventoryService = new InventoryService(itemRepository, cartRepository); // <4>
	}
	// end::before[]

	// tag::test[]
	@Test
	void addItemToEmptyCartShouldProduceOneCartItem() { // <1>
		inventoryService.addItemToCart("My Cart", "item1") // <2>
				// StepVerifier가 구독을 한다.
				// 결괏값을 얻기위해 블로킹 방식으로 기다리는 대신에 리액터의 테스트 도구가 대신 구독을 하고 값을 확인할 수 있게 해준다.
				.as(StepVerifier::create) // <3> StepVerifier::create 메서드 래퍼런스로 연결 : 테스트 기능을 전담하는 리액터 타입 핸들러 생성
				.expectNextMatches(cart -> { // <4> 함수와 람다실을 사용해서 결과를 검증
					assertThat(cart.getCartItems()).extracting(CartItem::getQuantity) // 상품 추출
							.containsExactlyInAnyOrder(1); // <5> 1개만 존재함을 검증

					assertThat(cart.getCartItems()).extracting(CartItem::getItem) //
							.containsExactly(new Item("item1", "TV tray", "Alf TV tray", 19.99)); // <6>

					return true; // <7> 이 지점까지 왔다면 true를 반환
				}) //
				// onComplete() 시그널을 확인하면 의도한대로 테스트가 동작했음이 보장된다.
				.verifyComplete(); // <8> 리액티브 스트림의 complete 시그널이 발생하고 리액터 플로우가 성공적으로 완료됐음을 검증한다.
	}
	// end::test[]

	// tag::test2[]

	/**
	 * addItemToEmptyCartShouldProduceOneCartItem()가 더 좋은 방법으로 보인다.
	 * 메서드의 인자까지 뒤져봐야 무엇이 테스트되는지를 알 수 있기 때문이다.
	 */
	@Test
	void alternativeWayToTest() { // <1>
		StepVerifier.create( //
				inventoryService.addItemToCart("My Cart", "item1")) //
				.expectNextMatches(cart -> { // <4>
					assertThat(cart.getCartItems()).extracting(CartItem::getQuantity) //
							.containsExactlyInAnyOrder(1); // <5>

					assertThat(cart.getCartItems()).extracting(CartItem::getItem) //
							.containsExactly(new Item("item1", "TV tray", "Alf TV tray", 19.99)); // <6>

					return true; // <7>
				}) //
				.verifyComplete(); // <8>
	}
	// end::test2[]

}
