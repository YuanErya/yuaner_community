package com.yuanerya.yuaner_community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.vdurmont.emoji.EmojiParser;
import com.yuanerya.yuaner_community.common.api.ApiResult;
import com.yuanerya.yuaner_community.mapper.YeCommentMapper;
import com.yuanerya.yuaner_community.model.dto.AnswerAndCommentDTO;

import com.yuanerya.yuaner_community.model.entity.YeComment;
import com.yuanerya.yuaner_community.model.entity.YeUser;

import com.yuanerya.yuaner_community.service.IYeAnswerService;
import com.yuanerya.yuaner_community.service.IYeCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class IYeCommentServiceImpl extends ServiceImpl<YeCommentMapper, YeComment> implements IYeCommentService {

    @Autowired
    private IYeAnswerService iYeAnswerService;
    @Autowired
    private  YeCommentMapper yeCommentMapper;

    /**
     * 创建一个新的评论
     * @param dto
     * @param user
     * @param answer_id
     * @return
     */
    @Override
    public YeComment comment(AnswerAndCommentDTO dto, YeUser user, String answer_id) {
        //通过回答的ID找对回答的全部信息在获取问题的ID
        String question_id=iYeAnswerService.getById(answer_id).getQuestionId();
        YeComment comment =YeComment.builder()
                .userId(user.getId())
                .answerId(answer_id)
                .content(EmojiParser.parseToAliases(dto.getContent()))
                .createTime(new Date())
                .questionId(question_id)
                .build();
        yeCommentMapper.insert(comment);
        return comment;
    }

    /**
     * 删除自己的评论
     * @param comment_id
     * @param user_id
     * @return
     */
    @Override
    public ApiResult delete(String comment_id,String user_id) {
        try {
            if(yeCommentMapper.selectById(comment_id).getUserId().equals(user_id)){
                //检验操作者
                yeCommentMapper.deleteById(comment_id);}
            else{
                return ApiResult.failed("非法操作，您只能删除你自己的评论！");
            }

        }catch (Exception e){
            return ApiResult.failed("操作失败");
        }
        return ApiResult.success("操作成功，删除了："+comment_id);
    }

    /**
     * 修改自己的评论
     * @param comment_id
     * @param user_id
     * @param dto
     * @return
     */
    @Override
    public ApiResult checkAndUpdate(String comment_id, String user_id,AnswerAndCommentDTO dto) {
        try {
            if(yeCommentMapper.selectById(comment_id).getUserId().equals(user_id)) {
                //检验操作者
                YeComment comment=yeCommentMapper.selectById(comment_id);
                comment.setContent(dto.getContent());
                comment.setModifyTime(new Date());
                yeCommentMapper.updateById(comment);
            }
            else{
                return ApiResult.failed("非法操作，您只能修改你自己的评论！");
            }

        }catch (Exception e){
            return ApiResult.failed("操作失败");
        }
        return ApiResult.success("修改成功，修改的评论为：" + comment_id);
    }

}
