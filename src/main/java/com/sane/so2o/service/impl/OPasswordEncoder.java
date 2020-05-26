package com.sane.so2o.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class OPasswordEncoder extends BCryptPasswordEncoder {

    private Pattern BCRYPT_PATTERN = Pattern
            .compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if(!BCRYPT_PATTERN.matcher(encodedPassword).matches()){
            if(rawPassword.equals(encodedPassword)){
                System.out.println(super.encode(rawPassword));
                return true;
            }
            return  false;
        }

        return super.matches(rawPassword, encodedPassword);
    }
}
