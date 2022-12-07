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

package com.greglturnquist.hackingspringboot.reactive.client;

import static io.rsocket.metadata.WellKnownMimeType.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.*;

import java.net.URI;
import java.time.Duration;

import org.springframework.http.MediaType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Greg Turnquist
 */
// tag::code[]
@RestController // <1>
public class RSocketController {

	private final Mono<RSocketRequester> requester; // <2> R소켓에 연결된 코드는 새 클라이언트가 구독할때마다 호출된다.

	/*
	RSocketRequester 란?
	R소켓에 무언가를 보낼때 사용하는 얇은 포장재와 같다. 결국 R소켓의 API는 프로젝트 리액터를 사용한다.
	RSocketRequester를 사용해야 스프링 프레임워크와 연동된다.
	이렇게 하면 도착지를 기준으로 메시지를 라우팅할 수 있다.
	그리고 보너스로 트래픽의 인코딩/디코딩도 쉽게 할 수 있다.
	RSocketRequester를 사용하지 않으면 클라이언트-서버 양쪽의 모든 R소켓 연결에서 데이터를 직접 관리해야한다.

	Mono로 감싸는 이유는?
	리액터의 Mono 패러다임은 연결을 R소켓 연결 세부정보를 포함하는 지연구조체로 전환한다.
	아무도 연결하지 않으면 R소켓은 열리지 않는다.
	누군가가 구독을 해야 세부정보가 여러 구독자에 공유될 수 있다.

	하나의 R소켓만으로 모든 구독자에게 서비스할 수 있다는 점도 중요하다.
	R소켓을 구독자마다 1개씩 만들 필요가 없다.
	대신에 하나의 R소켓 파이프에 대해 구독자별로 하나씩 연결을 생성한다.

	이렇게 준비과정을 마쳐야 R소켓이 네트워크를 통해 오가는 데이터 프레임을 리액티브하게 전송하고 배압을 처리하는데
	집중할 수 있다.
	스프링 프레임워크는 데이터 인코딩/디코딩과 라우팅을 담당할 수 있다.
	리액터는 요청 처리 전 과정을 지연 방식으로 수행할 수 있어서 자원 효율성을 높일 수 있다.
	 */
	public RSocketController(RSocketRequester.Builder builder) { // <3> RsocketRequesterAutoConfiguration 정책 안에서 자동 설정으로 RSocketRequester.Builder 빈 생성
		this.requester = builder //
				.dataMimeType(APPLICATION_JSON) // <4>
				.metadataMimeType(parseMediaType(MESSAGE_RSOCKET_ROUTING.toString())) // <5>
				.connectTcp("localhost", 7000) // <6> 7000번 포트를 사용하는 R소켓 서버에 연결
				.retry(5) // <7> 메시지 처리 실패시 5번까지 재시도 가능
				.cache(); // <8> 요청 Mono를 핫 소스(hot source)로 전환 - 핫 소스에서는 가장 최근의 신호는 캐시돼있을 수도 있으며 구독자는 사본을 가지고있을 수 있다.
	}
	// end::code[]

	// tag::request-response[]
	@PostMapping("/items/request-response") // <1>
	Mono<ResponseEntity<?>> addNewItemUsingRSocketRequestResponse(@RequestBody Item item) {
		return this.requester //
				.flatMap(rSocketRequester -> rSocketRequester //
						.route("newItems.request-response") // <2>
						.data(item) // <3>
						.retrieveMono(Item.class)) // <4> Mono<Item>을 원한다는 신호를 보낸다.
				.map(savedItem -> ResponseEntity.created( // <5>
						URI.create("/items/request-response")).body(savedItem));
	}
	// end::request-response[]

	@GetMapping(value = "/items/request-stream", produces = MediaType.APPLICATION_NDJSON_VALUE) // <1>
	Flux<Item> findItemsUsingRSocketRequestStream() {
		return this.requester //
				.flatMapMany(rSocketRequester -> rSocketRequester // <2> flatMapMany : 여러건의 조회 결과를 Flux에 담아 반환할 수 있도록 한다.
						.route("newItems.request-stream") // <3>
						.retrieveFlux(Item.class) // <4> Flux<Item>
						.delayElements(Duration.ofSeconds(1))); // <5>
	}

	// tag::fire-and-forget[]
	@PostMapping("/items/fire-and-forget")
	Mono<ResponseEntity<?>> addNewItemUsingRSocketFireAndForget(@RequestBody Item item) {
		return this.requester //
				.flatMap(rSocketRequester -> rSocketRequester //
						.route("newItems.fire-and-forget") // <1>
						.data(item) //
						.send()) // <2> Mono<Void>
				.then( // <3> 망각 예제에서는 Mono<Void>를 반환받으므로 map()으로 해도 아무일도 일어나지 않는다.
						// 그래서 새로 생성된 Item에 대한 Location 헤더 값을 포함하는 HTTP 201 Created를 반환하려면 map()이 아닌 then()과 Mono.just()(를 사용해서 Mono를 새로 만들어서 반환해야한다.
						Mono.just( //
								ResponseEntity.created( //
										URI.create("/items/fire-and-forget")).build()));
	}
	// end::fire-and-forget[]

	// tag::request-stream[]
	@GetMapping(value = "/items", produces = TEXT_EVENT_STREAM_VALUE) // <1> 응답할 결과가 생길때마다 결괏값을 스트림에 흘려보낸다는 것을 의미한다.
	Flux<Item> liveUpdates() {
		return this.requester //
				.flatMapMany(rSocketRequester -> rSocketRequester //
						.route("newItems.monitor") // <2>
						.retrieveFlux(Item.class)); // <3> Flux<Item>
	}
	// end::request-stream[]
}
