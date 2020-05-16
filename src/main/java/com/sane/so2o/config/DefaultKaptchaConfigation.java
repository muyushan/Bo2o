package com.sane.so2o.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class DefaultKaptchaConfigation {
    @Bean
    public DefaultKaptcha createKaptcha(){
        DefaultKaptcha defaultKaptcha=new DefaultKaptcha();
        Properties properties=new Properties();
        properties.setProperty("kaptcha.image.width","132");
        properties.setProperty("kaptcha.image.height","38");
        properties.setProperty("kaptcha.textproducer.char.string","123456789AKWUEHPMRXaqdfgjh");
        properties.setProperty("kaptcha.textproducer.char.length","4");
        properties.setProperty("kaptcha.border","no");
        properties.setProperty("kaptcha.border.color","105,179,90");
        properties.setProperty("kaptcha.border.thickness","1");
        properties.setProperty("kaptcha.textproducer.font.color","black");
        properties.setProperty("kaptcha.textproducer.font.size","24");
        properties.setProperty("kaptcha.textproducer.font.names","楷体");
        properties.setProperty("kaptcha.noise.color","black");
        properties.setProperty("kaptcha.textproducer.char.space","8");
        properties.setProperty("kaptcha.obscurificator.impl","com.google.code.kaptcha.impl.ShadowGimpy");
        Config config=new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
