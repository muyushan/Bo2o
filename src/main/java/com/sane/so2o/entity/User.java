package com.sane.so2o.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 母玉山
 * @since 2020-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User extends Model<User> {

    private static final long serialVersionUID=1L;

    /**
     * 用户ID
     */
      @TableId(value = "user_id", type = IdType.AUTO)
    private Integer user_id;

    /**
     * 用户组ID
     */
    private Integer group_id;

    /**
     * 用户名
     */
    private String user_name;

    /**
     * 用户密码
     */
    private String user_pwd;

    /**
     * 用户手机号码
     */
    private Integer user_phone;

    /**
     * 用户性别
     */
    private String user_sex;

    /**
     * 用户QQ号码
     */
    private Integer user_qq;

    /**
     * 用户EMAIL地址
     */
    private String user_email;

    /**
     * 用户地址
     */
    private String user_address;

    /**
     * 用户积分
     */
    private Integer user_mark;

    /**
     * 用户等级
     */
    private Integer user_rank_id;

    /**
     * 用户上一次登录IP地址
     */
    private String user_last_login_ip;

    /**
     * 用户生日
     */
    private Integer user_birthday;

    /**
     * 自我描述
     */
    private String user_description;

    /**
     * 用户头像存储路径
     */
    private String user_image_url;

    /**
     * 毕业学校
     */
    private String user_school;

    /**
     * 用户注册时间
     */
    private Integer user_register_time;

    /**
     * 用户注册时IP地址
     */
    private String user_register_ip;

    /**
     * 用户上次更新博客时间
     */
    private Integer user_last_update_time;

    /**
     * 用户微博
     */
    private String user_weibo;

    /**
     * 用户血型
     */
    private String user_blood_type;

    /**
     * 用户语录
     */
    private String user_says;

    /**
     * 是否锁定，0为不锁定，1为锁定
     */
    private Integer user_lock;

    /**
     * 是否冻结，0为不冻结，1为冻结
     */
    private Integer user_freeze;

    /**
     * 拥有权限
     */
    private String user_power;


    @Override
    protected Serializable pkVal() {
        return this.user_id;
    }

}
