package org.eocencle.dasislcy.dao;

import org.eocencle.dasislcy.entity.StudentExamResultEntity;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface StudentExamResultMapper extends Mapper<StudentExamResultEntity> {

    /**
     * 根据学生ID获得所有试卷结果list
     *
     * @param stuAndExampaperId
     * @return
     */
    List<StudentExamResultEntity> getByStuAndExampaperId(Integer stuAndExampaperId);

}