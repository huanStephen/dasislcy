package org.eocencle.dasislcy.controller;

import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.dto.ChoiceQuestionDto;
import org.eocencle.dasislcy.entity.ChoiceQuestionEntity;
import org.eocencle.dasislcy.service.IChoiceQuestionService;
import org.eocencle.dasislcy.service.IQuestionService;
import org.eocencle.dasislcy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return result;
    }

}
