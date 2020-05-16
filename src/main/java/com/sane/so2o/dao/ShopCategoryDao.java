package com.sane.so2o.dao;

import com.sane.so2o.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopCategoryDao {
    List<ShopCategory>queryShopCategory(@Param("category") ShopCategory category);
}

