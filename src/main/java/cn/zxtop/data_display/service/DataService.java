package cn.zxtop.data_display.service;

import cn.zxtop.data_display.entity.News;
import cn.zxtop.data_display.util.PageUtils;

public interface DataService {
    PageUtils getDataList(int page, int limit, String websiteName);

    void addData(News news);

    void updateData(News news);

    void deleteDataByIds(String idList);

    void deleteDataById(String newsID);
}
