package org.eocencle.dasislcy.service;

import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.entity.StudentExamResultEntity;

/**
 * 答卷service
 * @Auther: huanStephen
 * @Date: 2019/4/25
 * @Description:
 */
public interface IAnswerService {

    /**
     * 获取考试答案
     * @param id
     * @return
     */
    StudentExamResultEntity getStudentExamResultById(Integer id);

    /**
     * 添加考试答案
     * @param studentExamResult
     */
    void addStudentExamResult(StudentExamResultEntity studentExamResult);

    /**
     * 删除考试答案
     * @param id
     */
    void removeStudentExamResultById(Integer id);

    /**
     * 更新考试答案
     * @param studentExamResult
     */
    void updateStudentExamResult(StudentExamResultEntity studentExamResult);

    /**
     * 根据试卷id获取考试答案
     * @param exampagerId
     * @return
     */
    PageAdapter<StudentExamResultEntity> getStudentExamResultByExampagerId(Integer exampagerId);

    /**
     * 根据试题id获取考试答案
     * @param examquestionId
     * @return
     */
    StudentExamResultEntity getStudentExamResultByExamquestionId(Integer examquestionId);
}
