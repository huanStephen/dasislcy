package org.eocencle.dasislcy.dao;

import org.eocencle.dasislcy.entity.StudentExamResultEntity;
import tk.mybatis.mapper.common.Mapper;

public interface StudentExamResultMapper extends Mapper<StudentExamResultEntity> {

    StudentExamResultEntity getByStuIdAndExamId(Integer stuId, Integer examId);

}