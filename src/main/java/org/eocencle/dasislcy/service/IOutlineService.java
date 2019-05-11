package org.eocencle.dasislcy.service;

import org.eocencle.dasislcy.entity.OutlineEntity;

import java.util.List;

/**
 * 大纲service
 * @Auther: huanStephen
 * @Date: 2019/4/25
 * @Description:
 */
public interface IOutlineService {

    /**
     * 获取大纲
     * @param id
     * @return
     */
    OutlineEntity getOutlineById(Integer id);

    /**
     * 添加大纲
     * @param outline
     */
    void addOutline(OutlineEntity outline);

    /**
     * 删除大纲
     * @param id
     */
    void removeOutlineById(Integer id);

    /**
     * 更新大纲
     * @param outline
     */
    void updateOutline(OutlineEntity outline);

    /**
     *根据科目id获取大纲
     * @param subjectId
     * @return
     */
    List<OutlineEntity> getOutlineBySubjectId(Integer subjectId);
}
