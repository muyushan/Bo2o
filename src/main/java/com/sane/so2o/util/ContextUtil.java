package com.sane.so2o.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

public class ContextUtil {
    public static String getUserName(){
        SecurityContextHolder.getContext().getAuthentication().getDetails();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Assert.notNull(principal,"当前登录信息为空，请重新登录");
        if(principal instanceof UserDetails){
           UserDetails userDetails= ((UserDetails) principal);
            return userDetails.getUsername();

        }
        return "";

    }
}
