package com.yuanerya.yuaner_community.controller;

import com.yuanerya.yuaner_community.common.api.ApiResult;
import com.yuanerya.yuaner_community.model.dto.LoginDTO;
import com.yuanerya.yuaner_community.model.dto.RegisterDTO;
import com.yuanerya.yuaner_community.model.entity.YeUser;
import com.yuanerya.yuaner_community.service.IYeUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static com.yuanerya.yuaner_community.jwt.JwtUtil.USER_NAME;


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

    /**
     *
     * @param dto 前端发送来的信息
     * @return 返回成功信息，并将生成的token返回给前端  taken是根据用户名加密生成的一段乱码
     */
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

    /**
     * 进行token验证，验证成功后，通过Header获取到userName,
     * 根据UserName再到数据库中进行查询，获取到用户的全部信息
     * @param userName
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ApiResult<YeUser> getUser(@RequestHeader(value = USER_NAME) String userName) {
       YeUser user = iYeUserService.getYeUserByUsername(userName);
        return ApiResult.success(user);
    }

}
