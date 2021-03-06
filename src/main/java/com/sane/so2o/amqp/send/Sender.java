package com.sane.so2o.amqp.send;

import com.rabbitmq.client.ConnectionFactory;
import com.sane.so2o.config.RabbitMQConfig;
import com.sane.so2o.entity.Article;
import lombok.extern.java.Log;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log
public class Sender {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void  sendMessage(String message,String key){
        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_EXCHANGE,key,message);
    }
}
