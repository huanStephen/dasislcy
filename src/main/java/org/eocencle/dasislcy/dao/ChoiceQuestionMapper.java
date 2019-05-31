package org.eocencle.dasislcy.dao;

import org.eocencle.dasislcy.entity.ChoiceQuestionEntity;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ChoiceQuestionMapper extends Mapper<ChoiceQuestionEntity> {

    /**
     * 根据科目ID获取选择题
     * @param subjectId
     * @return
     */
    List<ChoiceQuestionEntity> getChoiceQuestionsBySubjectId(Integer subjectId);

}