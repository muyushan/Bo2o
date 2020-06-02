package com.sane.so2o.web;

import com.sane.so2o.entity.ud.RetValue;
import com.sane.so2o.enums.RetCodeEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseBody
    public RetValue<String> handlerException(Exception e){
        RetValue<String> retValue=new RetValue<>();
        retValue.setCode(RetCodeEnum.FAIL.getCode());
        retValue.setMessage(e.getMessage());
        return retValue;
    }
}
