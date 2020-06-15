package com.sane.so2o.web;

import com.sane.so2o.entity.ud.RetValue;
import com.sane.so2o.enums.RetCodeEnum;
import com.sane.so2o.exceptions.UploadException;
import com.sane.so2o.util.ContextUtil;
import com.sane.so2o.util.HttpServletRequstUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.spring5.context.SpringContextUtils;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler()
    public ModelAndView handlerException(Exception e, HttpServletRequest req){
        RetValue<String> retValue=new RetValue<>();
        retValue.setCode(RetCodeEnum.FAIL.getCode());
        retValue.setMessage(e.getMessage());
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        modelAndView.addObject("message",retValue.getMessage());
        return modelAndView;
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(value = UploadException.class)
    @ResponseBody
    public RetValue<String> handlerUploadException(UploadException exception){
        RetValue<String> retValue=new RetValue<>();
        retValue.setSuccess(0);
        retValue.setMessage(exception.getMessage());
        return retValue;
    }
}
