package com.example.rabbitmq.config;

import com.example.rabbitmq.common.CustomJacksonConverter;
import com.example.rabbitmq.model.MyTask;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/** docker 사용 시 check list
 *   - vhost / 루트 패스
 *   - 계정 admin 권한 ( tag )
 */

@Slf4j
@Configuration /* 빈 등록 */
@EnableRabbit /* rabbitMQ 사용을 위해 선언 */
public class RabbitMQConfig {
    /* ID, PASSWORD 를 넣을 수 있는 값 */
    @Resource
    private RabbitProperties rabbitProperties;

    /* queue 들을 매핑할 key 값 설정 */
    public static final String RABBIT_EXECHAGNGE_NAME = "thecodinglive";
    private static final Integer CONSUMER_COUNT = 5;

    /**
     * 객체를 주고받을때 객체로 사용할 값을 설정
     * @return
     */
    @Bean
    public DefaultClassMapper classMapper() {
        DefaultClassMapper classMapper = new DefaultClassMapper();

        /* MyTask 클래스를 RabbitMq 를 통해서 주고받을것이라는 명시 */
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("myTask", MyTask.class);

        classMapper.setIdClassMapping(idClassMapping);
        return classMapper;
    }

    /**
     * 데이터를 Json 으로 변환하여 주고받으므로 설정
     * @return
     */
    @Bean
    public MessageConverter rabbitMessageConverter() {
        Jackson2JsonMessageConverter jsonConverter = new Jackson2JsonMessageConverter(CustomJacksonConverter.getInstance());
        jsonConverter.setClassMapper(classMapper());
        return jsonConverter;
    }

    /**
     * RabbitMQ 실제 연동하는 코드
     * @return
     */
    @Bean
    public ConnectionFactory rabbitConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        log.info("userName:: {}", rabbitProperties.getUsername());

        /* User 정보 설정 */
        connectionFactory.setUsername(rabbitProperties.getUsername());
        connectionFactory.setPassword(rabbitProperties.getPassword());
        connectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CHANNEL);

        return connectionFactory;
    }

    /**
     * Admin 설정 관련 부분
     * RabbitMQ에 admin 권한이 있는 계정으로 접속한 후에
     * exchange와 queue를 등록하고 매핑해준다.
     * @param rabbitConnectionFactory
     * @return
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory rabbitConnectionFactory) {
        final RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitConnectionFactory);

        // exchange 등록
        rabbitExchange(rabbitAdmin);

        // queue 자동 등록
        for (CustomRabbitQueue customRabbitQueue : CustomRabbitQueue.values()) {
            rabbitAdmin.declareQueue(new Queue(customRabbitQueue.getQueueName(), true));

            // rabbitExchange
            rabbitAdmin.declareBinding(BindingBuilder.bind(new Queue(customRabbitQueue.getQueueName(), true))
                    .to(rabbitExchange(rabbitAdmin)).with(customRabbitQueue.getQueueName()));
        }

        rabbitAdmin.afterPropertiesSet();
        return rabbitAdmin;
    }

    /**
     * exchange Topic 설정
     * @param rabbitAdmin
     * @return
     */
    @Bean
    TopicExchange rabbitExchange(RabbitAdmin rabbitAdmin) {
        TopicExchange topicExchange = new TopicExchange(RABBIT_EXECHAGNGE_NAME);
        topicExchange.setAdminsThatShouldDeclare(rabbitAdmin);
        return topicExchange;
    }

    /**
     * RabbitTemplate
     * @param rabbitConnectionFactory
     * @param rabbitMessageConverter
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory rabbitConnectionFactory,
                                         MessageConverter rabbitMessageConverter) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(rabbitConnectionFactory);
        rabbitTemplate.setMessageConverter(rabbitMessageConverter);
        rabbitTemplate.setExchange(RABBIT_EXECHAGNGE_NAME);

        rabbitTemplate.setConfirmCallback(((correlationData, ack, cause) -> {
            if(ack) {
                log.info("success");
            }else{
                log.error("error {}", cause);
            }
        }));

        return rabbitTemplate;
    }
}
