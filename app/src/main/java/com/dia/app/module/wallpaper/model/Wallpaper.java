package com.dia.app.module.wallpaper.model;

import java.util.Date;

/**
 * @author: Dia
 * @Description: 壁纸 对象实体
 * @version:
 * @date: 2018/6/19 14:26
 */
public class Wallpaper {

    /**
     * 主键ID
     */
    private Long id;
    /**
     * 壁纸名称
     */
    private String name;
    /**
     * 缩略图地址
     */
    private String thumbUrl;
    /**
     * 图片地址
     */
    private String imageUrl;
    /**
     * 访问地址 暂时没用
     */
    private String httpUrl;
    /**
     * oss 访问地址 暂时没用
     */
    private String ossUrl;
    /**
     * 网站ID
     */
    private String websiteId;
    /**
     * 收藏次数
     */
    private Long favorites;
    /**
     * 星星
     */
    private Long star;
    /**
     * 描述
     */
    private String description;
    /**
     * 类型
     */
    private String type;
    /**
     * 上传者
     */
    private String uploader;
    /**
     * 查看次数
     */
    private String views;
    /**
     * 壁纸大小
     */
    private Long size;
    /**
     * 分类
     */
    private String category;
    /**
     * 上传时间
     */
    private Date gmtUploadTime;
    /**
     * 爬取时间
     */
    private Date gmtGetTime;
    /**
     * 分辨率
     */
    private String resolution;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public String getOssUrl() {
        return ossUrl;
    }

    public void setOssUrl(String ossUrl) {
        this.ossUrl = ossUrl;
    }

    public String getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(String websiteId) {
        this.websiteId = websiteId;
    }

    public Long getFavorites() {
        return favorites;
    }

    public void setFavorites(Long favorites) {
        this.favorites = favorites;
    }

    public Long getStar() {
        return star;
    }

    public void setStar(Long star) {
        this.star = star;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getGmtUploadTime() {
        return gmtUploadTime;
    }

    public void setGmtUploadTime(Date gmtUploadTime) {
        this.gmtUploadTime = gmtUploadTime;
    }

    public Date getGmtGetTime() {
        return gmtGetTime;
    }

    public void setGmtGetTime(Date gmtGetTime) {
        this.gmtGetTime = gmtGetTime;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }
}
