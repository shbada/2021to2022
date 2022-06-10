package com.example.springconsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class SpringConsumerApplication_Custom {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringConsumerApplication_Custom.class);
        application.run(args);
    }

    /**
     * customContainerFactory 를 옵션값으로 설정한다.
     * 커스텀 컨테이너 팩토리로 생성된 커스텀 리스너 컨테이너를 사용할 수 있다.
     * @param data
     */
    @KafkaListener(topics = "test",
            groupId = "test-group",
            containerFactory = "customContainerFactory")
    public void customListener(String data) {
        log.info(data);
    }
}
