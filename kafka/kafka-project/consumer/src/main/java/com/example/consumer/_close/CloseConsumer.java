package com.example.consumer._close;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class CloseConsumer {
    private final static String TOPIC_NAME = "test";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";
    private final static String GROUP_ID = "test-group";
    private static KafkaConsumer<String, String> consumer;

    public static void main(String[] args) {
        // 셧다운훅(kill -TERM 프로세스번호)가 발생하면, wakeup() 메서드가 호출되어 컨슈머를 안전하게 종료한다.
        Runtime.getRuntime().addShutdownHook(new ShutdownThread());

        Properties configs = new Properties();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);

        configs.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);

        // 프로듀서에서 직렬화하여 전송한 데이터를 역직렬화한다.
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        /* 명시적 오프셋 커밋 */
        configs.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        consumer = new KafkaConsumer<String, String>(configs);

        // 컨슈머에게 토픽을 할당하기 위해 subscribe() 를 사용한다.
        // 이 메서드는 Collection 타입의 String 타입을 받는데, 1개 이상의 토픽 이름을 받을 수 있다.
        consumer.subscribe(Arrays.asList(TOPIC_NAME));

        try {
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
        } catch (WakeupException we) {
            log.warn("Wakeup consumer");
        } finally {
            /*
            컨슈머 애플리케이션은 안전하게 종료되어야 한다.
            정상적으로 종료되지 않은 컨슈머는 세션 타임아웃이 발생할때까지 컨슈머 그룹에 남게된다.
            이로 인해 실제로는 종료되었지만 더는 동작하지 않는 컨슈머가 존재하기 때문에,
            파티션의 데이터는 소모되지 못하고 컨슈머 랙이 늘어나게된다.
            컨슈머 랙이 늘어나면 데이터 처리 지연이 발생한다.
            컨슈머를 안전하게 종료하기 위해 wakeup() 메서드를 지원한다.

            wakeup() 메서드를 실행하여 KafkaConsumer 인스턴스를 안전하게 종료할 수 있다.
            wakeup()이 실행된 이후 poll() 메서드가 호출되면 WakeupException 예외가 발생한다.
            WakeupException 예외를 받은 뒤에는 데이터 처리를 위해 사용한 자원을 해제하면 된다.
            그리고 마지막에는 close() 를 호출하여 카프카 클러스터에 컨슈머가 안전하게 종료되었음을 명시적으로 알려주면,
            종료가 완료되었다고 볼 수 있다.

            close()
            해당 컨슈머는 더는 동작하지 않는다는 것을 명시적으로 알려준다.
            컨슈머 그룹에서 이탈되고 나머지 컨슈머들이 파티션을 할당받게된다.
             */
            consumer.close();
        }
    }

    static class ShutdownThread extends Thread {
        public void run() {
            log.info("Shutdown hook");
            consumer.wakeup();
        }
    }
}
