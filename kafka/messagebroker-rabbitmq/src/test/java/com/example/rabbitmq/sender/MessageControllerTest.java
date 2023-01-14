package com.example.rabbitmq.sender;

import com.example.rabbitmq.config.CustomRabbitQueue;
import com.example.rabbitmq.model.MyTask;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class MessageControllerTest {
    @Autowired
    private RabbitMessagePublisher publisher;

    @Test
    public void sendMsg() {
        /**
         * 첫 실행시 에러 발생
         * no exchange "thecodinglive" : thecodinglive exchange 를 생성해줘야한다.
         * http://localhost:15672/#/exchange > Add a new exchange 등록
         */
        String msg = "you guys do something!!!";
        publisher.publish(CustomRabbitQueue.SAMPLE_TASK.getQueueName(), new MyTask(msg));
    }
}