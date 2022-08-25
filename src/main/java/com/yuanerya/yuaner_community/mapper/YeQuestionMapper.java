package com.yuanerya.yuaner_community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuanerya.yuaner_community.model.entity.YeQuestion;
import com.yuanerya.yuaner_community.model.vo.QuestionVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface YeQuestionMapper extends BaseMapper<YeQuestion> {

    public Page<QuestionVO> getPage(@Param("page") Page<QuestionVO> page);

}
