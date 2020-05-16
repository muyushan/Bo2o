package com.sane.so2o.dao;

import com.sane.so2o.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 母玉山
 * @since 2020-05-13
 */
public interface ArticleDao extends BaseMapper<Article> {
    Article querytById(Integer id);
}
