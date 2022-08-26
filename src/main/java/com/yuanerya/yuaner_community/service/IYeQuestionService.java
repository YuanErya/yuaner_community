package com.yuanerya.yuaner_community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuanerya.yuaner_community.common.api.ApiResult;
import com.yuanerya.yuaner_community.model.dto.AnswerAndCommentDTO;
import com.yuanerya.yuaner_community.model.dto.CreateQuestionDTO;
import com.yuanerya.yuaner_community.model.entity.YeQuestion;
import com.yuanerya.yuaner_community.model.entity.YeUser;
import com.yuanerya.yuaner_community.model.vo.QuestionVO;

public interface IYeQuestionService extends IService<YeQuestion> {
    /**
     * 分页查询
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<QuestionVO> getPage(Integer pageNo, Integer pageSize);

    /**
     * 发布帖子
     * @param dto
     * @param
     * @return
     */
    YeQuestion create(CreateQuestionDTO dto, YeUser user);

    /**
     * 根据问题的ID删除问题以及它下属的回答和评论
     * @param question_id 传入的问题的id
     * @return 返回操作成功
     */
    ApiResult delete(String question_id,String user_id);

    ApiResult checkAndUpdate(String question_id,String user_id, CreateQuestionDTO dto);
}
