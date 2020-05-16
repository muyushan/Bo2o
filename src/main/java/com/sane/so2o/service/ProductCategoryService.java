package com.sane.so2o.service;

import com.sane.so2o.dto.ProductCategoryExecution;
import com.sane.so2o.entity.ProductCategory;
import com.sane.so2o.exceptions.ProductCategoryOperationException;

import java.util.List;

public interface ProductCategoryService {
    public List<ProductCategory> getProductCategoryList(Long shopId);
    public ProductCategoryExecution batchInserProductCategory(List<ProductCategory> productCategoryList)throws ProductCategoryOperationException;
    public ProductCategoryExecution deleteProductCategory(long categoryId,Long shopId)throws ProductCategoryOperationException;
}
