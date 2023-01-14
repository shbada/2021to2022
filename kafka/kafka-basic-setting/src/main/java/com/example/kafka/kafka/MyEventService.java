package com.example.kafka.kafka;

import com.example.kafka.domain.MyEvent;
import com.example.kafka.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class MyEventService {
    private final KafkaProducer kafkaProducer;

    public void sendMsg() {
        Map<String, Object> data = new HashMap<>();
        data.put("width", 1020);
        data.put("height", 7090);

        kafkaProducer.send(KafkaProducer.TOPIC_NAME, new MyEvent(data));
    }

}
