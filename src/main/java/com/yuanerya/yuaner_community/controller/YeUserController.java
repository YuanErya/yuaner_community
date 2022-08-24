package com.yuanerya.yuaner_community.controller;

import com.yuanerya.yuaner_community.common.api.ApiResult;
import com.yuanerya.yuaner_community.model.dto.RegisterDTO;
import com.yuanerya.yuaner_community.model.entity.YeUser;
import com.yuanerya.yuaner_community.service.IYeUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class YeUserController {
    @Resource
    private IYeUserService iYeUserService;

    @PostMapping("/register")
    public ApiResult register(@Valid  @RequestBody RegisterDTO dto){
        YeUser user=iYeUserService.register(dto);
        if(user==null){
            return ApiResult.failed("账号注册失败！");
        }
        return ApiResult.success("账号注册成功！");
    }
}
