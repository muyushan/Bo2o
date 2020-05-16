package com.sane.so2o.web.article;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("article")
public class ArticleController {

    @RequestMapping("new")
    public String newArticle(){
        return "article/createnewarticle";
    }
}
