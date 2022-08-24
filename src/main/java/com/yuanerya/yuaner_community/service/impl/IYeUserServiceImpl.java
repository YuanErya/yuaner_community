package com.yuanerya.yuaner_community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuanerya.yuaner_community.common.exception.ApiAsserts;
import com.yuanerya.yuaner_community.jwt.JwtUtil;
import com.yuanerya.yuaner_community.mapper.YeUserMapper;
import com.yuanerya.yuaner_community.model.dto.LoginDTO;
import com.yuanerya.yuaner_community.model.dto.RegisterDTO;
import com.yuanerya.yuaner_community.model.entity.YeUser;
import com.yuanerya.yuaner_community.service.IYeUserService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class IYeUserServiceImpl extends ServiceImpl<YeUserMapper,YeUser> implements IYeUserService {


    @Override
    public YeUser register(RegisterDTO dto) {
        //用户名和邮箱要与数据库中的已经存在的数据进行比对查重
        LambdaQueryWrapper<YeUser> lqw = new LambdaQueryWrapper<>();
        //判断名字和邮箱
        lqw.eq(YeUser::getUsername,dto.getName()).or().eq(YeUser::getEmail,dto.getEmail());
        YeUser yeUser=baseMapper.selectOne(lqw);
        if(yeUser!=null){
            ApiAsserts.fail("账号或邮箱已存在");
        }
        YeUser registerUser= YeUser.builder()
                .username(dto.getName())
                .alias(dto.getAlias())
                .password(dto.getPass())
                .email(dto.getEmail())
                .createTime(new Date())
                .status(true)
                .build();
        baseMapper.insert(registerUser);
        return registerUser;
    }

    @Override
    public String login(LoginDTO dto) {
        String token =null;
        try {
            LambdaQueryWrapper<YeUser> lqw=new LambdaQueryWrapper<>();
            lqw.eq(YeUser::getUsername,dto.getName());
            YeUser yeUser =baseMapper.selectOne(lqw);
            if(!yeUser.getPassword().equals(dto.getPass())){
                throw new Exception("密码错误，请输入正确的密码！");
            }
            token= JwtUtil.generateToken(yeUser.getUsername());
        }catch(Exception e){

        }
        return token;
    }

    @Override
    public YeUser getYeUserByUsername(String username) {
        return baseMapper.selectOne(new LambdaQueryWrapper<YeUser>().eq(YeUser::getUsername, username));
    }


}
