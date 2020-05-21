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
 * 存储用户注册时 填写的电子邮件，用于job调用发送验证码
 * </p>
 *
 * @author 母玉山
 * @since 2020-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Verifycodeprocess extends Model<Verifycodeprocess> {

    private static final long serialVersionUID=1L;

    /**
     * 主键，自增
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 电邮地址
     */
    private String email;

    /**
     * 是否已经发送验证码，0:未发送，1：已发送
     */
    private Boolean deal_flag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
