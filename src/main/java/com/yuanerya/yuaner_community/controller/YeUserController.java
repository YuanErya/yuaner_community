package com.yuanerya.yuaner_community.controller;

import com.yuanerya.yuaner_community.common.api.ApiResult;
import com.yuanerya.yuaner_community.model.dto.LoginDTO;
import com.yuanerya.yuaner_community.model.dto.RegisterDTO;
import com.yuanerya.yuaner_community.model.entity.YeUser;
import com.yuanerya.yuaner_community.service.IYeUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


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

    @PostMapping("/login")
    public ApiResult<Map<String,String>> login(@Valid @RequestBody LoginDTO dto){
        String token= iYeUserService.login(dto);
        if(token==null){
            return ApiResult.failed("账号密码错误");
        }
        Map<String,String> map=new HashMap<String,String>(16);
        map.put("token",token);
        return ApiResult.success(map,"登录成功");
    }
}
