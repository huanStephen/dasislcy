package org.eocencle.dasislcy.controller;

import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.dto.ChoiceQuestionDto;
import org.eocencle.dasislcy.dto.SubjectQuestionRelationDto;
import org.eocencle.dasislcy.entity.ChoiceQuestionEntity;
import org.eocencle.dasislcy.entity.SubjectQuestionEntity;
import org.eocencle.dasislcy.service.IChoiceQuestionService;
import org.eocencle.dasislcy.service.IQuestionService;
import org.eocencle.dasislcy.service.ISubjectService;
import org.eocencle.dasislcy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 试题控制类
 * @Auther: huanStephen
 * @Date: 2019/5/30
 * @Description:
 */
@RestController
@RequestMapping("/wx/question")
public class QuestionController {

    @Autowired
    private IChoiceQuestionService choiceQuestionService;

    @Autowired
    private ISubjectService subjectService;

    @RequestMapping("/getChoiceQuestions")
    public Result<PageAdapter<ChoiceQuestionDto>> getChoiceQuestions(Integer subjectId, Integer chapterId, Integer outlineId, Integer type, Integer currPage, Integer pageSize) {
        Result<PageAdapter<ChoiceQuestionDto>> result = new Result<>(Result.STATUS_SUCCESSED);

        if (null == currPage || 1 > currPage || null == pageSize || 1 > pageSize) {
            result.setStatus(Result.STATUS_FAILED);
            result.setMsg("分页有误！");
            return result;
        }

        PageAdapter<ChoiceQuestionDto> page = new PageAdapter<>();
        page.setCurrPage(currPage);
        page.setPageSize(pageSize);
        page = this.choiceQuestionService.getChoiceQuestionsBySubjectId(subjectId, chapterId, outlineId, page);

        result.setData(page);
        result.setMsg("请求成功！");
        return result;
    }

    @RequestMapping("/getChoiceQuestion")
    public Result<ChoiceQuestionDto> getChoiceQuestion(Integer questionId) {
        Result<ChoiceQuestionDto> result = new Result<>(Result.STATUS_SUCCESSED);

        if (null == questionId) {
            result.setStatus(Result.STATUS_FAILED);
            result.setMsg("试题ID有误！");
            return result;
        }

        result.setData(this.choiceQuestionService.getChoiceQuestionById(questionId));
        result.setMsg("请求成功！");
        return result;
    }

    @RequestMapping("/getChoiceQuestionMappings")
    public Result<List<SubjectQuestionRelationDto>> getChoiceQuestionMappings(Integer questionId) {
        Result<List<SubjectQuestionRelationDto>> result = new Result<>(Result.STATUS_SUCCESSED);

        if (null == questionId) {
            result.setStatus(Result.STATUS_FAILED);
            result.setMsg("试题ID有误！");
            return result;
        }

        result.setData(this.subjectService.getSubjectQuestionMappings(questionId));
        result.setMsg("请求成功！");
        return result;
    }

    @RequestMapping("/addChoiceQuestionMapping")
    public Result<Boolean> addChoiceQuestionMapping(SubjectQuestionEntity mapping) {
        Result<Boolean> result = new Result<>(Result.STATUS_SUCCESSED);

        this.subjectService.addSubjectQuestionMapping(mapping);

        result.setData(true);
        result.setMsg("请求成功！");
        return result;
    }

    @RequestMapping("/updateChoiceQuestionMapping")
    public Result<Boolean> updateChoiceQuestionMapping(SubjectQuestionEntity mapping) {
        Result<Boolean> result = new Result<>(Result.STATUS_SUCCESSED);

        this.subjectService.updateSubjectQuestionMapping(mapping);

        result.setData(true);
        result.setMsg("请求成功！");
        return result;
    }

    @RequestMapping("/delChoiceQuestionMapping")
    public Result<Boolean> delChoiceQuestionMapping(Integer mappingId) {
        Result<Boolean> result = new Result<>(Result.STATUS_SUCCESSED);

        if (null == mappingId) {
            result.setStatus(Result.STATUS_FAILED);
            result.setMsg("试题映射ID有误！");
            return result;
        }

        this.subjectService.removeSubjectQuestionMapping(mappingId);

        result.setData(true);
        return result;
    }

    @RequestMapping("/updateQuestionAnswer")
    public Result<Boolean> updateQuestionAnswer(Integer questionId, Integer answer) {
        Result<Boolean> result = new Result<>(Result.STATUS_SUCCESSED);

        if (null == questionId) {
            result.setStatus(Result.STATUS_FAILED);
            result.setMsg("试题ID有误！");
            return result;
        }

        ChoiceQuestionEntity record = this.choiceQuestionService.getChoiceQuestionById(questionId);
        record.setAnswer(answer);
        this.choiceQuestionService.updateChoiceQuestion(new ChoiceQuestionDto(record));

        result.setData(true);
        result.setMsg("请求成功！");
        return result;
    }
}
