package com.example.consumer._workerThread;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConsumerWithMultiWorkerThread {
    private final static Logger logger = LoggerFactory.getLogger(ConsumerWithMultiWorkerThread.class);
    private final static String TOPIC_NAME = "test";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";
    private final static String GROUP_ID = "test-group";

    public static void main(String[] args) {
        Properties configs = new Properties();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configs.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        configs.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 10000);


        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(configs);
        consumer.subscribe(Arrays.asList(TOPIC_NAME));

        /*
            thread 실행을 위해 ExecutorService 를 사용한다.
            ExecutorService는 다양한 스레드 풀을 제공하는데, 여기서는 레코드를 출력하고 출력이 완료되면 스레드를 종료하도록
            CachedThreadPool 을 사용했다.
         */
        ExecutorService executorService = Executors.newCachedThreadPool(); //필요한 만큼 스레드 풀을 늘려서 스레드를 실행하는 방식

        while (true) {
            // poll() 메서드를 통해 리턴받은 레코드들을 처리하는 스레드를 레코드마다 개별 실행한다.
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(10));

            for (ConsumerRecord<String, String> record : records) {
                ConsumerWorker worker = new ConsumerWorker(record.value());

                // 스레드는 execute() 로 실행되고 레코드별 로그가 출력된다.
                executorService.execute(worker);
            }
        }
    }
}
