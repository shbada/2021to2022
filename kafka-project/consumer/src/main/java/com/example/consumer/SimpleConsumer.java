package com.example.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@Slf4j
public class SimpleConsumer {
    private final static String TOPIC_NAME = "test";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";
    private final static String GROUP_ID = "test-group";

    public static void main(String[] args) {
        Properties configs = new Properties();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);

        /*
        컨슈머 그룹을 통해 컨슈머의 목적을 구분할 수 있다.
        동일한 역할을 하는 컨슈머를 묶어 관리할 수 있다.
        컨슈머 그룹을 기준으로 컨슈머 오프셋을 관리하기 때문에 subscribe() 메서드를 사용하여 토픽을 구독하는 경우에는,
        컨슈머 그룹을 선언해야한다.
        컨슈머가 중단되거나 재시작되더라도 컨슈머 그룹의 컨슈머 오프셋을 기준으로 이후 데이터를 처리하기 때문이다.
        컨슈머 그룹을 선언하지 않으면 어떤 그룹에도 속하지 않는 컨슈머로 동작하게 된다.
         */
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);

        // 프로듀서에서 직렬화하여 전송한 데이터를 역직렬화한다.
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(configs);

        // 컨슈머에게 토픽을 할당하기 위해 subscribe() 를 사용한다.
        // 이 메서드는 Collection 타입의 String 타입을 받는데, 1개 이상의 토픽 이름을 받을 수 있다.
        consumer.subscribe(Arrays.asList(TOPIC_NAME));

        while (true) {
            // poll() 메서드를 호출하여 데이터를 가져와서 처리한다.
            // 지속적으로 데이터를 처리하기 위해서 반복 호출을 해야한다.
            // 지속적으로 반복 호출하기 위한 가장 쉬운 방법은 while(true)처럼 무한루프를 만드는 것이다.
            // 무한루프 내부에서 poll 메서드를 통해 데이터를 가져오고 사용자가 원하는 데이터 처리를 수행한다.
            // Duration 타입을 인자로 받는데, 이 인자 값은 브로커로부터 데이터를 가져올때 컨슈머 버퍼에 데이터를 기다리기 위한 타임아웃 간격이다.
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));

            // for loop 를 통해 poll() 메서드가 반환한 ConsumerRecord 데이터들을 순차적으로 처리한다.
            for (ConsumerRecord<String, String> record : records) {
                log.info("{}", record);
            }
        }
    }
}
