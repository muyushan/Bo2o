package com.sane.so2o.web.article;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sane.so2o.BaseTest;
import com.sane.so2o.entity.Article;
import com.sane.so2o.entity.ud.Pager;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class ArticleControllerTest extends BaseTest {

    @Autowired
    private ArticleController articleController;
    @Test
    public void queryArticles() {
        Pager pager=new Pager();
        pager.setPageNum(1);
        pager.setPageSize(10);
        Page<Article> articlePage=articleController.queryArticles(new Article(),pager);
        Assert.assertEquals(articlePage.getSize(),10);
    }
}