package com.yuanerya.yuaner_community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuanerya.yuaner_community.model.entity.YeAnswer;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface YeAnswerMapper extends BaseMapper<YeAnswer> {
    @Delete("DELETE FROM ye_answer WHERE question_id=#{question_id}")
    public int deleteByQuestionId(@Param("question_id") String question_id);}
