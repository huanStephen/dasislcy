package org.eocencle.dasislcy.service;

import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.entity.ExampaperEntity;

/**
 * 试卷service
 * @Auther: huanStephen
 * @Date: 2019/4/25
 * @Description:
 */
public interface IExampaperService {

    /**
     * 获取试卷
     * @param id
     * @return
     */
    ExampaperEntity getExampaperById(Integer id);

    /**
     * 添加试卷
     * @param exampaper
     */
    void addExampaper(ExampaperEntity exampaper);

    /**
     * 删除试卷
     * @param id
     */
    void removeExampaperById(Integer id);

    /**
     * 更新试卷
     * @param exampaper
     */
    void updateExampaper(ExampaperEntity exampaper);

    /**
     * 根据科目id获取试卷
     * @param subjectId
     * @return
     */
    PageAdapter<ExampaperEntity> getExampapersBySubjectId(Integer subjectId);

    /**
     * 根据大纲id获取试卷
     * @param outlineId
     * @return
     */
    PageAdapter<ExampaperEntity> getExampapersByOutlineId(Integer outlineId);
}
