package com.sane.so2o.config;

import com.sane.so2o.entity.ud.UserUD;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Configuration
public class KeyGeneratorConfigation {
    @Bean
    public KeyGenerator registVerifyKeyGenerator(){
        return new RegistVerifyKeyGenerator();
    }
    class RegistVerifyKeyGenerator implements KeyGenerator {
        @Override
        public Object generate(Object o, Method method, Object... objects) {
            UserUD userUD=(UserUD)o;
            return "regist:"+userUD.getUser_name()+":"+userUD.getUser_email();
        }
    }
}
