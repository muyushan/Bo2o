package com.sane.so2o.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sane.so2o.entity.User;
import com.sane.so2o.entity.ud.SecurityUser;
import com.sane.so2o.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service(value = "userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrappper=new QueryWrapper<User>();
        queryWrappper.eq("user_name",userName);
        User user = userService.getOne(queryWrappper);
        SecurityUser securityUser = null;
        if (user!=null) {
             securityUser=new SecurityUser(user);
        }else{
            throw  new UsernameNotFoundException(userName);
        }
        return securityUser;
    }

}
