package com.sane.so2o;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;


@SpringBootApplication(scanBasePackages ={"com.sane.so2o"})
public class So2oApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(So2oApplication.class, args);
    }

//    @PostConstruct
//    public void setDefaultTimeZone(){
//        TimeZone.getDefault();
//        System.out.println(new Date());
//        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
//    }
}

