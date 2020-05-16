package com.sane.so2o.service.impl;

import com.sane.so2o.dao.AreaDao;
import com.sane.so2o.entity.Area;
import com.sane.so2o.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {
    private Logger logger= LoggerFactory.getLogger(AreaServiceImpl.class);
    @Autowired
    private AreaDao areaDao;
    @Override
    @Cacheable(value = {"area1","area2"},key = "#root.targetClass.name")
    public List<Area> queryAreaList() {
        logger.info("日志记录===============");
        return areaDao.queryDao();
    }
}
