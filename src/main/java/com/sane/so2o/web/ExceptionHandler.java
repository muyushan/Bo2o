package com.sane.so2o.web;

import com.sane.so2o.entity.ud.RetValue;
import com.sane.so2o.enums.RetCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler
//    @ResponseBody
    public ModelAndView handlerException(Exception e){
        RetValue<String> retValue=new RetValue<>();
        retValue.setCode(RetCodeEnum.FAIL.getCode());
        retValue.setMessage(e.getMessage());
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        modelAndView.addObject("message",retValue.getMessage());
        return modelAndView;
    }
}
