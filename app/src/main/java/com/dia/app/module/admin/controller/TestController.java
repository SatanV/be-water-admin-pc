package com.dia.app.module.admin.controller;

import com.dia.common.dto.base.BaseReqDto;
import com.dia.common.dto.base.BaseRespDto;
import com.dia.common.util.BaseRespDtoWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author: Dia
 * @Description: 测试
 * @version: v1.0.0
 * @date: 2018年5月29日 上午01:14:15
 */
@Api(value = "Dia管理后台-DiaWallpaper", tags = { "测试接口" })
@RestController
public class TestController {

    @ApiOperation(value = "测试", httpMethod = "POST", notes = "Test")
    @PostMapping("Test")
    public BaseRespDto<Object> login(@RequestBody @Valid BaseReqDto<Object> query) {
        return new  BaseRespDtoWrapper<Object>().success();
    }

}
