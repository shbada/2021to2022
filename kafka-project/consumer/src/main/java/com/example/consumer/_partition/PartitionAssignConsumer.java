package com.example.consumer._partition;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.*;

@Slf4j
public class PartitionAssignConsumer {
    private final static String TOPIC_NAME = "test";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";
    private final static int PARTITION_NUMBER = 0;
    private final static String GROUP_ID = "test-group";

    public static void main(String[] args) {
        Properties configs = new Properties();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);

        configs.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);

        // 프로듀서에서 직렬화하여 전송한 데이터를 역직렬화한다.
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        /* 명시적 오프셋 커밋 */
        configs.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(configs);

//        consumer.subscribe(Arrays.asList(TOPIC_NAME));

        /**
         * assign 메서드를 사용하여 컨슈머가 어떤 토픽, 파티션을 할당할지 명시적으로 선언할 수 있다.
         * TopicPartitions 인스턴스를 지닌 자바 컬렉션 타입을 파라미터로 받는다.
         * TopicPartition 클래스는 카프카 라이브러리 내/외부에서 사용되는 토픽, 파티션의 정보를 담는 객체로 사용된다.
         * test의 0번 파티션을 할당하여 레코드를 가져오는 구문이다.
         * subscribe() 메서드를 사용할 때와 다르게 직접 컨슈머가 특정 토픽, 특정 파티션에 할당되므로
         * 리밸런싱하는 과정이 없다.
         */
        consumer.assign(Collections.singleton(new TopicPartition(TOPIC_NAME, PARTITION_NUMBER)));

        while (true) {

            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));

            // 파라미터 추가
            Map<TopicPartition, OffsetAndMetadata> currentOffset = new HashMap<>();

            // for loop 를 통해 poll() 메서드가 반환한 ConsumerRecord 데이터들을 순차적으로 처리한다.
            for (ConsumerRecord<String, String> record : records) {
                log.info("{}", record);

                currentOffset.put(new TopicPartition(record.topic(), record.partition()),
                        new OffsetAndMetadata(record.offset() + 1, null));
                consumer.commitSync();
            }
        }
    }
}
