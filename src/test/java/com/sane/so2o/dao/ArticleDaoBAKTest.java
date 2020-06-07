package com.sane.so2o.dao;

import com.sane.so2o.BaseTest;
import com.sane.so2o.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;
@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArticleDaoBAKTest extends BaseTest {
    @Autowired
    private ArticleDao articleDao;

    @Test
    public void insertA() {
        log.info(TimeZone.getDefault().getDisplayName());
        Article article = new Article();
        article.setArticleName("test");
        article.setArticleTime(Calendar.getInstance().getTime());
        log.info(LocalDateTime.now().toString());
        article.setArticleClick(1);
        article.setArticleContent("dfdsfdsfdsfdsfdsfds");
        article.setArticleIp("1270.0.1");
        article.setArticleSupport(1);
        article.setArticleType(1);
        article.setArticleUp(1);
        article.setSortArticleId(1);
        article.setTypeId(1);
        article.setUserId(1);
        int count = articleDao.insert(article);
        assertEquals(1, count);
    }

    @Test
    public void updateB() {
        Article article = new Article();
        article.setArticleName("test2");
        article.setArticleId(1);
        article.setArticleTime(Calendar.getInstance().getTime());
        articleDao.updateById(article);
    }

    @Test
    public void queryC() {
        Article article = articleDao.selectById(1);
        Assert.assertNotNull(article);


    }
}
