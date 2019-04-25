package org.eocencle.dasislcy.service;

import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.dto.ChoiceQuestionDto;

import java.util.List;

/**
 * 选择题service
 * @Auther: huanStephen
 * @Date: 2019/4/25
 * @Description:
 */
public interface IChoiceQuestionService {

    /**
     * 获取选择题
     * @param id
     * @return
     */
    ChoiceQuestionDto getChoiceQuestionById(Integer id);

    /**
     * 添加选择题
     * @param choiceQuestion
     */
    void addChoiceQuestion(ChoiceQuestionDto choiceQuestion);

    /**
     * 删除选择题
     * @param id
     */
    void removeChoiceQuestionById(Integer id);

    /**
     * 更新选择题
     * @param choiceQuestion
     */
    void updateSubject(ChoiceQuestionDto choiceQuestion);

    /**
     * 根据大纲ids获取试题
     * @param outlineIds
     * @return
     */
    PageAdapter<ChoiceQuestionDto> getChoiceQuestionsByOutlineIds(List<Integer> outlineIds);
}
