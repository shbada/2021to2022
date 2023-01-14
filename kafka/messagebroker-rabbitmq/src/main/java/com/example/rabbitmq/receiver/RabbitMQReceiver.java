package com.example.rabbitmq.receiver;

import com.example.rabbitmq.model.MyTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMQReceiver {
    /**
     * 메세지 send 이벤트 발생시, 받는다.
     * @param task
     */
    @RabbitListener(id = "photo.sample", queues = "photo.sample")
    public void handle(MyTask task) {
        log.info("mydata handle:: {}", task.toString());
    }
}
