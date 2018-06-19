package com.dia.app.module.wallpaper.service;

import com.dia.app.module.wallpaper.dao.WallpaperDao;
import com.dia.common.dto.base.BasePageReqDto;
import com.dia.common.dto.wallpaper.WallpaperRespDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Dia
 * @Description:
 * @version:
 * @date: 2018/6/19 15:48
 */
@Service
public class WallpaperService {

    @Autowired
    private WallpaperDao wallpaperDao;

    public Integer count(BasePageReqDto query){
        if(null == query)
            return null;
        return wallpaperDao.count(query);
    }

    public List<WallpaperRespDto> query(BasePageReqDto query){
        if(null == query)
            return null;
        return wallpaperDao.query(query);
    }

}
