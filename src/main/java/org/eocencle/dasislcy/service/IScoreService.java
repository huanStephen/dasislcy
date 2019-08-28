package org.eocencle.dasislcy.service;

/**
 * Author: Xukai
 * Description: 试题分数计算Service
 * CreateDate: 2019/8/28 21:24
 * Modified By:
 */
public interface IScoreService {

    /**
     * 根据学生ID和试卷ID获得此次考试分数
     *
     * @param stuId
     * @param examId
     * @return
     */
    Float getScoreByStuIdAndExamId(Integer stuId, Integer examId);


}
