package com.sane.so2o.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
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
 * @since 2020-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserComment extends Model<UserComment> {

    private static final long serialVersionUID=1L;

    /**
     * 评论自增ID号
     */
      @TableId(value = "c_id", type = IdType.AUTO)
    private Integer cId;

    /**
     * 收到评论的用户ID
     */
    private Integer userId;

    /**
     * 评论栏目ID
     */
    private Integer typeId;

    /**
     * 评论内容的ID
     */
    private Integer commitId;

    /**
     * 评论内容
     */
    private String commitContent;

    /**
     * 评论者ID
     */
    private Integer commitUserId;

    /**
     * 评论时间
     */
    private Date commitTime;

    /**
     * 评论时的IP地址
     */
    private String commitIp;


    @Override
    protected Serializable pkVal() {
        return this.cId;
    }

}
