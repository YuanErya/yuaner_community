package com.yuanerya.yuaner_community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuanerya.yuaner_community.model.dto.RegisterDTO;
import com.yuanerya.yuaner_community.model.entity.YeUser;

public interface IYeUserService extends IService<YeUser> {

    /**
     * 注册功能的实现
     * @param dto
     * @return
     */
    YeUser register(RegisterDTO dto);
}
