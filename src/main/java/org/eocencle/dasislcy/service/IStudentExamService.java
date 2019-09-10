package org.eocencle.dasislcy.service;

import org.eocencle.dasislcy.dto.StudentExampaperDto;
import org.eocencle.dasislcy.entity.StudentExampaperEntity;

import java.util.List;

/**
 * Author: Xukai
 * Description: 试题分数计算Service
 * CreateDate: 2019/8/28 21:24
 * Modified By:
 */
public interface IStudentExamService {

    /**
     * 根据ID计算考试结果并保存
     *
     * @param stuAndExamId
     */
    Integer updateRecord(Integer stuAndExamId);

    /**
     * 根据ID获得此次考试结果
     *
     * @param stuAndExamId
     * @return
     */
    StudentExampaperEntity getExamAnalyzeByStuAndExamId(Integer stuAndExamId);

    /**
     * 根据学生ID获得所有ID主键
     *
     * @param stuId
     * @return
     */
    List<StudentExampaperDto> getExamsByStuId(Integer stuId);

}
