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
// tag::code[]
package com.greglturnquist.hackingspringboot.reactive;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class KitchenService {

	/**
	 * Generates continuous stream of dishes.
	 * 요리 스트림 생성
	 */
	Flux<Dish> getDishes() {
		// sink : 무작위로 제공되는 요리를 둘러싸는 Flux의 핸들러로서, Flux에 포함될 원소를 동적으로 발행할 수 있게 해준다.
		return Flux.<Dish> generate(sink -> sink.next(randomDish())) // 요리 연속적으로 제공
				.delayElements(Duration.ofMillis(250)); // 250(ms) 간격으로 계속 제공
	}

	/**
	 * Randomly pick the next dish.
	 */
	private Dish randomDish() {
		return menu.get(picker.nextInt(menu.size()));
	}

	private List<Dish> menu = Arrays.asList( //
			new Dish("Sesame chicken"), //
			new Dish("Lo mein noodles, plain"), //
			new Dish("Sweet & sour beef"));

	private Random picker = new Random();
}
// end::code[]
