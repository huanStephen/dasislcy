package org.eocencle.dasislcy.dao;

import org.eocencle.dasislcy.entity.StudentExampaperEntity;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface StudentExampaperMapper extends Mapper<StudentExampaperEntity> {

    /**
     * 根据ID获得试卷结果
     *
     * @param stuAndExamId
     * @return
     */
    StudentExampaperEntity getById(Integer stuAndExamId);

    /**
     * 根据学生ID获得所有试卷结果list
     *
     * @param stuId
     * @return
     */
    List<StudentExampaperEntity> getByStuId(Integer stuId);

    /**
     * 更新考试结果分析
     *
     * @param studentExampaperEntity
     * @return
     */
    int updateRecord(StudentExampaperEntity studentExampaperEntity);
}