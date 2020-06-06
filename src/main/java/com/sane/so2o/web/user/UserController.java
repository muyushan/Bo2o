package com.sane.so2o.web.user;


import com.sane.so2o.entity.User;
import com.sane.so2o.entity.ud.RetValue;
import com.sane.so2o.entity.ud.UserUD;
import com.sane.so2o.enums.RetCodeEnum;
import com.sane.so2o.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 母玉山
 * @since 2020-05-16
 */
@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @ResponseBody
    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public RetValue<User> registPost(UserUD user) {
        log.info(user.toString());
        RetValue<User> retValue=new RetValue();
        retValue=userService.registUserInfo(user);
        return retValue;
    }
    @RequestMapping("/validate/{userName}")
    @ResponseBody
    public RetValue validateUserName(@PathVariable String userName){
        RetValue retValue=new RetValue();
        if(userService.userNameExist(userName)){
            retValue.setCode(RetCodeEnum.FAIL.getCode());
            retValue.setMessage("该用户名已经被注册使用，请更换。");
        }else{
            retValue.setCode(RetCodeEnum.SUCCESS.getCode());
        }
        return  retValue;
    }
    @ResponseBody
    @RequestMapping(value = "/sendregistcode",method = RequestMethod.POST)
    public RetValue registPost(String userName,String email) {
        RetValue retValue=userService.sendEmailVerifyCode(userName,email);
        return retValue;
    }
}

