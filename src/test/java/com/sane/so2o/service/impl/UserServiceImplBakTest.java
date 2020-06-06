package com.sane.so2o.service.impl;

import com.sane.so2o.BaseTest;
import com.sane.so2o.entity.User;
import com.sane.so2o.entity.ud.RetValue;
import com.sane.so2o.entity.ud.UserUD;
import com.sane.so2o.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public  class UserServiceImplBakTest extends BaseTest {

    @Autowired
    private UserService userService;
    @Test
   public void userNameExist() {
       Boolean exist=userService.userNameExist("muys2");
        Assert.assertFalse(exist);
    }


    @Test
    public void testRegistUserInfo() {
        UserUD userUD=new UserUD();
        userUD.setUserPwd("566788");
        userUD.setUserEmail("muyushanm@126.com");
        userUD.setUserName("muys2");
        userUD.setEmailVerifyCode("COGN02");
        userUD.setUserPwdVerify("566788");
       RetValue retValue=userService.registUserInfo(userUD);
       Assert.assertEquals("200",retValue.getCode());
       log.info(retValue.getMessage());
    }

}