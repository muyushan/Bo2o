package com.sane.so2o.exceptions;

public class ProductOperationException extends RuntimeException {

    public ProductOperationException(String errorMsg){
        super(errorMsg);
    }
}
