package com.sane.so2o.dao;

import com.sane.so2o.BaseTest;
import com.sane.so2o.entity.HeadLine;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HeadLineDaoTest extends BaseTest {
    @Autowired
    private HeadLineDao headLineDao;

    @Test
    public void testHeadLineQuery(){
      List<HeadLine> headLineList= headLineDao.queryHeadLine(new HeadLine());
        Assert.assertEquals(4,headLineList.size());
    }
}
