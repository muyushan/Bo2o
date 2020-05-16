package com.sane.so2o.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sane.so2o.BaseTest;
import com.sane.so2o.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;
@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArticleDaoTest extends BaseTest {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private DataSource dataSource;

    @Test
    public void insertA() {
        log.info(TimeZone.getDefault().getDisplayName());
        Article article = new Article();
        article.setArticle_name("test");
        article.setArticle_time(Calendar.getInstance().getTime());
        log.info(LocalDateTime.now().toString());
        article.setArticle_click(1);
        article.setArticle_content("dfdsfdsfdsfdsfdsfds");
        article.setArticle_ip("1270.0.1");
        article.setArticle_support(1);
        article.setArticle_type(1);
        article.setArticle_up(1);
        article.setSort_article_id(1);
        article.setType_id(1);
        article.setUser_id(1);
        int count = articleDao.insert(article);
        assertEquals(1, count);
    }

    @Test
    public void updateB() {
        Article article = new Article();
        article.setArticle_name("test2");
        article.setArticle_id(1);
        article.setArticle_time(Calendar.getInstance().getTime());
        articleDao.updateById(article);
    }

    @Test
    public void queryC() {
        Article article =articleDao.selectById(1);
        Assert.assertNotNull(article);


    }
}
