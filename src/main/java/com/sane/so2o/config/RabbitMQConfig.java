package com.sane.so2o.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static String QUEUE_FIRST="FIRST";
    @Bean
    public Queue firstQueue(){

        return new Queue(QUEUE_FIRST);
    }
}
