package com.example.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Slf4j
public class SimpleProducer {
    private final static String TOPIC_NAME = "test";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties configs = new Properties();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        // 메시지 키, 값을 직렬화하기 위한 직렬화 클래스 선언
        // String 객체를 전송하므로 String 을 직렬화하는 클래스인 카프카의 라이브러리의 StringSerializer 사용
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(configs);

        String messageValue = "testMessage";
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, messageValue);

        // '배치 전송'
        // 파라미터로 들어간 record를 프로듀서 내부에 가지고 있다가, 배치 형태로 묶어서 브로커에 전송한다.
        RecordMetadata recordMetadata = producer.send(record).get();
        log.info("result : " + recordMetadata.toString());
        log.info("send : " + record);

        producer.flush();
        producer.close();

    }
}
