package com.yuanerya.yuaner_community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuanerya.yuaner_community.common.api.ApiResult;
import com.yuanerya.yuaner_community.model.dto.AnswerAndCommentDTO;
import com.yuanerya.yuaner_community.model.entity.YeAnswer;
import com.yuanerya.yuaner_community.model.entity.YeUser;


public interface IYeAnswerService extends IService<YeAnswer> {
    /**
     * 创建新回答
     * @param dto
     * @param user
     * @param question_id
     * @return
     */
    YeAnswer answer(AnswerAndCommentDTO dto, YeUser user, String question_id);

    /**
     * 删除回答
     * @param answer_id
     * @param user_id
     * @return
     */
    ApiResult delete(String answer_id,String user_id);

    /**
     * 检查发起该操作的是否为回答者本人
     * 并修改回答内容
     * @param answer_id
     * @param user_id
     * @param dto
     * @return
     */
    ApiResult checkAndUpdate(String answer_id, String user_id,AnswerAndCommentDTO dto);
}
