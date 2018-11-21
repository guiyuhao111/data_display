package cn.zxtop.data_display.controller;

import cn.zxtop.data_display.dao.DataMapper;
import cn.zxtop.data_display.entity.JsonResult;
import cn.zxtop.data_display.entity.News;
import cn.zxtop.data_display.service.DataService;
import cn.zxtop.data_display.util.PageUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("data")
public class DataController {
    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            public void setAsText(String value) {
                try {
                    setValue(new SimpleDateFormat("yyyy-MM-dd").parse(value));
                } catch(ParseException e) {
                    setValue(null);
                }
            }
            public String getAsText() {
                return new SimpleDateFormat("yyyy-MM-dd").format((Date) getValue());
            }
        });
    }
    @Resource
    private DataService dataService;

    @RequestMapping("getDataList")
    @ResponseBody
    public JsonResult getDataList(int page,int limit,String websiteName){
        return new JsonResult(dataService.getDataList(page, limit,websiteName));
    }
    @RequestMapping("addData")
    @ResponseBody
    public JsonResult addData(News news){
        System.out.println("news = [" + news + "]");
        dataService.addData(news);
        return new JsonResult();
    }
    @RequestMapping("updateData")
    @ResponseBody
    public JsonResult updateData(News news){
        System.out.println("news = [" + news + "]");
        dataService.updateData(news);
        return new JsonResult();
    }
    @RequestMapping("deleteDataByIds")
    @ResponseBody
    public JsonResult deleteDataByIds(String idList){
        System.out.println("idList = [" + idList + "]");
        dataService.deleteDataByIds(idList);
        return new JsonResult();
    }
    @RequestMapping("deleteDataByID")
    @ResponseBody
    public JsonResult deleteDataByID(String newsID){
        System.out.println("newsID = [" + newsID + "]");
        dataService.deleteDataById(newsID);
        return new JsonResult();
    }
}
