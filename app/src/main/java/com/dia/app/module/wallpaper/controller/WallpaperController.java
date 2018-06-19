package com.dia.app.module.wallpaper.controller;

import com.dia.app.module.wallpaper.service.WallpaperService;
import com.dia.common.constants.ResponseCode;
import com.dia.common.dto.base.BaseReqDto;
import com.dia.common.dto.base.BaseRespDto;
import com.dia.common.dto.wallpaper.WallpaperReqDto;
import com.dia.common.dto.wallpaper.WallpaperRespDto;
import com.dia.common.route.WallpaperUrl;
import com.dia.common.util.BaseRespDtoWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Dia
 * @Description: 壁纸controller
 * @version:
 * @date: 2018/6/19 15:46
 */
@Api(value = "壁纸数据查询-Wallpaper", tags = { "壁纸" })
@RestController
public class WallpaperController {

    @Autowired
    private WallpaperService wallpaperService;

    @ApiOperation(value = "壁纸-总数", httpMethod = "POST", notes = "查询")
    @PostMapping(WallpaperUrl.WALLPAPER_COUNT)
    public BaseRespDto<Integer> count(@RequestBody BaseReqDto<WallpaperReqDto>  query){
        BaseRespDtoWrapper<Integer> result = new BaseRespDtoWrapper();
        Integer count = wallpaperService.count(query.getData());
        return result.success(count);
    }

    @ApiOperation(value = "壁纸-分页查询", httpMethod = "POST", notes = "查询")
    @PostMapping(WallpaperUrl.WALLPAPER_QUERY)
    public BaseRespDto<List<WallpaperRespDto>> query(@RequestBody BaseReqDto<WallpaperReqDto> query){
        BaseRespDtoWrapper<List<WallpaperRespDto>> result = new BaseRespDtoWrapper();
        Integer count = wallpaperService.count(query.getData());
        if(count > 0){
            List<WallpaperRespDto> list = wallpaperService.query(query.getData());
            return result.pageSuccess(list,count);
        }
        return result.pageError(ResponseCode.INTERNAL_DB_ERROR);
    }

}
