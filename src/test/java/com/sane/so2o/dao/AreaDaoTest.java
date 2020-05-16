package com.sane.so2o.dao;

import com.sane.so2o.BaseTest;
import com.sane.so2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AreaDaoTest extends BaseTest {

    @Autowired
    private AreaDao areaDao;
    @Test
    public void queryDao() {
        List<Area> areaList=areaDao.queryDao();
        assertEquals(4,areaList.size());
    }
}