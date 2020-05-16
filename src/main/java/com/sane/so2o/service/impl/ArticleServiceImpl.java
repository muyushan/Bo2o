package com.sane.so2o.service.impl;

import com.sane.so2o.entity.Article;
import com.sane.so2o.dao.ArticleDao;
import com.sane.so2o.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private ArticleDao articleDao;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void test() {
        Article article=articleDao.querytById(1);
        article.setArticle_content("dddgdfdfd666");
        article.setArticle_time(new Date());
        articleDao.updateById(article);
//        log.info(String.valueOf(3/0));
    }
}
