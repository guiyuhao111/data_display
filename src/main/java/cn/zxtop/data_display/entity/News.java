package cn.zxtop.data_display.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.lang.reflect.Type;
import java.util.Date;

@TableName("News")
public class News {
    @TableId("NewsID")
    private String newsID;
    @TableField("Title")
    private String title;
    @TableField("Type")
    private String type;
    @TableField("Link")
    private String link;
    @TableField("Remark")
    private String remark;
    @TableField("Author")
    private String author;
    @TableField("CreateTime")
    private Date createTime;
    @TableField("ModifiedTime")
    private Date modifiedTime;
    @TableField("PubTime")
    private Date pubTime;
    @TableField("WebSiteName")
    private String webSiteName;

    public String getWebSiteName() {
        return webSiteName;
    }

    public void setWebSiteName(String webSiteName) {
        this.webSiteName = webSiteName;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public String getNewsID() {
        return newsID;
    }

    public void setNewsID(String newsID) {
        this.newsID = newsID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsID='" + newsID + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", link='" + link + '\'' +
                ", remark='" + remark + '\'' +
                ", author='" + author + '\'' +
                ", createTime=" + createTime +
                ", modifiedTime=" + modifiedTime +
                ", pubTime=" + pubTime +
                ", webSiteName='" + webSiteName + '\'' +
                '}';
    }

}
