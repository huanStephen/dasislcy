package org.eocencle.dasislcy.service;

import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.dto.ChoiceQuestionDto;
import org.eocencle.dasislcy.entity.SubjectQuestionEntity;

import java.util.List;

/**
 * 选择题service
 *
 * @Auther: huanStephen
 * @Date: 2019/4/25
 * @Description:
 */
public interface IChoiceQuestionService {

    /**
     * 获取选择题
     *
     * @param id
     * @return
     */
    ChoiceQuestionDto getChoiceQuestionById(Integer id);

    /**
     * 添加选择题
     *
     * @param choiceQuestion
     */
    void addChoiceQuestion(ChoiceQuestionDto choiceQuestion, SubjectQuestionEntity subjectQuestion);

    /**
     * 删除选择题
     *
     * @param id
     */
    void removeChoiceQuestionById(Integer id);

    /**
     * 更新选择题
     *
     * @param choiceQuestion
     */
    void updateChoiceQuestion(ChoiceQuestionDto choiceQuestion);

    /**
     * 根据大纲ids获取试题
     *
     * @param outlineIds
     * @return
     */
    PageAdapter<ChoiceQuestionDto> getChoiceQuestionsByOutlineIds(List<Integer> outlineIds);

    /**
     * 根据科目、章节、大纲id获取试题
     *
     * @param subjectId
     * @param chapterId
     * @param outlineId
     * @param page
     * @return
     */
    PageAdapter<ChoiceQuestionDto> getChoiceQuestionsBySubjectId(Integer subjectId, Integer chapterId, Integer outlineId, PageAdapter<ChoiceQuestionDto> page);
}
