package com.sane.so2o.web.article;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sane.so2o.entity.Article;
import com.sane.so2o.entity.UserComment;
import com.sane.so2o.entity.ud.RetValue;
import com.sane.so2o.enums.RetCodeEnum;
import com.sane.so2o.service.ArticleService;
import com.sane.so2o.service.UserCommentService;
import com.sane.so2o.util.ContextUtil;
import com.sane.so2o.util.HttpServletRequstUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 母玉山
 * @since 2020-06-05
 */
@Controller
@RequestMapping("/userComment")
public class UserCommentController {

    @Autowired
    private UserCommentService userCommentService;
    @Autowired
    private ArticleService articleService;
    @ResponseBody
    @RequestMapping("save")
    public RetValue<String> saveComment(UserComment userComment, HttpServletRequest request){
        RetValue<String> retValue=new RetValue<>();
        userComment.setCommitUserId(ContextUtil.getUserDetail().getUserId());
        userComment.setCommitIp(HttpServletRequstUtil.getRealIp(request));
        userComment.setCommitTime(new Date());
        LambdaQueryWrapper<Article> articleQueryWrapper= Wrappers.lambdaQuery(Article.class).eq(Article::getArticleId,userComment.getCommitId());
        int count=articleService.count(articleQueryWrapper);
        Assert.isTrue(count>0,"没有查询到要评论的文章，无法提交评论");
        Article article=articleService.getById(userComment.getCommitId());
        userComment.setUserId(article.getUserId());
        userComment.setTypeId(article.getTypeId());
        Assert.hasText(userComment.getCommitContent(),"评论内容不能为空或空白字符");
        userCommentService.save(userComment);
        retValue.setCode(RetCodeEnum.SUCCESS.getCode());
        return  retValue;
    }
    @RequestMapping("listByArticleId")
    @ResponseBody
    public List<UserComment> queryArticleComment(int articleId){
        LambdaQueryWrapper<UserComment> commentQueryWrapper=Wrappers.lambdaQuery(UserComment.class).eq(UserComment::getCommitId,articleId).orderByDesc(UserComment::getCId);
        List<UserComment> userCommentList=userCommentService.list(commentQueryWrapper);
        return userCommentList;

    }
}

