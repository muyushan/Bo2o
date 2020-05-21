package com.sane.so2o.service.impl;

import com.sane.so2o.BaseTest;
import com.sane.so2o.entity.User;
import com.sane.so2o.entity.ud.RetValue;
import com.sane.so2o.entity.ud.UserUD;
import com.sane.so2o.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public  class UserServiceImplTest extends BaseTest {

    @Autowired
    private IUserService userService;
    @Test
   public void userNameExist() {
       Boolean exist=userService.userNameExist("muys2");
        Assert.assertFalse(exist);
    }


    @Test
    public void testRegistUserInfo() {
        UserUD userUD=new UserUD();
        userUD.setUser_pwd("566788");
        userUD.setUser_email("muyushanm@126.com");
        userUD.setUser_name("muys2");
        userUD.setEmail_verify_code("COGN02");
        userUD.setUser_pwd_verify("566788");
       RetValue retValue=userService.registUserInfo(userUD);
       Assert.assertEquals("200",retValue.getCode());
       log.info(retValue.getMessage());
    }

}