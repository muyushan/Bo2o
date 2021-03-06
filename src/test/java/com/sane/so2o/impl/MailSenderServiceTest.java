package com.sane.so2o.impl;

import com.google.code.kaptcha.Producer;
import com.sane.so2o.BaseTest;
import com.sane.so2o.service.MailSenderService;
import com.sane.so2o.service.UserService;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MailSenderServiceTest extends BaseTest {
    @Autowired
    private MailSenderService mailSenderService;
    @Autowired
    private Producer producer;
    @Autowired
    private UserService userService;

    @Test
    public void testSendTextEmail(){
        mailSenderService.sendSimpleTextMail("muyushanm@126.com","测试邮件时间","测试内容");
    }
    @Test
    public void testSendTemplateEmail() throws MessagingException, javax.mail.MessagingException {
        Map<String,Object>map=new HashMap<>();
        map.put("verifyCode",producer.createText());
        mailSenderService.sendTemplateMail("917763727@qq.com","测试邮件时间",map,"/email/regist_verify_code");
    }

    @Test
    public void testSendValidateEmail() throws MessagingException, javax.mail.MessagingException {
        userService.sendEmailVerifyCode("muys2","muyushanm@126.com");
    }
}
