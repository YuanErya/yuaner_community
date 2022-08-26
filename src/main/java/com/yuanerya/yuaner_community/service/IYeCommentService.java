package com.yuanerya.yuaner_community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuanerya.yuaner_community.common.api.ApiResult;
import com.yuanerya.yuaner_community.model.dto.AnswerAndCommentDTO;
import com.yuanerya.yuaner_community.model.entity.YeAnswer;
import com.yuanerya.yuaner_community.model.entity.YeComment;
import com.yuanerya.yuaner_community.model.entity.YeUser;

public interface IYeCommentService extends IService<YeComment> {
    YeComment comment(AnswerAndCommentDTO dto, YeUser user, String answer_id);
    ApiResult delete(String comment_id);
}
