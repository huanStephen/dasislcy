package org.eocencle.dasislcy.service;

import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.dto.OutlineDto;
import org.eocencle.dasislcy.entity.OutlineEntity;

import java.util.List;

/**
 * 大纲service
 *
 * @Auther: huanStephen
 * @Date: 2019/4/25
 * @Description:
 */
public interface IOutlineService {

    /**
     * 获取大纲
     *
     * @param id
     * @return
     */
    OutlineEntity getOutlineById(Integer id);

    /**
     * 添加大纲
     *
     * @param outline
     */
    void addOutline(OutlineEntity outline);

    /**
     * 删除大纲
     *
     * @param id
     */
    void removeOutlineById(Integer id);

    /**
     * 根据科目删除大纲
     *
     * @param subjectId
     */
    void removeOutlineBySubjectId(Integer subjectId);

    /**
     * 根据章节删除大纲
     *
     * @param chapterId
     */
    void removeOutlineByChapterId(Integer chapterId);

    /**
     * 更新大纲
     *
     * @param outline
     */
    void updateOutline(OutlineEntity outline);

    /**
     * 根据科目id获取大纲
     *
     * @param subjectId
     * @param level
     * @return
     */
    List<OutlineDto> getOutlineBySubjectId(Integer subjectId, Integer level);

    /**
     * 根据章节id获取大纲
     *
     * @param chapterId
     * @param page
     * @return
     */
    PageAdapter<OutlineEntity> getOutlineByChapterId(Integer chapterId, PageAdapter<OutlineEntity> page);
}
