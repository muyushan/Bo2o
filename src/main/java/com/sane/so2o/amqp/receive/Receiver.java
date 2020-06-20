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
@RabbitListener(queues = {"FIRST"})
public class Receiver {
    @RabbitHandler
    public void  processMessage(Long seconds) throws InterruptedException {
        log.info(String.format("Receiver1==rabbitmessage:%s",seconds.toString()));
        TimeUnit.SECONDS.sleep(seconds);
    }
}
