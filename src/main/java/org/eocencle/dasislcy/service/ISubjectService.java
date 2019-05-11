package org.eocencle.dasislcy.service;

import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.entity.SubjectEntity;

/**
 * 科目service
 * @Auther: huanStephen
 * @Date: 2019/4/25
 * @Description:
 */
public interface ISubjectService {

    /**
     * 获取科目
     * @param id
     * @return
     */
    SubjectEntity getSubjectById(Integer id);

    /**
     * 添加科目
     * @param subject
     */
    void addSubject(SubjectEntity subject);

    /**
     * 删除科目
     * @param id
     */
    void removeSubjectById(Integer id);

    /**
     * 更新科目
     * @param subject
     */
    void updateSubject(SubjectEntity subject);

    /**
     * 获取科目列表
     * @param page
     * @return
     */
    PageAdapter<SubjectEntity> getSubjects(PageAdapter<SubjectEntity> page);
    
}
