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
public class CustomPartitionerProducer {
    private final static String TOPIC_NAME = "test";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties configs = new Properties();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        // 메시지 키, 값을 직렬화하기 위한 직렬화 클래스 선언
        // String 객체를 전송하므로 String 을 직렬화하는 클래스인 카프카의 라이브러리의 StringSerializer 사용
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // custom partitioner 설정
        configs.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartitioner.class);

        KafkaProducer<String, String> producer = new KafkaProducer<>(configs);

        String messageValue = "testMessage";
//        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, messageValue);

        // 메시지 키가 포함된 레코드 전송 (토픽 이름, 메시지 키, 메시지 값 순서)
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, "key", messageValue);

        // '배치 전송'
        // 파라미터로 들어간 record를 프로듀서 내부에 가지고 있다가, 배치 형태로 묶어서 브로커에 전송한다.
        /*
        .get() : send()가 Future 객체를 반환하는데, 이 객체는 RecordMetadata의 비동기 결과를 표현하는 것으로
                 ProducerRecord가 카프카 브로커에 정상적으로 적재되었는지에 대한 데이터가 포함되어있다.
                 get()을 사용하여 프로듀서로 보낸 데이터의 결과를 동기적으로 가져온다.

                 send()의 결괏값은 카프카 브로커로부터 응답을 기다렸다가 브로커로부터 응답이 오면 RecordMetadata 인스턴스를 반환한다.

         test1@2 -> 1: 파티션 번호, 2: 오프셋 번호

         그러나 동기로 프로듀서의 전송 결과를 확인하는 것은 빠른 전송에 허들이 될 수 있다.
         프로듀서가 전송하고 난 뒤, 브로커로부터 전송에 대한 응답 값을 받기 전까지 대기하기 때문이다.
         따라서 이를 원하지 않는 경우를 위해 프로듀서는 비동기로 결과를 확인할 수 있도록 Callback 인터페이스를 제공하고 있다.
         사용자는 사용자 정의 Callback 클래스를 생성하여 레코드의 전송 결과에 대응하는 로직을 만들 수 있다.
         */
//        RecordMetadata recordMetadata = producer.send(record).get();
//        log.info("result : " + recordMetadata.toString());
        // 비동기로 결과를 받을 경우, 동기로 결과를 받는 경우보다 더 빠른 속도로 데이터를 추가 처리할 수 있지만,
        // 전송하는 데이터의 순서가 중요한 경우 사용하면 안된다.
        // 비동기로 결과를 기다리는 동안 다음으로 보낼 데이터읮 ㅓㄴ송이 성공하고 앞서 보낸 데이터의 결과가 실패할 경우
        // 재전송으로 인해 데이터 순서가 역전될 수 있다.
        producer.send(record, new ProducerCallback()); // callback 클래스 설정
        log.info("send : " + record);

        producer.flush();
        producer.close();

    }
}
