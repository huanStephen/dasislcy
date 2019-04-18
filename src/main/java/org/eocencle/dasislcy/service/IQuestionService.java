package org.eocencle.dasislcy.service;

import org.eocencle.dasislcy.dto.ChoiceQuestionDto;
import org.eocencle.dasislcy.entity.SubjectQuestionEntity;

/**
 * @Auther: shizh26250
 * @Date: 2019/3/29 16:09
 * @Description:
 */
public interface IQuestionService {

    void addChoiceQuestion(ChoiceQuestionDto dto, SubjectQuestionEntity subjectQuestion);

}
