package com.yuanerya.yuaner_community.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuanerya.yuaner_community.common.api.ApiResult;
import com.yuanerya.yuaner_community.jwt.JwtUtil;
import com.yuanerya.yuaner_community.model.dto.AnswerAndCommentDTO;
import com.yuanerya.yuaner_community.model.dto.CreateQuestionDTO;
import com.yuanerya.yuaner_community.model.entity.YeAnswer;
import com.yuanerya.yuaner_community.model.entity.YeComment;
import com.yuanerya.yuaner_community.model.entity.YeQuestion;
import com.yuanerya.yuaner_community.model.entity.YeUser;
import com.yuanerya.yuaner_community.model.vo.QuestionVO;
import com.yuanerya.yuaner_community.service.IYeAnswerService;
import com.yuanerya.yuaner_community.service.IYeCommentService;
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
    @Resource
    private IYeCommentService iYeCommentService;

    /**
     * 分页查询问题
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public ApiResult<Page<QuestionVO>> list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                            @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        Page<QuestionVO> pageList = iYeQuestionService.getPage(pageNo, pageSize);
        return ApiResult.success(pageList);
    }

    /**
     * 创建问题
     *
     * @param token
     * @param dto
     * @return
     */
    @PostMapping("/create")
    public ApiResult<YeQuestion> create(@RequestHeader(value = HEADER_STRING) String token,
                                        @RequestBody CreateQuestionDTO dto) {
        String userName = JwtUtil.parseToken(token);
        YeUser user = iYeUserService.getYeUserByUsername(userName);
        YeQuestion question = iYeQuestionService.create(dto, user);
        return ApiResult.success(question);
    }

    /**
     * 回答问题
     *
     * @param token
     * @param question_id 需要携带当前所在问题的ID
     * @param dto
     * @return
     */
    @PostMapping("/answer")
    public ApiResult<YeAnswer> answer(@RequestHeader(value = HEADER_STRING) String token,
                                      @RequestHeader(value = "question_id") String question_id,
                                      @RequestBody AnswerAndCommentDTO dto) {
        String userName = JwtUtil.parseToken(token);
        YeUser user = iYeUserService.getYeUserByUsername(userName);
        YeAnswer answer = iYeAnswerService.answer(dto, user, question_id);
        return ApiResult.success(answer);
    }

    /**
     * 对 问题的回答进行评论
     *
     * @param token
     * @param answer_id
     * @param dto
     * @return
     */
    @PostMapping("/answer/comment")
    public ApiResult<YeComment> comment(@RequestHeader(value = HEADER_STRING) String token,
                                        @RequestHeader(value = "answer_id") String answer_id,
                                        @RequestBody AnswerAndCommentDTO dto) {
        String userName = JwtUtil.parseToken(token);
        YeUser user = iYeUserService.getYeUserByUsername(userName);
        YeComment comment = iYeCommentService.comment(dto, user, answer_id);
        return ApiResult.success(comment);


    }

    /**
     *删除问题
     * @param question_id
     * @return 操作成功则返回删除的问题的id
     */

    @DeleteMapping("/delete/question")
    public ApiResult deleteQuestion(@RequestHeader(value = HEADER_STRING) String token,
                                    @RequestHeader(value = "question_id") String question_id) {
        String userName = JwtUtil.parseToken(token);
        String user_id = iYeUserService.getYeUserByUsername(userName).getId();
        return iYeQuestionService.delete(question_id,user_id);
    }

    @PutMapping("/update/question")
    public ApiResult updateAnswer(@RequestHeader(value = HEADER_STRING) String token,
                                  @RequestHeader(value = "question_id") String question_id,
                                  @RequestBody CreateQuestionDTO dto
                                  ){
        String userName = JwtUtil.parseToken(token);
        String user_id = iYeUserService.getYeUserByUsername(userName).getId();
        return iYeQuestionService.checkAndUpdate(question_id,user_id,dto);
    }

    /**
     * 删除回答
     * @param answer_id
     * @return 操作成功则返回删除的回答的id
     */
    @DeleteMapping("/delete/answer")
    public ApiResult deleteAnswer(@RequestHeader(value = HEADER_STRING) String token,
                                  @RequestHeader(value = "answer_id") String answer_id) {
        String userName = JwtUtil.parseToken(token);
        String user_id = iYeUserService.getYeUserByUsername(userName).getId();
        return iYeAnswerService.delete(answer_id,user_id);
    }

    @PutMapping("/update/answer")
    public ApiResult updateAnswer(@RequestHeader(value = HEADER_STRING) String token,
                                   @RequestHeader(value ="answer_id")  String answer_id,
                                   @RequestBody AnswerAndCommentDTO dto){
        String userName = JwtUtil.parseToken(token);
        String user_id = iYeUserService.getYeUserByUsername(userName).getId();
        return iYeAnswerService.checkAndUpdate(answer_id,user_id,dto);
    }


    /**
     * 删除评论
     * @param comment_id
     * @return 操作成功则返回删除的评论的id
     */
    @DeleteMapping("/delete/comment")
    public ApiResult deleteComment(@RequestHeader(value = HEADER_STRING) String token,
                                   @RequestHeader(value = "comment_id") String comment_id) {
        String userName = JwtUtil.parseToken(token);
        String user_id = iYeUserService.getYeUserByUsername(userName).getId();
        return iYeCommentService.delete(comment_id,user_id);
    }

    /**\
     * 修改已经发表的评论
     * @param token
     * @param comment_id
     * @param dto
     * @return
     */
    @PutMapping("/update/comment")
    public ApiResult updateComment(@RequestHeader(value = HEADER_STRING) String token,
                                   @RequestHeader(value ="comment_id")  String comment_id,
                                   @RequestBody AnswerAndCommentDTO dto){
        String userName = JwtUtil.parseToken(token);
        String user_id = iYeUserService.getYeUserByUsername(userName).getId();
        return iYeCommentService.checkAndUpdate(comment_id,user_id,dto);
    }

}
