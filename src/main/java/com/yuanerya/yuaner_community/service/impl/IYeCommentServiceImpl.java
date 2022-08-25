package com.yuanerya.yuaner_community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.vdurmont.emoji.EmojiParser;
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
}
