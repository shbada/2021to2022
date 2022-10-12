package com.greglturnquist.hackingspringboot.reactive;

import org.thymeleaf.TemplateEngine;
import reactor.blockhound.BlockHound;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.JdkIdGenerator;
import org.springframework.web.filter.reactive.HiddenHttpMethodFilter;

@SpringBootApplication
public class HackingSpringBootApplicationBlockHoundCustomized {

	// tag::blockhound[]
	public static void main(String[] args) {
		BlockHound.builder() // <1>
				.allowBlockingCallsInside( //
						// TemplateEngine.class.getCanonicalName() 를 허용 리스트에 추가
						TemplateEngine.class.getCanonicalName(), "process") // <2>
				.install(); // <3> install()하면 커스텀 설정이 적용된 블록 하운드가 애플리케이션에 심어진다.

		SpringApplication.run(HackingSpringBootApplicationBlockHoundCustomized.class, args);
	}
	// end::blockhound[]
}
