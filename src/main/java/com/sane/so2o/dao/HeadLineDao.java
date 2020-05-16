package com.sane.so2o.dao;

import com.sane.so2o.entity.HeadLine;

import java.util.List;

public interface HeadLineDao {

    public List<HeadLine> queryHeadLine(HeadLine headLineCondiction);
}
