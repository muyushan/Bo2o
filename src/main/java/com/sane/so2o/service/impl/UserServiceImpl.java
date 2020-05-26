package com.sane.so2o.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.code.kaptcha.Producer;
import com.sane.so2o.dao.VerifycodeprocessDao;
import com.sane.so2o.entity.User;
import com.sane.so2o.dao.UserDao;
import com.sane.so2o.entity.Verifycodeprocess;
import com.sane.so2o.entity.ud.RetValue;
import com.sane.so2o.entity.ud.UserUD;
import com.sane.so2o.enums.RetCodeEnum;
import com.sane.so2o.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sane.so2o.service.MailSenderService;
import com.sane.so2o.service.RedisService;
import com.sane.so2o.util.RegexUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 母玉山
 * @since 2020-05-16
 */
//@Configuration
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private Producer producer;
    @Autowired
    private MailSenderService mailSenderService;
    @Value("${so2o.regist.verifycode.expire}")
    private int expire;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Boolean userNameExist(String userName) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        return  super.baseMapper.selectCount(queryWrapper)>0;
    }

    @Override
    public RetValue sendEmailVerifyCode(String userName, String emailAddress) {

        RetValue retValue=new RetValue();
        try{
            Verifycodeprocess verifycodeprocess=new Verifycodeprocess();
            verifycodeprocess.setEmail(emailAddress);
            if(!verifycodeprocess.insert()){
                retValue.setCode(RetCodeEnum.FAIL.getCode());
                retValue.setMessage("验证码发送失败,请重试");
            }else{
                retValue.setMessage("系统将尝试向您的邮箱发送验证码，请在"+expire+"分钟内完成注册");
                retValue.setCode(RetCodeEnum.SUCCESS.getCode());
            }
        }catch (Exception ex){
            retValue.setCode(RetCodeEnum.FAIL.getCode());
            retValue.setMessage("验证码发送失败,请重试");
        }

        return retValue;
    }

    @CacheEvict(value = "user",key ="#p0.user_email")
    @Override
    public RetValue registUserInfo(UserUD userUD) {
        String verifyCode="";
        if(redisService.hasKey(userUD.getKey())){
           verifyCode=redisService.get(userUD.getKey()).toString();
        }

        RetValue retValue=new RetValue();
        if(StringUtils.isEmpty(userUD.getUser_name())){
            retValue.setCode(RetCodeEnum.FAIL.getCode());
            retValue.setMessage("用户名不能为空");
        }else if(userUD.getUser_name().length()>32){
            retValue.setCode(RetCodeEnum.FAIL.getCode());
            retValue.setMessage("用户名最大长度32");
        }else if(StringUtils.isEmpty(userUD.getUser_pwd())){
            retValue.setCode(RetCodeEnum.FAIL.getCode());
            retValue.setMessage("密码不允许为空");
        }else if(userUD.getUser_pwd().length()>32){
            retValue.setMessage("密码长度过长");
            retValue.setCode(RetCodeEnum.FAIL.getCode());
        }else if(StringUtils.isEmpty(userUD.getUser_email())){
            retValue.setMessage("邮箱地址不允许为空");
            retValue.setCode(RetCodeEnum.FAIL.getCode());
        }else if(!RegexUtil.isEmail(userUD.getUser_email())){
            retValue.setMessage("邮箱格式不正确");
            retValue.setCode(RetCodeEnum.FAIL.getCode());
        }else if(!StringUtils.equals(userUD.getUser_pwd(),userUD.getUser_pwd_verify())){
            retValue.setMessage("确认密码不正确，请重新输入");
            retValue.setCode(RetCodeEnum.FAIL.getCode());
        }else if(StringUtils.isEmpty(verifyCode)){
            retValue.setMessage("注册验证码已过期，请重新申请");
            retValue.setCode(RetCodeEnum.FAIL.getCode());
        }else if(StringUtils.isEmpty(userUD.getEmail_verify_code())){
            retValue.setMessage("注册验证码不正确，请确认输入的内容");
            retValue.setCode(RetCodeEnum.FAIL.getCode());
        }else{
           userUD.setUser_pwd(passwordEncoder.encode(userUD.getUser_pwd()));
            if(userUD.insert()){
                retValue.setCode(RetCodeEnum.SUCCESS.getCode());
                retValue.setMessage("注册成功");
            }else{
                retValue.setCode(RetCodeEnum.FAIL.getCode());
                retValue.setMessage("注册失败，请重试");
            }
        }
        return retValue;
    }

}
