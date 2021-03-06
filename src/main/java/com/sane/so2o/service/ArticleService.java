package com.sane.so2o.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sane.so2o.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sane.so2o.entity.ud.ArticleUD;
import com.sane.so2o.entity.ud.Pager;
import com.sane.so2o.entity.ud.RetValue;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 母玉山
 * @since 2020-06-06
 */
public interface ArticleService extends IService<Article> {

    public void updateClick(int articleId);
    public Page<ArticleUD> query(Article article, Pager pager);
    public ArticleUD queryArticleById(Integer articleId);
    public boolean saveOrUpdateMD(Article article);
    public RetValue<String> uploadImage(MultipartFile file) throws IOException;
}
