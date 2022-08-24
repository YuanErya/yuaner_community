package com.yuanerya.yuaner_community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuanerya.yuaner_community.model.dto.LoginDTO;
import com.yuanerya.yuaner_community.model.dto.RegisterDTO;
import com.yuanerya.yuaner_community.model.entity.YeUser;

public interface IYeUserService extends IService<YeUser> {

    /**
     * 注册功能的实现
     * @param dto
     * @return
     */
    YeUser register(RegisterDTO dto);

    /**
     * 登录
     * @param dto
     * @return
     */
    String login(LoginDTO dto);

    /**
     * 根据用户名获取到用户
     * @param username
     * @return
     */
    YeUser getYeUserByUsername(String username);
}
