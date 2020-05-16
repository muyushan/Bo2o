package com.sane.so2o.service.impl;

import com.sane.so2o.dao.HeadLineDao;
import com.sane.so2o.entity.HeadLine;
import com.sane.so2o.service.HeadLineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeadLineServiceImpl implements HeadLineService {
    Logger logger= LoggerFactory.getLogger(HeadLineServiceImpl.class);
    @Autowired
    private HeadLineDao headLineDao;
    @Cacheable(value = "headline",key ="#root.methodName")
        @Override
        public List<HeadLine> getHeadLineList(HeadLine headLineCondition) {
            return headLineDao.queryHeadLine(headLineCondition);

    }
}
