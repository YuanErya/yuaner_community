package com.yuanerya.yuaner_community.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vdurmont.emoji.EmojiParser;
import com.yuanerya.yuaner_community.common.api.ApiResult;
import com.yuanerya.yuaner_community.mapper.YeAnswerMapper;
import com.yuanerya.yuaner_community.mapper.YeCommentMapper;
import com.yuanerya.yuaner_community.mapper.YeQuestionMapper;
import com.yuanerya.yuaner_community.model.dto.CreateQuestionDTO;
import com.yuanerya.yuaner_community.model.entity.YeQuestion;
import com.yuanerya.yuaner_community.model.entity.YeUser;
import com.yuanerya.yuaner_community.model.vo.QuestionVO;
import com.yuanerya.yuaner_community.service.IYeQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class IYeQuestionServiceImpl extends ServiceImpl<YeQuestionMapper, YeQuestion> implements IYeQuestionService {

    @Autowired
    private YeQuestionMapper yeQuestionMapper;
    @Autowired
    private YeAnswerMapper yeAnswerMapper;
    @Autowired
    private YeCommentMapper yeCommentMapper;

    public Page<QuestionVO> getPage(Integer pageNo,Integer pageSize){
        Page page=new Page(pageNo,pageSize);
        yeQuestionMapper.getPage(page);
        return page;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)//用于保证数据库的同步
    public YeQuestion create(CreateQuestionDTO dto, YeUser user) {
//在问题的数据表中插入数据
        YeQuestion yq = YeQuestion.builder()
                .userId(user.getId())
                .title(dto.getTitle())
                .content(EmojiParser.parseToAliases(dto.getContent()))
                .createTime(new Date())
                .build();
        this.baseMapper.insert(yq);
        return yq;
    }

    @Override
    public ApiResult delete(String question_id) {
        try {
        yeQuestionMapper.deleteById(question_id);
        yeAnswerMapper.deleteByQuestionId(question_id);
        yeCommentMapper.deleteByQuestionId(question_id);
    }catch (Exception e){
            return ApiResult.failed("操作失败");
        }
        return ApiResult.success("操作成功,删除了："+question_id);
    }
}
