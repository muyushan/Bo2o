package com.sane.so2o.util;

import com.sane.so2o.entity.User;
import com.sane.so2o.entity.ud.SecurityUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

public class ContextUtil {
    public static String getUserName(){
        SecurityContextHolder.getContext().getAuthentication().getDetails();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Assert.notNull(principal,"当前登录信息为空，请重新登录");
        if(principal instanceof SecurityUser){
           SecurityUser userDetails= ((SecurityUser) principal);
            return userDetails.getUsername();
        }
        return "";
    }
    public static User getUserDetail(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Assert.notNull(principal,"当前登录信息为空，请重新登录");
        if(principal instanceof SecurityUser){
            SecurityUser securityUser= ((SecurityUser) principal);
            return securityUser.getDetail();
        }
        return null;
    }
}
