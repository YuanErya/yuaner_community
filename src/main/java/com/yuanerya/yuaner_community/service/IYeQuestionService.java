package com.yuanerya.yuaner_community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
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
}
