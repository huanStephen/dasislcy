package org.eocencle.dasislcy.dao;

import org.eocencle.dasislcy.entity.StudentExamResultEntity;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface StudentExamResultMapper extends Mapper<StudentExamResultEntity> {


    /**
     * 根据学生ID和试卷ID获得试卷结果list
     *
     * @param stuId
     * @param examId
     * @return
     */
    List<StudentExamResultEntity> getByStuIdAndExamId(Integer stuId, Integer examId);

    List<StudentExamResultEntity> getByStuId(Integer stuId, Integer examId);

}