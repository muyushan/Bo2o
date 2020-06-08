package com.sane.so2o.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.DefaultTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Qualifier("userDetailService")
    @Autowired
    private UserDetailsService userDetailsService;
    @Value("${spring.security.remberme.salt}")
    private String saltkey;

    @Override
    public void configure(WebSecurity web) throws Exception {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        //去掉黑名单
        firewall.setAllowSemicolon(true);
        //加入自定义的防火墙
        web.httpFirewall(firewall);
        super.configure(web);
    }

    /**
     * 关于SpringSecurity 请求缓存 的一点记录：
     * 请求地址以/favicon.ico结尾
     * header中的content-type值为application/json
     * header中的X-Requested-With值为XMLHttpRequest
     * 以上三种请求是不进行缓存的
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/js/**","/css/**","/images/**","/layui/**").permitAll();
        http.authorizeRequests().antMatchers("/mdeditor/**","/signup","/redirectindex","/index","/user/validate/*","/user/regist","/user/sendregistcode","/article/list","/article/a_{articleId}").permitAll();
        http.authorizeRequests().antMatchers("/signup","/redirectindex","/index","/user/validate/*","/user/regist","/user/sendregistcode","/article/list","/article/a_{articleId}").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.rememberMe().key(saltkey).userDetailsService(userDetailsService);
        http.formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/index");
        http.logout().logoutSuccessUrl("/index").invalidateHttpSession(true).clearAuthentication(true);
        http.csrf().disable();
    }

    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver){
        SpringTemplateEngine templateEngine=new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.addDialect(new SpringSecurityDialect());
        return templateEngine;
    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
    }

}
