package com.example.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class CommitSyncConsumer {
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

        /* 명시적 오프셋 커밋 */
        configs.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

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

            // 파라미터 추가
            Map<TopicPartition, OffsetAndMetadata> currentOffset = new HashMap<>();

            // for loop 를 통해 poll() 메서드가 반환한 ConsumerRecord 데이터들을 순차적으로 처리한다.
            for (ConsumerRecord<String, String> record : records) {
                log.info("{}", record);

                // 파라미터 추가
                // 개별 레코드 단위로 매번 오프셋을 커밋하고 싶다면 Map<TopicPartition, OffsetAndMetadata> 을 파라미터로 넣는다.
                // TopicPartition : 토픽과 파티션 정보, OffsetAndMetadata : 오프셋 정보
                /*
                   처리를 완료한 레코드의 정보를 토대로 Map<TopicPartition, OffsetAndMetadata> 인스턴스에 키/값을 넣는다.
                   record.offset() + 1 : 현재 처리한 오프셋에 1을 더한 값을 커밋해야한다.
                   -> 이후에 컨슈머가 poll()을 수행할때 마지막으로 커밋한 오프셋부터 레코드를 리턴하기 때문이다.
                   이렇게 파라미터로 설정해서 호출하면 해당 특정 토픽, 파티션의 오프셋이 매번 커밋된다.
                 */
                currentOffset.put(new TopicPartition(record.topic(), record.partition()),
                        new OffsetAndMetadata(record.offset() + 1, null));
                consumer.commitSync(currentOffset);
            }

            /*
            commitSync()는 poll() 메서드로 받은 가장 마지막 레코드의 오프셋을 기준으로 커밋한다.
            동기 오프셋 커밋을 사용할 경우 poll() 메서드로 받은 모든 레코드의 처리가 끝난 이후
            commitSync() 메서드를 호출해야한다.
            동기 커밋의 경우 브로커로 커밋을 요청한 이후에 커밋이 완료되기까지 기다린다.
            브로커로부터 컨슈머 오프셋 커밋이 완료되었음을 받기까지 컨슈머는 데이터를 다 처리하지 않고 기다리기 때문에
            자동 커밋이나 비동기 오프셋 커밋보다 동일 시간당 데이터 처리량이 적다는 특징이 있다.
             */
//            consumer.commitSync(); // 파라미터가 없을 경우 poll()로 반환된 마지막 레코드의 오픗세 기준으로 커밋
        }
    }
}
