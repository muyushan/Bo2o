package com.sane.so2o.junit;

import com.rabbitmq.client.*;
import org.junit.Test;
import org.springframework.amqp.core.Exchange;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RabbitMQTest {
    @Test
    public void  test1() throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setPassword("guest");
        connectionFactory.setUsername("guest");
        connectionFactory.setPort(5672);
        connectionFactory.setHost("localhost");
        connectionFactory.setVirtualHost("/blog");
        //创建连接
       Connection connection= connectionFactory.newConnection();
       //创建Channel
       Channel channel=connection.createChannel();
       //声明交换机
       channel.exchangeDeclare("exchange1", BuiltinExchangeType.DIRECT,true,false,null);
       //声明队列
      String queue=channel.queueDeclare().getQueue();
      channel.exchangeDelete("");
      channel.exchangeDelete("",true);
      channel.exchangeDeleteNoWait("",true);
      //把队列绑定到交换机上
      channel.queueBind(queue,"exchange1","item.insert");
        TimeUnit.SECONDS.sleep(50);
    }
}
