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
@RabbitListener(queues = {"FIRST"})
public class Receiver2 {
    @RabbitHandler
    public void  processMessage(Long seconds, Channel channel, Message message) throws Exception {
       try{
           if (seconds>10L) throw new Exception("处理超时");
           log.info(String.format("Receiver2==rabbitmessage:%s",seconds.toString()));
           TimeUnit.SECONDS.sleep(seconds);
           channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
       }catch (Exception ex){
           log.info("发生了异常："+message.getBody().toString());
           channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
       }

    }
}
