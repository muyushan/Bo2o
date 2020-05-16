package com.sane.so2o.service;

import com.sane.so2o.entity.HeadLine;

import java.util.List;

public interface HeadLineService {

    public List<HeadLine> getHeadLineList(HeadLine headLineCondition);
}
