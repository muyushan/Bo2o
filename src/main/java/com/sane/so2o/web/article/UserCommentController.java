package com.sane.so2o.web.article;


import com.sane.so2o.entity.Article;
import com.sane.so2o.entity.UserComment;
import com.sane.so2o.entity.ud.RetValue;
import com.sane.so2o.enums.RetCodeEnum;
import com.sane.so2o.service.IArticleService;
import com.sane.so2o.service.UserCommentService;
import com.sane.so2o.util.ContextUtil;
import com.sane.so2o.util.HttpServletRequstUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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
    private IArticleService articleService;
    @RequestMapping("save")
    public RetValue<String> saveComment(UserComment userComment, HttpServletRequest request){
        RetValue<String> retValue=new RetValue<>();
        userComment.setCommitUserId(ContextUtil.getUserDetail().getUserId());
        userComment.setCommitIp(HttpServletRequstUtil.getRealIp(request));
        userComment.setCommitTime(new Date());
        Article article=articleService.getById(userComment.getCommitId());
        Assert.notNull(article,"没有查询到要评论的文章，无法提交评论");
        userComment.setTypeId(article.getType_id());
        Assert.hasText(userComment.getCommitContent(),"评论内容不能为空或空白字符");
        userCommentService.save(userComment);
        retValue.setCode(RetCodeEnum.SUCCESS.getCode());
        return  retValue;


    }
}

