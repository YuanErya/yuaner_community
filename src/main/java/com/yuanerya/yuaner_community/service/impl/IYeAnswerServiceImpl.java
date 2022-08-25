package com.yuanerya.yuaner_community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vdurmont.emoji.EmojiParser;
import com.yuanerya.yuaner_community.mapper.YeAnswerMapper;
import com.yuanerya.yuaner_community.model.dto.AnswerDTO;
import com.yuanerya.yuaner_community.model.entity.YeAnswer;
import com.yuanerya.yuaner_community.model.entity.YeUser;
import com.yuanerya.yuaner_community.service.IYeAnswerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class IYeAnswerServiceImpl extends ServiceImpl<YeAnswerMapper,YeAnswer> implements IYeAnswerService {
    @Override
    @Transactional(rollbackFor = Exception.class)//用于保证数据库的同步
    public YeAnswer answer(AnswerDTO dto, YeUser user, String question_id) {
        YeAnswer answer = YeAnswer.builder()
                .userId(user.getId())
                .content(EmojiParser.parseToAliases(dto.getContent()))
                .questionId(question_id)
                .createTime(new Date())
                .build();
        this.baseMapper.insert(answer);
        return answer;
    }
}
