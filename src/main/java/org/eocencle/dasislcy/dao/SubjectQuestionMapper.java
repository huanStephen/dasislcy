package org.eocencle.dasislcy.dao;

import org.eocencle.dasislcy.dto.SubjectQuestionRelationDto;
import org.eocencle.dasislcy.entity.SubjectQuestionEntity;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SubjectQuestionMapper extends Mapper<SubjectQuestionEntity> {

    List<SubjectQuestionRelationDto> getSubjectQuestionMappings(Integer questionId);
}