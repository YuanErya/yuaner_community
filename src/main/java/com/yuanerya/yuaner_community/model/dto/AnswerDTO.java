package com.yuanerya.yuaner_community.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AnswerDTO implements Serializable {
    private static final long serialVersionUID = -5957433707110390852L;

    /**
     * 内容
     */
    private String content;
}
