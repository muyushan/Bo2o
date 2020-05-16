package com.sane.so2o;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.TimeZone;


@SpringBootApplication(scanBasePackages ={"com.sane.so2o"})
@MapperScan("com.sane.so2o.dao")
@EnableTransactionManagement
//@EnableAspectJAutoProxy
public class So2oApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(So2oApplication.class, args);
    }


}
