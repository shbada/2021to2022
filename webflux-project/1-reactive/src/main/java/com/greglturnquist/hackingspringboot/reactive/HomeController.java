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

import reactor.core.publisher.Mono;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	/**
	 * 반환타입 Mono<String> : 템플릿의 이름을 나타내는 문자열을 리액티브 컨테이너인 Mono에 담아서 반환
	 * @return
	 */
	@GetMapping
	Mono<String> home() {
		// 단순 템플릿 리턴일 경우는 Mono에 담아서 반환할 필요는 없다. 일단 단순하게 이렇게라도 사용해보았다.
		return Mono.just("home");
	}
}
// end::code[]