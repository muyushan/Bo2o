package com.sane.so2o.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sane.so2o.entity.User;
import com.sane.so2o.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.Collection;

@Service(value = "userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private IUserService userService;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrappper=new QueryWrapper<User>();
        queryWrappper.eq("user_name",userName);
        User user = userService.getOne(queryWrappper);
        UserDetails userDetails = null;
        if (user!=null) {
            userDetails=org.springframework.security.core.userdetails.User.builder().password(user.getUser_pwd()).username(userName).roles("USER","ADMIN").build();
        }else{
            throw  new UsernameNotFoundException(userName);
        }
        return userDetails;
    }

//    @Bean
//    PasswordEncoder passwordEncoder(){
//        PasswordEncoder passwordEncoder =new BCryptPasswordEncoder(16);
//        return passwordEncoder;
//    }
}
