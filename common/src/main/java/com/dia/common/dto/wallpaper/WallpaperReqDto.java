package com.dia.common.dto.wallpaper;

import com.dia.common.dto.base.BasePageReqDto;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: Dia
 * @Description:
 * @version:
 * @date: 2018/6/19 16:20
 */
public class WallpaperReqDto extends BasePageReqDto {

    @ApiModelProperty(value = "壁纸类型")
    private String type;
    @ApiModelProperty(value = "壁纸标签")
    private String tag;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
