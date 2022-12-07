package com.greglturnquist.hackingspringboot.reactive;

import reactor.blockhound.BlockHound;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HackingSpringBootApplicationPlainBlockHound {

	// tag::blockhound[]
	public static void main(String[] args) {
		// 블록 하운드 등록
		// SpringApplication.run(...) 보다 앞에 위치한다.
		// 이렇게하면 스프링부트 애플리케이션을 시작할때 블록하운드가 바이트코드를 조작(instrument)할 수 있게 된다.
		BlockHound.install();

		SpringApplication.run(HackingSpringBootApplicationPlainBlockHound.class, args);
	}
	// end::blockhound[]
}
