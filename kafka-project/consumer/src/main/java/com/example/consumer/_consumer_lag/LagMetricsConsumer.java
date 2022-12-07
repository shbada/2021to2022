package com.example.consumer._consumer_lag;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Map;
import java.util.Properties;

@Slf4j
public class LagMetricsConsumer {
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

        for (Map.Entry<MetricName, ? extends Metric> entry : consumer.metrics().entrySet()) {
            if ("record-lag-max".equals(entry.getKey().name())
                || "record-lags".equals(entry.getKey().name())
                || "record-lag-avg".equals(entry.getKey().name())) {
                Metric metric = entry.getValue();
                log.info(entry.getKey().name(), metric.metricValue());
            }
        }
    }
}
