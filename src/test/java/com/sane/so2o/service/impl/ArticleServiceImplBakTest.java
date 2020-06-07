package com.sane.so2o.service.impl;

import com.sane.so2o.BaseTest;
import com.sane.so2o.entity.Article;
import com.sane.so2o.entity.ud.ArticleUD;
import com.sane.so2o.entity.ud.Pager;
import com.sane.so2o.service.ArticleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class ArticleServiceImplBakTest extends BaseTest {

    @Autowired
    private ArticleService articleService;
    @Test
    public void query() {
        Pager pager=new Pager();
        pager.setPageNum(1);
        pager.setPageSize(10);
        articleService.query(new Article(),pager);
    }
}