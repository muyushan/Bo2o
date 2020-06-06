package com.sane.so2o.web;

import com.sane.so2o.entity.ud.RetValue;
import com.sane.so2o.service.AreaService;
import com.sane.so2o.service.IArticleService;
import com.sane.so2o.util.HttpServletRequstUtil;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log
@Controller
public class FirstController {
    @Autowired
    private AreaService areaService;
    @Autowired
    private IArticleService articleService;
    @RequestMapping(value = "/login",headers ={"X-Requested-With=XMLHttpRequest"} )
    public void loginPage(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("redirectUrl", HttpServletRequstUtil.getBaseUrl(request)+"/login");
        //设置跳转使能
        response.setHeader("enableRedirect","true");
        try {
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loginPage(){
        log.info("into here！！！！");
            return "login";
    }
    @RequestMapping(value = "/signup",method = RequestMethod.GET)
    public String showRegist() {
        return "/user/regist";
    }
    @RequestMapping("/index")
    public String index(){
    return "/index";
    }
    @RequestMapping("/redirectindex")
    public String redirectIndex(){
        return "redirect:/index";
    }
}
