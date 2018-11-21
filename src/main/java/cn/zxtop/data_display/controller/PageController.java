package cn.zxtop.data_display.controller;

import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Controller
public class PageController {
    @RequestMapping("/")
    public String getIndexPage(){
        return "index";
    }
    @RequestMapping("page")
    public String getPage(String pageName,String title,Model model){
        HashMap<String,String> hashMap=new LinkedHashMap<>();
        hashMap.put("title",title);
        model.addAllAttributes(hashMap);
        return pageName;
    }
    @RequestMapping("layout")
    public String getLayoutPage(Model model){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        HashMap<String,Integer> hashMap=new LinkedHashMap<>();
        hashMap.put("year",calendar.get(Calendar.YEAR));
        hashMap.put("month",calendar.get(Calendar.MONTH));
        hashMap.put("day",calendar.get(Calendar.DAY_OF_MONTH));
        model.addAllAttributes(hashMap);
        return "layout";
    }
}
