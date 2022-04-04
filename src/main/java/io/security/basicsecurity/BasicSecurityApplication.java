package io.security.basicsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// springBoot 에 내장된 톰캣 실행
@SpringBootApplication
public class BasicSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicSecurityApplication.class, args);
	}

}
