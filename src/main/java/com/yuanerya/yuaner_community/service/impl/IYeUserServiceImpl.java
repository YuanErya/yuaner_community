package com.yuanerya.yuaner_community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuanerya.yuaner_community.common.exception.ApiAsserts;
import com.yuanerya.yuaner_community.jwt.JwtUtil;
import com.yuanerya.yuaner_community.mapper.YeAnswerMapper;
import com.yuanerya.yuaner_community.mapper.YeCommentMapper;
import com.yuanerya.yuaner_community.mapper.YeQuestionMapper;
import com.yuanerya.yuaner_community.mapper.YeUserMapper;
import com.yuanerya.yuaner_community.model.dto.LoginDTO;
import com.yuanerya.yuaner_community.model.dto.RegisterDTO;
import com.yuanerya.yuaner_community.model.entity.YeAnswer;
import com.yuanerya.yuaner_community.model.entity.YeComment;
import com.yuanerya.yuaner_community.model.entity.YeQuestion;
import com.yuanerya.yuaner_community.model.entity.YeUser;
import com.yuanerya.yuaner_community.model.vo.FootPrintVO;
import com.yuanerya.yuaner_community.service.IYeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class IYeUserServiceImpl extends ServiceImpl<YeUserMapper,YeUser> implements IYeUserService {

    @Autowired
    private YeUserMapper yeUserMapper;
    @Autowired
    private YeQuestionMapper yeQuestionMapper;
    @Autowired
    private YeAnswerMapper yeAnswerMapper;
    @Autowired
    private YeCommentMapper yeCommentMapper;

    /**
     * 注册
     * @param dto 前端来的信息
     * @return 将生成的用户信息返回给前端
     */
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
        yeUserMapper.insert(registerUser);
        return registerUser;
    }

    /**
     * 用户登录
     * @param dto 来自前端的登录的账号和密码
     * @return 返回生成的TOKEN
     */

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

    /**
     * 根据用户名来进行查询
     * @param username 传来的用户名
     * @return 返回符合要求的用户
     */
    @Override
    public YeUser getYeUserByUsername(String username) {
        return yeUserMapper.selectOne(new LambdaQueryWrapper<YeUser>().eq(YeUser::getUsername, username));
    }

    /**
     * 用户可以获取自己的所有问题、所有回答、所有评论。
     * @param userId 获取到用户的ID
     * @return
     */
    @Override
    public FootPrintVO getFootprint(String userId) {
        FootPrintVO footprint = new FootPrintVO();
        footprint.setQuestion(yeQuestionMapper.selectList(
                new LambdaQueryWrapper<YeQuestion>().eq(YeQuestion::getUserId,userId)));
        footprint.setAnswer(yeAnswerMapper.selectList(
                new LambdaQueryWrapper<YeAnswer>().eq(YeAnswer::getUserId,userId)));
        footprint.setComment(yeCommentMapper.selectList(
                new LambdaQueryWrapper<YeComment>().eq(YeComment::getUserId,userId)));
        return footprint;
    }


}
