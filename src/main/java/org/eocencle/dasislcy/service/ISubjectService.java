package org.eocencle.dasislcy.service;

import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.dto.SubjectQuestionRelationDto;
import org.eocencle.dasislcy.entity.SubjectEntity;
import org.eocencle.dasislcy.entity.SubjectQuestionEntity;

import java.util.List;

/**
 * 科目service
 *
 * @Auther: huanStephen
 * @Date: 2019/4/25
 * @Description:
 */
public interface ISubjectService {

    /**
     * 获取科目
     *
     * @param id
     * @return
     */
    SubjectEntity getSubjectById(Integer id);

    /**
     * 添加科目
     *
     * @param subject
     */
    void addSubject(SubjectEntity subject);

    /**
     * 添加科目试题映射
     *
     * @param mapping
     */
    void addSubjectQuestionMapping(SubjectQuestionEntity mapping);

    /**
     * 删除科目
     *
     * @param id
     */
    void removeSubjectById(Integer id);

    /**
     * 删除科目试题映射
     *
     * @param mappingId
     */
    void removeSubjectQuestionMapping(Integer mappingId);

    /**
     * 更新科目
     *
     * @param subject
     */
    void updateSubject(SubjectEntity subject);

    /**
     * 更新科目映射
     *
     * @param mapping
     */
    void updateSubjectQuestionMapping(SubjectQuestionEntity mapping);

    /**
     * 获取科目列表
     *
     * @param page
     * @return
     */
    PageAdapter<SubjectEntity> getSubjects(PageAdapter<SubjectEntity> page);

    /**
     * 获取科目试题映射列表
     *
     * @param questionId
     * @return
     */
    List<SubjectQuestionRelationDto> getSubjectQuestionMappings(Integer questionId);
}
