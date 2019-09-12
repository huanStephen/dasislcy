package org.eocencle.dasislcy.service;

import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.entity.ChapterEntity;

/**
 * 章节service
 *
 * @Auther: huanStephen
 * @Date: 2019/4/25
 * @Description:
 */
public interface IChapterService {

    /**
     * 获取章节
     *
     * @param id
     * @return
     */
    ChapterEntity getChapterById(Integer id);

    /**
     * 添加章节
     *
     * @param chapter
     */
    void addChapter(ChapterEntity chapter);

    /**
     * 删除章节
     *
     * @param id
     */
    void removeChapterById(Integer id);

    /**
     * 根据科目删除章节
     *
     * @param subjectId
     */
    void removeChapterBySubjectId(Integer subjectId);

    /**
     * 更新章节
     *
     * @param chapter
     */
    void updateChapter(ChapterEntity chapter);

    /**
     * 根据科目获取章节列表
     *
     * @param subjectId
     * @param page
     * @return
     */
    PageAdapter<ChapterEntity> getChapters(Integer subjectId, PageAdapter<ChapterEntity> page);

}
