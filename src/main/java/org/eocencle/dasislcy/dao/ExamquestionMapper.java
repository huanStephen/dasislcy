package org.eocencle.dasislcy.dao;

import org.eocencle.dasislcy.dto.ExamQuestionDto;
import org.eocencle.dasislcy.entity.ExamquestionEntity;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ExamquestionMapper extends Mapper<ExamquestionEntity> {

    Integer getExamquestionMaxSort(Integer exampaperId);

    List<ExamQuestionDto> getExamQuestions(Integer exampaperId);
}