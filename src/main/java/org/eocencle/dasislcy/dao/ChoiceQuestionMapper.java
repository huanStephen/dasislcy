package org.eocencle.dasislcy.dao;

import org.eocencle.dasislcy.entity.ChoiceQuestionEntity;
import org.eocencle.dasislcy.entity.SubjectQuestionEntity;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ChoiceQuestionMapper extends Mapper<ChoiceQuestionEntity> {

    /**
     * 根据科目ID、章节ID、大纲ID获取选择题
     * @param subject
     * @return
     */
    List<ChoiceQuestionEntity> getChoiceQuestionsBySubjectId(SubjectQuestionEntity subject);

}