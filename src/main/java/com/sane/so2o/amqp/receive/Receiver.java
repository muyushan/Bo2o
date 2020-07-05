package com.sane.so2o.amqp.receive;

import com.sane.so2o.config.RabbitMQConfig;
import com.sane.so2o.entity.Article;
import lombok.extern.java.Log;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Log
@Component
@RabbitListener(queues = {"queue_log_file"})
public class Receiver {
    @RabbitHandler
    public void  processMessage(String message) throws InterruptedException {
        log.info(String.format("Receiver1==rabbitmessage:%s",message));
    }
}
