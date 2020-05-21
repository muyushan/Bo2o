package com.sane.so2o.service;

import com.sane.so2o.entity.Verifycodeprocess;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 存储用户注册时 填写的电子邮件，用于job调用发送验证码 服务类
 * </p>
 *
 * @author 母玉山
 * @since 2020-05-21
 */
public interface IVerifycodeprocessService extends IService<Verifycodeprocess> {

    void processSendRegistCode();

}
