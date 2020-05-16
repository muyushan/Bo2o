package com.sane.so2o.exceptions;

public class ProductCategoryOperationException extends RuntimeException {

    public ProductCategoryOperationException(String errorMsg){
        super(errorMsg);
    }
}
