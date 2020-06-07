package com.sane.so2o.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sane.so2o.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sane.so2o.entity.ud.ArticleUD;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 母玉山
 * @since 2020-06-06
 */
public interface ArticleDao extends BaseMapper<Article> {
    ArticleUD querytById(Integer id);
    List<ArticleUD> queryByCondiction(@Param("article") Article article, Page page);
}
