package org.eocencle.dasislcy.service;

import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.dto.ExamQuestionDto;
import org.eocencle.dasislcy.entity.ExamquestionEntity;

import java.util.List;

/**
 * 试题service
 * @Auther: huanStephen
 * @Date: 2019/4/25
 * @Description:
 */
public interface IExamquestionService {

    /**
     * 获取试题
     * @param id
     * @return
     */
    ExamquestionEntity getExamquestionById(Integer id);

    /**
     * 添加试题
     * @param examquestion
     */
    void addExamquestion(ExamquestionEntity examquestion);

    /**
     * 添加试题
     * @param examquestions
     */
    void addExamquestions(List<ExamquestionEntity> examquestions);

    /**
     * 删除试题
     * @param id
     */
    void removeExamquestionById(Integer id);

    /**
     * 更新试题
     * @param examquestion
     */
    void updateExamquestion(ExamquestionEntity examquestion);

    /**
     * 根据试卷ID获取试题
     * @param exampaperId
     * @param page
     * @return
     */
    PageAdapter<ExamQuestionDto> getExamquestionByExampaperId(Integer exampaperId, PageAdapter<ExamQuestionDto> page);
}
