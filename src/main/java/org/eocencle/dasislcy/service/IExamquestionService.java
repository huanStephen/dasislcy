package org.eocencle.dasislcy.service;

import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.entity.ExamquestionEntity;

/**
 * 试题service
 * @Auther: huanStephen
 * @Date: 2019/4/25
 * @Description:
 */
public interface IExamquestionService {

    /**
     * 获取试题
     * @param id
     * @return
     */
    ExamquestionEntity getExamquestionById(Integer id);

    /**
     * 添加试题
     * @param examquestion
     */
    void addExamquestion(ExamquestionEntity examquestion);

    /**
     * 删除试题
     * @param id
     */
    void removeExamquestionById(Integer id);

    /**
     * 更新试题
     * @param examquestion
     */
    void updateExamquestion(ExamquestionEntity examquestion);
}
