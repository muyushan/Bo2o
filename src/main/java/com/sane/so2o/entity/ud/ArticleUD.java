package com.sane.so2o.entity.ud;

import com.sane.so2o.entity.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ArticleUD extends Article {
    private String userName;
    private Integer commentCount;
}
