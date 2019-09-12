package org.eocencle.dasislcy.dao;

import org.eocencle.dasislcy.entity.StudentExamOutlineEntity;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface StudentExamOutlineMapper extends Mapper<StudentExamOutlineEntity> {

    /**
     * 根据学生试卷ID获得所有试卷结果list
     *
     * @param stuAndExampaperId
     * @return
     */
    List<StudentExamOutlineEntity> getByStuAndExampaperId(Integer stuAndExampaperId);
}