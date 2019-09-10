package org.eocencle.dasislcy.dto;

import org.eocencle.dasislcy.entity.ExamquestionEntity;
import org.eocencle.dasislcy.entity.StudentExamResultEntity;

/**
 * 试卷试题转答卷卡
 * @Auther: huanStephen
 * @Date: 2019/4/28
 * @Description:
 */
public class StudentExamQuestionDto extends StudentExamResultEntity {

    public StudentExamQuestionDto() {

    }

    public StudentExamQuestionDto(ExamquestionEntity entity) {
        this.setExamquestionId(entity.getQuestionId());
        this.setQuestionType(entity.getQuestionType());
        this.setSort(entity.getSort());
    }

}
