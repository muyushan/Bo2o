package com.sane.so2o.impl;

import com.sane.so2o.BaseTest;
import com.sane.so2o.entity.ProductCategory;
import com.sane.so2o.service.AreaService;
import com.sane.so2o.service.ProductCategoryService;
import com.sane.so2o.service.impl.HeadLineServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AreaServiceImplTest extends BaseTest {

    Logger logger= LoggerFactory.getLogger(AreaServiceImplTest.class);

    @Autowired
    private AreaService areaService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Test
    public void queryAreaList() {
        logger.info("dddddd");
//        Assert.assertEquals(4,areaService.queryAreaList().size());
        List<ProductCategory> productCategories=new ArrayList<>();
        for(int i=10;i<15;i++){
            ProductCategory productCategory=new ProductCategory();
            productCategory.setShopId(16L);
            productCategory.setProductCategoryName("c"+i);
            productCategories.add(productCategory);
        }


        productCategoryService.batchInserProductCategory(productCategories);
    }
}