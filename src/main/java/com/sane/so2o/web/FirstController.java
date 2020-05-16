package com.sane.so2o.web;

import com.sane.so2o.entity.Area;
import com.sane.so2o.service.AreaService;
import com.sane.so2o.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FirstController {
    @Autowired
    private AreaService areaService;
    @Autowired
    private IArticleService articleService;
@RequestMapping("/first")
@ResponseBody
    public  Object first(){
    List<Area> areaList=areaService.queryAreaList();
    return  areaList;
    }
    @RequestMapping("/index")
    public String index(){
    articleService.test();
    return "/index";
    }
}
