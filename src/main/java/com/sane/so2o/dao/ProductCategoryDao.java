package com.sane.so2o.dao;

import com.sane.so2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryDao {

    public List<ProductCategory> queryProductCategoryList(Long shopId);
    public int batchInsertProductCategory(@Param("productCategoryList") List<ProductCategory> productCategoryList);
    public int deleteProductCategoryById(Long categoryId);
}
