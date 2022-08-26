package com.yuanerya.yuaner_community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuanerya.yuaner_community.model.entity.YeComment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface YeCommentMapper extends BaseMapper<YeComment> {
    /**
     * 根据问题的ID，删除评论
     * 主要是用在删除问题时候，同时删除该问题下所属的评论
     * @param question_id
     */
    @Delete("DELETE FROM ye_comment WHERE question_id=#{question_id}")
    void deleteByQuestionId(@Param("question_id") String question_id);

    /**
     * 根据回答的ID删除评论
     * 主要是用于在删除回答的时候顺带删除其下所属的评论
     * @param answer_id
     */
    @Delete("DELETE FROM ye_comment WHERE answer_id=#{answer_id}")
    void deleteByAnswerId(@Param("answer_id") String answer_id);
}
