package com.sane.so2o.service;

import com.sane.so2o.dto.ShopExecution;
import com.sane.so2o.entity.Shop;
import com.sane.so2o.exceptions.ShopOperationException;
import org.springframework.web.multipart.MultipartFile;

public interface ShopService {

    ShopExecution addShop(Shop shop, MultipartFile shopImg) throws ShopOperationException;
    Shop queryShopById(Long shopId);
    ShopExecution modifyShop(Shop shop, MultipartFile shopImg)throws ShopOperationException;
    ShopExecution queryShopList(Shop shop, int pageIndex, int pageSize);

}
