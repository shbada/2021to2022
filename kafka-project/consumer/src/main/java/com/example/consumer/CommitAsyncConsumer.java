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
public class CommitAsyncConsumer {
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

            // for loop 를 통해 poll() 메서드가 반환한 ConsumerRecord 데이터들을 순차적으로 처리한다.
            for (ConsumerRecord<String, String> record : records) {
                log.info("{}", record);

                /*
                동기 오프셋 커밋을 사용할 경우 커밋 응답을 기다리는 동안 데이터 처리가 일시적으로 중단된다.
                더 많은 데이터를 처리하기 위해서 비동기 오프셋 커밋을 사용할 수 있다.
                비동기 오프셋 커밋은 commitAsync() 메서드를 호출하여 사용할 수 있다.
                 */
//                consumer.commitAsync(); // 비동기 오프셋 커밋 호출

                /*
                비동기 오프셋 커밋도 동기 커밋과 마찬가지로 poll() 메서드로 리턴된 가장 마지막 레코드를
                기준으로 오프셋을 커밋한다.
                다만, 동기 오프셋 커밋과 다른 점은 커밋이 완료될때까지 응답을 기다리지 않는다는 것이다.
                이 때문에, 동기 오프셋 커밋을 사용할때보다 동일 시간당 데이터 처리량이 많다.
                비동기 오프셋 커밋을 사용할 경우 비동기로 커밋 응답을 받기 때문에 callback 함수를 파라미터로 받아서 결과를 얻을 수 있다.
                 */
                consumer.commitAsync(new OffsetCommitCallback() {
                    /*
                    OffsetCommitCallback : commitAsync()의 응답을 받을 수 있도록 도와주느 콜백 인터페이스
                    비동기로 받은 커밋 응답은 onComplete() 를 통해 확인한다.
                    정상적으로 커밋되었다면 exception 객체는 null이다.
                    커밋 완료된 오프셋 정보가 Map<TopicPartition, OffsetAndMetadata>에 포함되어있다.
                    만약 커밋이 실패했다면 Exception 변수에 에러값이 포함되어 있으므로 어떠한 이유로 커밋이 실패했는지 확인할 수 있다.
                     */
                    @Override
                    public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                        if (exception != null) {
                            System.err.println("Commit failed");
                        } else {
                            System.out.print("Commit succeeded");
                        }

                        if (exception != null) {
                            log.error("Commit failed for offsets {}", offsets, exception);
                        }
                    }
                });
            }
        }
    }
}
