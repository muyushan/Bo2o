package com.sane.so2o.service;

import com.sane.so2o.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sane.so2o.entity.ud.RetValue;
import com.sane.so2o.entity.ud.UserUD;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 母玉山
 * @since 2020-05-16
 */
public interface IUserService extends IService<User> {

    public Boolean userNameExist(String userName);
    public RetValue sendEmailVerifyCode(String userName, String emailAddress);
    public RetValue registUserInfo(UserUD userUD);
}
