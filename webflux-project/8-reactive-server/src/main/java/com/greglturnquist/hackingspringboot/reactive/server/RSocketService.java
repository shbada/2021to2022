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

package com.greglturnquist.hackingspringboot.reactive.server;

import reactor.core.publisher.*;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

/**
 * 새로운 Item 객체가 저장되면 스트림 갱신을 받도록 약속한 사람들에게 자동으로 정보를 제공해주자.
 * @author Greg Turnquist
 */
// tag::code[]
@Controller // <1>R소켓 트래픽을 처리하는 서비스
public class RSocketService {

	private final ItemRepository repository;
	// end::code[]

	// EmitterProcessor : 최근 메시지만 보내야 할 경우
	// ReplayProcessor : 최근 N개의 메시지를 보관하고 새로운 구독자에게 N개의 메시지를 모두 보내야 할 경우
	private final EmitterProcessor<Item> itemProcessor;

	private final FluxSink<Item> itemSink;

	//  Deprecated인 FluxProcessor, EmitterProcessor의 대체 구현
//	private final Sinks.Many<Item> itemsSink;

	// tag::code2[]
	public RSocketService(ItemRepository repository) {
		this.repository = repository; // <2>
		// end::code2[]
		// tag::code3[]
		/** EmitterProcessor는 단지 Flux를 상속받은 특별한 버전의 플럭스 */
		this.itemProcessor = EmitterProcessor.create(); // <1> 새 프로세서 생성
		this.itemSink = this.itemProcessor.sink(); // <2> EmitterProcessor에 새 Item을 추가하려면 진입점이 필요하며 이를 sink라고 한다. sink() 메서드를 호출해서 sink 를 얻는다.
		//  Deprecated인 FluxProcessor, EmitterProcessor의 대체 구현
//		this.itemsSink = Sinks.many().multicast().onBackpressureBuffer();

	}
	// end::code3[]

	// tag::request-response[]
	@MessageMapping("newItems.request-response") // <1> 도착지가 이 문자열로 지정된 R소켓 메시지를 이 메서드로 라우팅
	public Mono<Item> processNewItemsViaRSocketRequestResponse(Item item) { // <2> 스프링 메시징은 메시지가 들어오기를 리액티브하게 기다리고 있다가, 메시지가 들어오면 메시지 본문을 인자로 해서 save() 메서드를 호출한다.
		return this.repository.save(item) // <3>
				.doOnNext(savedItem -> this.itemSink.next(savedItem)); // <4>
				//  Deprecated인 FluxProcessor, EmitterProcessor의 대체 구현
//				.doOnNext(savedItem -> this.itemsSink.tryEmitNext(savedItem));
	}
	// end::request-response[]

	@MessageMapping("newItems.request-stream") // <1> R소켓 메시지 라우팅
	public Flux<Item> findItemsViaRSocketRequestStream() { // <2>
		return this.repository.findAll() // <3>
				.doOnNext(this.itemSink::next); // <4> 조회한 Item을 싱크를 통해 FluxProcessor로 내보낸다.
		//  Deprecated인 FluxProcessor, EmitterProcessor의 대체 구현
//				.doOnNext(this.itemsSink::tryEmitNext);
	}

	// tag::fire-and-forget[]
	@MessageMapping("newItems.fire-and-forget")
	// void가 아닌건, 리액티브 스트림 프로그래밍에서는 적어도 제어 신호를 받을 수 있는 수단은 반환해야한다. Mono<Void>
	public Mono<Void> processNewItemsViaRSocketFireAndForget(Item item) { // 데이터를 반환할 필요가 없다.
		return this.repository.save(item) //
				.doOnNext(savedItem -> this.itemSink.next(savedItem)) //
				//  Deprecated인 FluxProcessor, EmitterProcessor의 대체 구현
//				.doOnNext(savedItem -> this.itemsSink.tryEmitNext(savedItem))
				.then(); // Mono에 감싸져있는 데이터를 사용하지 않고 버릴 수 있다. 실제로 데이터는 사용되지 않고 리액티브 스트림의 제어 신호만 남게된다.
	}
	// end::fire-and-forget[]

	// tag::monitor[]
	@MessageMapping("newItems.monitor") // <1>
	public Flux<Item> monitorNewItems() { // <2>
		// 실제 반환은 EmitterProcessor 이다.
		// EmitterProcessor도 Flux 이므로 반환 타입에 맞는다.
		// EmitterProcessor에는 입수, 저장, 발행된 ITem 객체들이 들어있다.
		// 이 메서드를 구독하는 여러 주체들은 모두 EmitterProcessor에 담겨있는 Item 객체들의 복사본을 받게된다.
		return this.itemProcessor; // <3>
		//  Deprecated인 FluxProcessor, EmitterProcessor의 대체 구현
//		return this.itemsSink.asFlux();
	}
	// end::monitor[]
}
