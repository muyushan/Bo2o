package com.sane.so2o.web.article;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sane.so2o.entity.Article;
import com.sane.so2o.entity.ud.Pager;
import com.sane.so2o.entity.ud.RetValue;
import com.sane.so2o.enums.RetCodeEnum;
import com.sane.so2o.service.IArticleService;
import com.sane.so2o.util.ContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private IArticleService articleService;
    @RequestMapping(value = "new",method = RequestMethod.GET)
    public String newArticle(){
        return "article/createnewarticle";
    }
    @ResponseBody
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public RetValue<String> saveArticle(Article article){
        article.setArticle_time(new Date());
        article.setArticle_ip(ContextUtil.getUserName());
        boolean result=articleService.saveOrUpdate(article);
        RetValue<String> retValue=new RetValue<>();
        retValue.setCode(RetCodeEnum.SUCCESS.getCode());
        retValue.setMessage(RetCodeEnum.SUCCESS.getMessage());
        return  retValue;
    }

    @ResponseBody
    @RequestMapping(value = "list")
    public Page<Article> queryArticles(Article article, Pager pager){
        Page<Article> page=new Page<>();
        page.setCurrent(pager.getPageNum());
        page.setSize(pager.getPageSize());
        QueryWrapper<Article> queryWrapper=new QueryWrapper<>();
        Page<Article> articlePage=articleService.page(page,queryWrapper);
        return  articlePage;
    }
    @RequestMapping("/{articleId}")
    public ModelAndView queryArticleById(@PathVariable Integer articleId){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("article/showarticle");
       Article article= articleService.getById(articleId);
       modelAndView.addObject("article",article);
//        Assert.notNull(article,"文章不存在了");
        return modelAndView;
    }
}
