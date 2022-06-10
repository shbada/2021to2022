package com.example.springconsumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

@SpringBootApplication
@Slf4j
public class SpringConsumerApplication_Batch_Commit {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringConsumerApplication_Batch_Commit.class);
        application.run(args);
    }

    /**
     * AckMode를 MANUAL, MANUAL_IMMEDIATE로 사용할 경우에는 수동 커밋을 해야한다.
     * 수동 커밋을 하기위해 파라미터로 Acknowledgment 인스턴스를 받아야한다.
     * acknowledge() 를 호출함으로써 커밋을 수행한다.
     * @param records
     * @param ack
     */
    @KafkaListener(topics = "test", groupId = "test-group-01")
    public void commitListener(ConsumerRecords<String, String> records, Acknowledgment ack) {
        records.forEach(record -> log.info(record.toString()));
        ack.acknowledge();
    }

    /**
     * 동기 커밋, 비동기 커밋을 사용하고 싶다면 컨슈머 인스턴스를 파라미터로 받아서 사용할 수 있다.
     * commitSync(), commitAsync() 메서드를 호출하면 사용자가 원하는 타이밍에 커밋할 수 있도록 로직을 추가할 수 있다.
     * 리스너가 커밋을 하지 않도록 AckMode를 MANUAL, MANUAL_IMMEDIATE로 설정해야한다.
     * @param records
     * @param consumer
     */
    @KafkaListener(topics = "test", groupId = "test-group-02")
    public void consumerCommitListener(ConsumerRecords<String, String> records, Consumer<String, String> consumer) {
        records.forEach(record -> log.info(record.toString()));
        consumer.commitSync();
    }

}