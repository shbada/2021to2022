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
// tag::controller[]
package com.greglturnquist.hackingspringboot.reactive;

import reactor.core.publisher.Flux;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Flux<T> : 실제 물건을 전달해주는 역할을 하는 placeholder
 * (레스토랑에서 일하는 서빙 점원)
 *
 * Flux<T>는 머지않아 전달될 결과를 담는 플레이스홀더일 뿐이라는 사실을 기억해야한다.
 * subscribe()를 호출하지 않으면 아무일도 일어나지 않는다!
 * 리액터 기반 애플리케이션에서는 구독하기 전까지는 아무일도 일어나지 않는다.
 * main() 메서드 안에서 subscribe()가 호출되어야 그때부터 뭔가가 동작하기 시작한다.
 */
@RestController
public class ServerController {

	private final KitchenService kitchen;

	public ServerController(KitchenService kitchen) {
		this.kitchen = kitchen;
	}

	@GetMapping(value = "/server", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	Flux<Dish> serveDishes() {
		/*
			Dish 객체 요청 - 완성된 후에 받을 수도 있지만, Flux<Dish> 객체로 바로 받는다.
			요리는 머지않아 완성될 것이다.
			요리 완성에 대한 반응 행동(act) : 리액트 (react)
			리액터는 논블로킹(non-blocking) 방식으로 동작하기 때문에,
			주방에서 요리가 완성될 때까지 서빙 점원(서버 스레드)이 다른 일을 못 한 채 계속 기다리게 하지 않는다.
			결과가 아직 정해지지 않았고 미래 어느 시점이 되어야 알 수 있다는 점에서 Flux는 Future와 비슷하다.
			결과를 미래에 알 수 있다는 관점에서 Future는 이미 시작됨을, Flux는 시작할 수 있음을 나타낸다.

			[Flux 제공 기능]
			- 하나 이상의 Dish 포함 가능
			- 각 Dish 가 제공될 때 어떤 일이 발생하는지 지정 가능
			- 성공과 실패의 두 가지 경로 모두에 대한 처리 방향 정의 가능
			- 결과 폴링 불필요
			- 함수형 프로그래밍 지원
		*/
		return this.kitchen.getDishes();
	}
	// end::controller[]

	/**
	 * 비동기 전달
	 * 구독은 누가할까? 스프링 웹플럭스가 구독한다.
	 * 개발자는 컨트롤러 메서드에서 리액터 타입을 반환하도록 작성하면 스프링 웹플럭스가 적절한 옵션과 함께 적절한 타이밍에 구독한다.
	 * @return
	 */
	// tag::deliver[]
	@GetMapping(value = "/served-dishes", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	Flux<Dish> deliverDishes() {
		return this.kitchen.getDishes() // 요리를 받아온다.
				// 리액티브 스트림의 onNext() 시그널을 받으면 kitchen에게 감사 인사를 한다.
//				.doOnNext(dish -> System.out.println("Thank you for " + dish))
				// onError() 시그널을 받으면 처리해야 할 일을 지정한다.
//				.doOnError(error -> System.out.println("So sorry about " + error.getMessage()))
				// 주방에서 모든 요리가 완성됐음을 의미하는 onComplete() 시그널을 받으면 처리해야 할 일을 지정해준다.
//				.doOnComplete(() -> System.out.println("Thanks for all your hard work!"))
				// map() : 인자로 받은 매핑 함수를 Flux에 담겨있는 각 요리에 적용해서 변환하고 Flux에 담아 반환하므로, 매핑 함수는 무언가를 반드시 반환해야한다.
				.map(dish -> Dish.deliver(dish)); // 요리 완성 후 해야할 일이다. (deliver() : 손님에게 요리를 가져다준다.)
	}
	// end::deliver[]
}
