package com.yuanerya.yuaner_community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vdurmont.emoji.EmojiParser;
import com.yuanerya.yuaner_community.common.api.ApiResult;
import com.yuanerya.yuaner_community.mapper.YeAnswerMapper;
import com.yuanerya.yuaner_community.mapper.YeCommentMapper;
import com.yuanerya.yuaner_community.model.dto.AnswerAndCommentDTO;
import com.yuanerya.yuaner_community.model.entity.YeAnswer;
import com.yuanerya.yuaner_community.model.entity.YeUser;
import com.yuanerya.yuaner_community.service.IYeAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class IYeAnswerServiceImpl extends ServiceImpl<YeAnswerMapper,YeAnswer> implements IYeAnswerService {
    @Autowired
    private YeAnswerMapper yeAnswerMapper;
    @Autowired
    private YeCommentMapper yeCommentMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)//用于保证数据库的同步
    public YeAnswer answer(AnswerAndCommentDTO dto, YeUser user, String question_id) {
        YeAnswer answer = YeAnswer.builder()
                .userId(user.getId())
                .content(EmojiParser.parseToAliases(dto.getContent()))
                .questionId(question_id)
                .createTime(new Date())
                .build();
        this.baseMapper.insert(answer);
        return answer;
    }

    @Override
    public ApiResult delete(String answer_id) {
        try {
            yeAnswerMapper.deleteById(answer_id);
            yeCommentMapper.deleteByAnswerId(answer_id);
        }catch (Exception e) {
            return ApiResult.failed("操作失败");
        }
        return ApiResult.success("操作成功，删除了："+answer_id);
    }
}
