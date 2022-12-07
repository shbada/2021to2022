package com.example.springconsumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;

@SpringBootApplication
@Slf4j
public class SpringConsumerApplication_Batch {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringConsumerApplication_Batch.class);
        application.run(args);
    }

    /**
     * 컨슈머 레코드 묶음을 파라미터로 받는다.
     * 카프카 클라이언트 라이브러리에서 poll() 메서드로 리턴받은 ConsumerRecords를 리턴받아 사용하는 것과 동일하다.
     * @param records
     */
    @KafkaListener(topics = "test",
            groupId = "test-group-01")
    public void batchListener(ConsumerRecords<String, String> records) {
        records.forEach(record -> log.info(record.toString()));
    }

    /**
     * 메시지 값들을 List 자료구조를 받아서 처리한다.
     * @param list
     */
    @KafkaListener(topics = "test",
            groupId = "test-group-02")
    public void batchListener(List<String> list) {
        list.forEach(recordValue -> log.info(recordValue));
    }

    /**
     * 2개 이상의 컨슈머 스레드로 배치 리스너를 운영할 경우에 concurrency 옵션을 함께 사용한다.
     * 3으로 설정하면 3개의 컨슈머 스레드가 생성된다.
     * @param records
     */
    @KafkaListener(topics = "test",
            groupId = "test-group-03",
            concurrency = "3")
    public void concurrentBatchListener(ConsumerRecords<String, String> records) {
        records.forEach(record -> log.info(record.toString()));
    }

}