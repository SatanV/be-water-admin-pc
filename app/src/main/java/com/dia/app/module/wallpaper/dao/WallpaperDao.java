package com.dia.app.module.wallpaper.dao;

import com.dia.common.dto.base.BasePageReqDto;
import com.dia.common.dto.wallpaper.WallpaperRespDto;

import java.util.List;

/**
 * @author: Dia
 * @Description:
 * @version:
 * @date: 2018/6/19 15:39
 */
public interface WallpaperDao {

    Integer count(BasePageReqDto query);

    List<WallpaperRespDto> query(BasePageReqDto query);

}
