package com.yuanerya.yuaner_community.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuanerya.yuaner_community.common.api.ApiResult;
import com.yuanerya.yuaner_community.jwt.JwtUtil;
import com.yuanerya.yuaner_community.model.dto.AnswerDTO;
import com.yuanerya.yuaner_community.model.dto.CreateQuestionDTO;
import com.yuanerya.yuaner_community.model.entity.YeAnswer;
import com.yuanerya.yuaner_community.model.entity.YeQuestion;
import com.yuanerya.yuaner_community.model.entity.YeUser;
import com.yuanerya.yuaner_community.model.vo.QuestionVO;
import com.yuanerya.yuaner_community.service.IYeAnswerService;
import com.yuanerya.yuaner_community.service.IYeQuestionService;
import com.yuanerya.yuaner_community.service.IYeUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.yuanerya.yuaner_community.jwt.JwtUtil.HEADER_STRING;

@RestController
@RequestMapping("/question")
public class YeQuestionController {
    @Resource
    private IYeQuestionService iYeQuestionService;
    @Resource
    private IYeUserService iYeUserService;
    @Resource
    private IYeAnswerService iYeAnswerService;

    /**
     * 分页查询问题
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public ApiResult<Page<QuestionVO>> list(@RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                            @RequestParam(value = "size", defaultValue = "10") Integer pageSize){
        Page<QuestionVO> pageList = iYeQuestionService.getPage(pageNo, pageSize);
        return ApiResult.success(pageList);
    }

    @PostMapping("/create")
    public ApiResult<YeQuestion> create(@RequestHeader(value = HEADER_STRING) String token,
                                        @RequestBody CreateQuestionDTO dto){
        String userName = JwtUtil.parseToken(token);
        YeUser user=iYeUserService.getYeUserByUsername(userName);
        YeQuestion question=iYeQuestionService.create(dto,user);
        return ApiResult.success(question);
    }

    @PostMapping("/answer")
    public ApiResult<YeAnswer> answer(@RequestHeader(value = HEADER_STRING) String token,
                                      @RequestHeader(value = "question_id") String question_id,
                                      @RequestBody AnswerDTO dto){
        String userName = JwtUtil.parseToken(token);
        YeUser user=iYeUserService.getYeUserByUsername(userName);
        YeAnswer answer=iYeAnswerService.answer(dto,user,question_id);
        return ApiResult.success(answer);
    }


}
