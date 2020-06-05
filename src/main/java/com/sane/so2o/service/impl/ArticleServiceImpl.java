package com.sane.so2o.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sane.so2o.entity.Article;
import com.sane.so2o.dao.ArticleDao;
import com.sane.so2o.entity.ud.ArticleUD;
import com.sane.so2o.entity.ud.Pager;
import com.sane.so2o.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Wrapper;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 母玉山
 * @since 2020-05-13
 */
@Slf4j
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, Article> implements IArticleService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateClick(int articleId) {
        LambdaUpdateWrapper<Article> articleLambdaUpdateWrapper= Wrappers.lambdaUpdate(new Article());
        articleLambdaUpdateWrapper.eq(Article::getArticle_id,articleId);
        articleLambdaUpdateWrapper.setSql("article_click=IFNULL(article_click,0)+1");
        update(articleLambdaUpdateWrapper);
    }

    @Override
    public Page<ArticleUD> query(Article article, Pager pager) {
        Page<ArticleUD> page = new Page<>(pager.getPageNum(), pager.getPageSize());// 当前页，总条数 构造 page 对象
        page=page.setRecords(this.baseMapper.queryByCondiction(article,page));
        return page;
    }
    @Cacheable(value = "article",key ="#root.args[0]")
    @Override
    public ArticleUD queryArticleById(Integer articleId) {
       return this.baseMapper.querytById(articleId);
    }
}
