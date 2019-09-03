package org.eocencle.dasislcy.service;

import org.eocencle.dasislcy.dto.StudentAbilityDto;
import org.eocencle.dasislcy.dto.StudentExamResultDto;

/**
 * Author: Xukai
 * Description: 试题分数计算Service
 * CreateDate: 2019/8/28 21:24
 * Modified By:
 */
public interface IScoreService {

    /**
     * 根据学生ID和试卷ID获得此次考试结果
     *
     * @param stuId
     * @param examId
     * @return
     */
    StudentExamResultDto getScoreByStuIdAndExamId(Integer stuId, Integer examId);


    /**
     * 根据学生ID获得学生能力概况
     *
     * @param stuId
     * @return
     */
    StudentAbilityDto getStudentAbilityByStuId(Integer stuId);
}
