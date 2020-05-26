package com.sane.so2o.web.article;

import com.sane.so2o.entity.Article;
import com.sane.so2o.entity.ud.RetValue;
import com.sane.so2o.enums.RetCodeEnum;
import com.sane.so2o.service.IArticleService;
import com.sane.so2o.util.ContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
