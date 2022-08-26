package com.yuanerya.yuaner_community.model.vo;

import com.yuanerya.yuaner_community.model.entity.YeAnswer;
import com.yuanerya.yuaner_community.model.entity.YeComment;
import com.yuanerya.yuaner_community.model.entity.YeQuestion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FootPrintVO implements Serializable {
    private static final long serialVersionUID = -261082150965211545L;
    /**
     * 储存本人所发布的全部问题
     */
    private List<YeQuestion> question;
    /**
     * 储存全部的回答的信息
     */
    private List<YeAnswer> answer;
    /**
     * 储存全部的对回答的评论信息
     */
    private List<YeComment> comment;


}
