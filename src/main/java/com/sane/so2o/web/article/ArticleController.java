package com.sane.so2o.web.article;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sane.so2o.entity.Article;
import com.sane.so2o.entity.User;
import com.sane.so2o.entity.ud.ArticleUD;
import com.sane.so2o.entity.ud.Pager;
import com.sane.so2o.entity.ud.RetValue;
import com.sane.so2o.enums.RetCodeEnum;
import com.sane.so2o.service.IArticleService;
import com.sane.so2o.util.ContextUtil;
import com.sane.so2o.util.HttpServletRequstUtil;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private IArticleService articleService;
    @RequestMapping(value = "new",method = RequestMethod.GET)
    public String newArticle(){
        AopContext.currentProxy();
        return "article/createnewarticle";
    }
    @ResponseBody
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public RetValue<String> saveArticle(Article article, HttpServletRequest request){
        article.setArticle_time(new Date());
        article.setArticle_ip(HttpServletRequstUtil.getRealIp(request));
        User user=ContextUtil.getUserDetail();
        article.setUser_id(user.getUser_id());
        boolean result=articleService.saveOrUpdate(article);
        RetValue<String> retValue=new RetValue<>();
        retValue.setCode(RetCodeEnum.SUCCESS.getCode());
        retValue.setMessage(RetCodeEnum.SUCCESS.getMessage());
        retValue.setData(HttpServletRequstUtil.getBaseUrl(request)+"/article/a_"+article.getArticle_id());
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
        Assert.notNull(article,"文章不存在了!");
        articleService.updateClick(articleId);
       modelAndView.addObject("article",article);
        return modelAndView;
    }
}
