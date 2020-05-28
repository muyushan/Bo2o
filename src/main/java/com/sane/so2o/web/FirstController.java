package com.sane.so2o.web;

import com.sane.so2o.entity.Area;
import com.sane.so2o.service.AreaService;
import com.sane.so2o.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FirstController {
    @Autowired
    private AreaService areaService;
    @Autowired
    private IArticleService articleService;
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public  String loginPage(){
    return  "login";
    }
    @RequestMapping(value = "/signup",method = RequestMethod.GET)
    public String showRegist() {
        return "/user/regist";
    }
    @RequestMapping("/index")
    public String index(){
    articleService.test();
    return "/index";
    }
    @RequestMapping("/redirectindex")
    public String redirectIndex(){
        return "redirect:/index";
    }
}
