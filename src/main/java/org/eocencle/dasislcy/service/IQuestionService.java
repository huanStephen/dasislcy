package org.eocencle.dasislcy.service;

import org.eocencle.dasislcy.dto.ChoiceQuestionDto;
import org.eocencle.dasislcy.entity.SubjectQuestionEntity;

/**
 * @Auther: huanStephen
 * @Date: 2019/3/29
 * @Description:
 */
public interface IQuestionService {

    void addChoiceQuestion(ChoiceQuestionDto dto, SubjectQuestionEntity subjectQuestion);

}
