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
 * @since 2020-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Article extends Model<Article> {

    private static final long serialVersionUID=1L;

    /**
     * 日志自增ID号
     */
      @TableId(value = "article_id", type = IdType.AUTO)
    private Integer article_id;

    /**
     * 文章名称
     */
    private String article_name;

    /**
     * 发布时间
     */
    private Date article_time;

    /**
     * 发布IP
     */
    private String article_ip;

    /**
     * 查看人数
     */
    private Integer article_click;

    /**
     * 所属分类
     */
    private Integer sort_article_id;

    /**
     * 所属用户ID
     */
    private Integer user_id;

    /**
     * 栏目ID
     */
    private Integer type_id;

    /**
     * 文章的模式:0为私有，1为公开，2为仅好友查看
     */
    private Integer article_type;

    /**
     * 文章内容
     */
    private String article_content;

    /**
     * 是否置顶:0为否，1为是
     */
    private Integer article_up;

    /**
     * 是否博主推荐:0为否，1为是
     */
    private Integer article_support;


    @Override
    protected Serializable pkVal() {
        return this.article_id;
    }

}
