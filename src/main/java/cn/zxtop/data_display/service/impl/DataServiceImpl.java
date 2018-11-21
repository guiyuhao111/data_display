package cn.zxtop.data_display.service.impl;

import cn.zxtop.data_display.dao.DataMapper;
import cn.zxtop.data_display.entity.News;
import cn.zxtop.data_display.service.DataService;
import cn.zxtop.data_display.util.PageUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class DataServiceImpl implements DataService {
    @Resource
    private DataMapper dataMapper;
    @Override
    public PageUtils getDataList(int currentPage, int limit, String websiteName) {
        Page<News> pageEntity=new Page<>();
        pageEntity.setCurrent(currentPage);
        pageEntity.setSize(limit);
        List<News> t=new ArrayList<>();
        QueryWrapper wrapper=new QueryWrapper<News>();
        wrapper.orderByDesc("PubTime");
        wrapper.orderByDesc("CreateTime");
        wrapper.eq("WebsiteName",websiteName);
        Page<News> page =(Page<News>) dataMapper.selectPage(pageEntity,wrapper);
        return  new PageUtils(page);
    }

    @Override
    public void addData(News news) {
        news.setCreateTime(new Date());
        news.setModifiedTime(new Date());
        news.setNewsID(UUID.randomUUID().toString());
        int i =  dataMapper.insert(news);
        if (i!=1){
            throw new RuntimeException("添加数据异常");
        }
    }

    @Override
    public void updateData(News news) {
        news.setModifiedTime(new Date());
        int i = dataMapper.updateById(news);
        if (i!=1){
            throw new RuntimeException("修改数据异常");
        }
    }

    @Override
    public void deleteDataByIds(String ids) {
         String [] idArr= ids.split(",");
         List<String> idList=Arrays.asList(idArr);
        int state = dataMapper.deleteBatchIds(idList);
        if (state!=idArr.length){
            throw new RuntimeException("删除数据失败");
        }
    }

    @Override
    public void deleteDataById(String newsID) {
        int state = dataMapper.deleteById(newsID);
        if (state!=1){
            throw new RuntimeException("删除数据失败");
        }
    }
}
