package com.example.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

@Slf4j
public class ProducerCallback implements Callback {

    /**
     * 레코드의 비동기 결과를 받기위해 사용한다.
     * 위 코드에서는 만약 브로커 적재에 이슈가 생겼을 경우 Exception 에 어떤 에러가 발생하였는지 담겨서
     * 메서드가 실행된다. 에러가 발생하지 않았을 경우에는 RecordMetadata를 통해 해당 레코드가 적재된 토픽 이름, 파티션 번호, 오프셋을 알 수 있다.
     */
    @Override
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        if (exception != null) {
            log.info(exception.getMessage());
        } else {
            log.info(metadata.toString());
        }
    }
}
