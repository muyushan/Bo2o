package com.sane.so2o.entity.ud;

import lombok.Data;

@Data
public class RetValue<T> {

    private String code;
    private Integer success;
    private String message;
    private String url;
    private T data;

}
