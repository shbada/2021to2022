package com.example.consumer._multiConsumerThread;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerWorker implements Runnable {

    private final static Logger logger = LoggerFactory.getLogger(ConsumerWorker.class);

    private final Properties prop;
    private final String topic;
    private final String threadName;
    private KafkaConsumer<String, String> consumer;

    /*
        생성자 변수에 컨슈머 옵션 prop, 토픽이름, 스레드 구별할 스레드 번호를 받는다.
     */
    ConsumerWorker(Properties prop, String topic, int number) {
        this.prop = prop;
        this.topic = topic;
        this.threadName = "consumer-thread-" + number;
    }

    @Override
    public void run() {
        /*
        KafkaConsumer 클래스는 스레드 세이프 하지 않다.
        그래서 스레드별로 KafkaConsumer 인스턴스를 별개로 만들어서 운영해야만한다.
        만약 KafkaConsumer 인스턴스를 여러 스레드에서 실행하면 ConcurrentModificationException 예외가 발생한다.
         */
        consumer = new KafkaConsumer<>(prop);
        consumer.subscribe(Arrays.asList(topic)); // 토픽을 명시적으로 구독하기 시작한다.
        try {
            while (true) {
                // 리턴받은 레코드들을 처리한다.
                // 이때 스레드 이름을 함께 로그에 출력함으로써 어떤 스레드가 어떤 케로드를 처리했는지 확인할 수 있다.
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                for (ConsumerRecord<String, String> record : records) {
                    logger.info("{}", record);
                }
                consumer.commitSync();
            }
        } catch (WakeupException e) {
            System.out.println(threadName + " trigger WakeupException");
        } finally {
            consumer.commitSync();
            consumer.close();
        }
    }

    public void shutdown() {
        consumer.wakeup();
    }
}
