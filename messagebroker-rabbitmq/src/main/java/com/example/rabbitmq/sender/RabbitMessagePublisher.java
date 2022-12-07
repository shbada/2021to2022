package com.example.rabbitmq.sender;

import com.example.rabbitmq.model.MyTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMessagePublisher {
    private final RabbitTemplate rabbitTemplate;

    /**
     * RqbbitMq 에 메세지 전송
     * @param routingKey
     * @param myTask
     */
    public void publish(String routingKey, MyTask myTask) {
        try {
            /* routingKey: queue name 이랑 동일하게 일반적으로 사용 */
            rabbitTemplate.convertAndSend("thecodinglive", "photo.sample", myTask);
        } catch (Exception e) {
            log.error("error", e);
        }
    }
}
