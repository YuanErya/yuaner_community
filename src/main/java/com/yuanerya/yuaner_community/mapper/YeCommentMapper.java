package com.yuanerya.yuaner_community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuanerya.yuaner_community.model.entity.YeComment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface YeCommentMapper extends BaseMapper<YeComment> {
    @Delete("DELETE FROM ye_comment WHERE question_id=#{question_id}")
    void deleteByQuestionId(@Param("question_id") String question_id);
    @Delete("DELETE FROM ye_comment WHERE answer_id=#{answer_id}")
    void deleteByAnswerId(@Param("answer_id") String answer_id);
}
