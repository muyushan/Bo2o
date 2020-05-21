package com.sane.so2o.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.code.kaptcha.Producer;
import com.sane.so2o.entity.Verifycodeprocess;
import com.sane.so2o.dao.VerifycodeprocessDao;
import com.sane.so2o.service.IVerifycodeprocessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sane.so2o.service.MailSenderService;
import com.sane.so2o.service.RedisService;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 存储用户注册时 填写的电子邮件，用于job调用发送验证码 服务实现类
 * </p>
 *
 * @author 母玉山
 * @since 2020-05-21
 */
@Service
public class VerifycodeprocessServiceImpl extends ServiceImpl<VerifycodeprocessDao, Verifycodeprocess> implements IVerifycodeprocessService {
    @Value("${so2o.regist.verifycode.expire}")
    private int expire;
    @Autowired
    private MailSenderService mailSenderService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private Producer producer;
    @Override
    @Transactional
    public void processSendRegistCode() {
        QueryWrapper<Verifycodeprocess> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("deal_flag",0);
        List<Verifycodeprocess> unprocessList=list(queryWrapper);
        if(CollectionUtils.isEmpty(unprocessList)){
            return;
        }else{
            unprocessList.forEach(verifycodeprocess -> {
                String verifyCode=producer.createText();
                Map<String,Object> contextMap=new HashMap<>();
                contextMap.put("verifyCode",verifyCode);
                contextMap.put("expire",expire);
                try {
                    mailSenderService.sendTemplateMail(verifycodeprocess.getEmail(),"SaneBlog注册验证码",contextMap,"/email/regist_verify_code");
                    boolean result=redisService.set("user::"+verifycodeprocess.getEmail(),verifyCode, TimeUnit.MINUTES.toSeconds(expire));
                    if(result){
                        verifycodeprocess.setDeal_flag(true);
                        verifycodeprocess.updateById();
                    }
                } catch (MessagingException e) {
                    e.printStackTrace();
                } catch (javax.mail.MessagingException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
