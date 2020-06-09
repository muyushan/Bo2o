package com.sane.so2o.web.article;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sane.so2o.entity.Article;
import com.sane.so2o.entity.User;
import com.sane.so2o.entity.ud.ArticleUD;
import com.sane.so2o.entity.ud.Pager;
import com.sane.so2o.entity.ud.RetValue;
import com.sane.so2o.enums.RetCodeEnum;
import com.sane.so2o.service.ArticleService;
import com.sane.so2o.util.ContextUtil;
import com.sane.so2o.util.HttpServletRequstUtil;
import com.sane.so2o.util.ImgUtil;
import com.sane.so2o.util.PathUtil;
import lombok.extern.java.Log;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

@Controller
@Log
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @RequestMapping(value = "new",method = RequestMethod.GET)
    public String newArticle(){
        AopContext.currentProxy();
        return "article/createnewarticle";
    }
    @ResponseBody
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public RetValue<String> saveArticle(Article article, HttpServletRequest request){
        article.setArticleTime(new Date());
        article.setArticleIp(HttpServletRequstUtil.getRealIp(request));
        User user=ContextUtil.getUserDetail();
        article.setUserId(user.getUserId());
        boolean result=articleService.saveOrUpdate(article);
        RetValue<String> retValue=new RetValue<>();
        retValue.setCode(RetCodeEnum.SUCCESS.getCode());
        retValue.setMessage(RetCodeEnum.SUCCESS.getMessage());
        retValue.setData(HttpServletRequstUtil.getBaseUrl(request)+"/article/a_"+article.getArticleId());
        return  retValue;
    }
@RequestMapping("upload")
@ResponseBody
    public RetValue<String> uploadImg(@RequestParam("editormd-image-file") MultipartFile multipartFile) throws IOException {
        RetValue<String> retValue=new RetValue<>();
        Assert.notNull(multipartFile);
        String destPath=PathUtil.getUserImagePath(ContextUtil.getUserDetail().getUserId());
        String path= ImgUtil.generateThumbnail(multipartFile,destPath);
        retValue.setMessage("成功");
        retValue.setUrl("image"+path);
        retValue.setSuccess(1);
        return  retValue;
    }

    @ResponseBody
    @RequestMapping(value = "list")
    public Page<ArticleUD> queryArticles(Article article, Pager pager){
        Page<Article> page=new Page<>();
        page.setCurrent(pager.getPageNum());
        page.setSize(pager.getPageSize());
        QueryWrapper<Article> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("article_time","article_click");
        Page<ArticleUD> articlePage=articleService.query(article,pager);
        return  articlePage;
    }
    @RequestMapping("/a_{articleId}")
    public ModelAndView queryArticleById(@PathVariable Integer articleId){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("article/showarticle");
        ArticleUD article= articleService.queryArticleById(articleId);
        articleService.updateClick(articleId);
       modelAndView.addObject("article",article);
        return modelAndView;
    }
}
