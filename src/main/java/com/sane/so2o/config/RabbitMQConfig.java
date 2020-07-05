package com.sane.so2o.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static String LOG_QUEUE_FILE="queue_log_file";
    public static String LOG_EXCHANGE="log_exchange";
    public static String LOG_QUEUE_CONSOLE="queue_log_console";
    @Bean(value = "queue_console")
    public Queue queue_console(){
        return QueueBuilder.durable(LOG_QUEUE_CONSOLE).build();
    }
    @Bean(value = "queue_file")
    public Queue queue_file(){
        return QueueBuilder.durable(LOG_QUEUE_FILE).build();
    }
    @Bean(value = "logExchange")
    public Exchange logExchange(){
        return ExchangeBuilder.topicExchange(LOG_EXCHANGE).durable(true).build();
    }

    @Bean
    public Binding bind_queue_console(@Qualifier("logExchange") Exchange exchange, @Qualifier("queue_console") Queue q1){

        return  BindingBuilder.bind(q1).to(exchange).with("item.#").noargs();
    }
    @Bean
    public Binding bind_queue_file(@Qualifier("logExchange") Exchange exchange,  @Qualifier("queue_file")Queue q2){

        return  BindingBuilder.bind(q2).to(exchange).with("item.#").noargs();
    }
}
