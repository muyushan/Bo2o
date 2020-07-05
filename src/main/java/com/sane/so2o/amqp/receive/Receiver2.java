package com.sane.so2o.amqp.receive;

import com.rabbitmq.client.Channel;
import com.sane.so2o.entity.Article;
import lombok.extern.java.Log;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Log
@Component
@RabbitListener(queues = {"queue_log_console"})
public class Receiver2 {
    @RabbitHandler
    public void  processMessage(String message) throws Exception {
        log.info(message);
    }
}
