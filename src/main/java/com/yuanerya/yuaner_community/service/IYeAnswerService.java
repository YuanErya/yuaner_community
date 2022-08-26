package com.yuanerya.yuaner_community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuanerya.yuaner_community.model.dto.AnswerAndCommentDTO;
import com.yuanerya.yuaner_community.model.entity.YeAnswer;
import com.yuanerya.yuaner_community.model.entity.YeUser;


public interface IYeAnswerService extends IService<YeAnswer> {
    YeAnswer answer(AnswerAndCommentDTO dto, YeUser user, String question_id);
}