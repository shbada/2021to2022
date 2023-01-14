package com.example.springproducer;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringProducerApplication implements CommandLineRunner {
    private static String TOPIC_NAME = "test";

//    private final KafkaTemplate<Integer, String> template;
    private final KafkaTemplate<Integer, String> customKafkaTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringProducerApplication.class, args);
    }

//    @Override
//    public void run(String... args) {
//        for (int i = 0; i < 10; i++) {
//            template.send(TOPIC_NAME, "test" + i);
//        }
//
//        System.exit(0);
//    }

    @Override
    public void run(String... args) {
        ListenableFuture<SendResult<Integer, String>> future = customKafkaTemplate.send(TOPIC_NAME, "test");
        future.addCallback(new KafkaSendCallback<Integer, String>() {
            /* 프로듀서가 보낸 데이터의 브로커 적재 여부를 비동기로 확인 */
            @Override // 정상일 경우
            public void onSuccess(SendResult<Integer, String> result) {

            }

            @Override // 이슈가 발생했을 경우
            public void onFailure(KafkaProducerException ex) {

            }
        });

        System.exit(0);
    }
}

