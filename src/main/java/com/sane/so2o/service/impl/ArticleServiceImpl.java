package com.sane.so2o.service.impl;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.DefaultCredentials;
import com.aliyun.oss.common.comm.RequestMessage;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.internal.OSSHeaders;
import com.aliyun.oss.internal.OSSRequestSigner;
import com.aliyun.oss.internal.SignV2Utils;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sane.so2o.dao.ArticleDao;
import com.sane.so2o.entity.Article;
import com.sane.so2o.entity.ud.ArticleUD;
import com.sane.so2o.entity.ud.Pager;
import com.sane.so2o.entity.ud.RetValue;
import com.sane.so2o.service.ArticleService;
import lombok.extern.java.Log;
import okhttp3.*;
import com.aliyun.oss.common.auth.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 母玉山
 * @since 2020-06-06
 */
@Log
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, Article> implements ArticleService {


    @Value("${aliyun.oss.bucket}")
    private String aliyun_oss_bucket;
    @Value("${aliyun.oss.url}")
    private String aliyun_oss_url;
    @Autowired
    private OSS ossClient;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateClick(int articleId) {
        LambdaUpdateWrapper<Article> articleLambdaUpdateWrapper = Wrappers.lambdaUpdate(new Article());
        articleLambdaUpdateWrapper.eq(Article::getArticleId, articleId);
        articleLambdaUpdateWrapper.setSql("article_click=IFNULL(article_click,0)+1");
        update(articleLambdaUpdateWrapper);
    }

    @Override
    public Page<ArticleUD> query(Article article, Pager pager) {
        Page<ArticleUD> page = new Page<>(pager.getPageNum(), pager.getPageSize());// 当前页，总条数 构造 page 对象
        page = page.setRecords(this.baseMapper.queryByCondiction(article, page));
        return page;
    }

    @Cacheable(value = "article", key = "#root.args[0]")
    @Override
    public ArticleUD queryArticleById(Integer articleId) {
        ArticleUD articleUD = this.baseMapper.querytById(articleId);
        Assert.notNull(articleUD, "文章不存在了!");
        return articleUD;
    }

    @CacheEvict(value = "article" ,key ="#article.articleId",condition ="#article.articleId!=null")
    @Override
    public boolean saveOrUpdateMD(Article article) {
       return saveOrUpdate(article);
    }

    @Override
    public RetValue<String> uploadImage(MultipartFile file) throws IOException {
        RetValue<String> retValue = new RetValue<>();
        try{
            PutObjectResult result = ossClient.putObject(aliyun_oss_bucket, "blog/"+file.getOriginalFilename(), file.getInputStream());
            if (!StringUtils.isEmpty(result.getETag())) {
                retValue.setSuccess(1);
                retValue.setUrl(aliyun_oss_url+"/blog/"+file.getOriginalFilename());
            } else {
                retValue.setSuccess(0);
                retValue.setMessage("上传失败");
            }
        }
        catch (Exception ex){
            retValue.setSuccess(0);
            retValue.setMessage("上传文件失败:"+ex.getMessage());
        }
        return retValue;
    }
}
