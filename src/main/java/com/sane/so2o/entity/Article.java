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
 * @since 2020-06-06
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
    private Integer articleId;

    /**
     * 文章名称
     */
    private String articleName;

    /**
     * 发布时间
     */
    private Date articleTime;

    /**
     * 发布IP
     */
    private String articleIp;

    /**
     * 查看人数
     */
    private Integer articleClick;

    /**
     * 所属分类
     */
    private Integer sortArticleId;

    /**
     * 所属用户ID
     */
    private Integer userId;

    /**
     * 栏目ID
     */
    private Integer typeId;

    /**
     * 文章的模式:0为私有，1为公开，2为仅好友查看
     */
    private Integer articleType;

    /**
     * 文章内容
     */
    private String articleContent;

    /**
     * 是否置顶:0为否，1为是
     */
    private Integer articleUp;

    /**
     * 是否博主推荐:0为否，1为是
     */
    private Integer articleSupport;


    @Override
    protected Serializable pkVal() {
        return this.articleId;
    }

}
