package com.sane.so2o.web;

import com.sane.so2o.amqp.send.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
    @Autowired
    private Sender sender;
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public  String  hello(String message,String key){
        sender.sendMessage(message, key);
        return "hello springboot";
    }
}
