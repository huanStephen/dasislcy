package org.eocencle.dasislcy.service;

import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.entity.StudentExamResultEntity;

/**
 * 答卷service
 *
 * @Auther: huanStephen
 * @Date: 2019/4/25
 * @Description:
 */
public interface IAnswerService {

    /**
     * 获取考试答案
     *
     * @param id
     * @return
     */
    StudentExamResultEntity getStudentExamResultById(Integer id);

    /**
     * 添加考试答案
     *
     * @param studentExamResult
     */
    void addStudentExamResult(StudentExamResultEntity studentExamResult);

    /**
     * 删除考试答案
     *
     * @param id
     */
    void removeStudentExamResultById(Integer id);

    /**
     * 更新考试答案
     *
     * @param studentExamResult
     */
    void updateStudentExamResult(StudentExamResultEntity studentExamResult);

    /**
     * 根据试卷id获取考试答案
     *
     * @param exampagerId
     * @param page
     * @return
     */
    PageAdapter<StudentExamResultEntity> getStudentExamResultByExampagerId(Integer exampagerId, PageAdapter page);

    /**
     * 根据试题id获取考试答案
     *
     * @param studentId
     * @param exampaperId
     * @param examquestionId
     * @return
     */
    StudentExamResultEntity getStudentExamResultByExamquestionId(Integer studentId, Integer exampaperId, Integer examquestionId);

    /**
     * 根据试卷id生成考试答题卡
     *
     * @param studentId
     * @param exampaperId
     */
    void generateStudentExamCardByExampagerId(Integer studentId, Integer exampaperId);
}
